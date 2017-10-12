package BackEnd;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ThreadTracer {
    private int newThreadID;
    private List<Thread> threads;
    private ObservableList<ThreadEntry> threadEntries;
    private List<Predicate<ThreadEntry>> filters;
    private boolean updateFlag;
    public ThreadTracer() {
        newThreadID = 0;
        updateFlag = true;
        threads = new ArrayList<>();
    	threadEntries = FXCollections.observableArrayList();
    	filters = new ArrayList<Predicate<ThreadEntry>>();
    	refreshThreads();
    	startRefreshLoop();
        Filtration f = new Filtration();
        System.out.println("\n\nFilter\n");
        // displayThreads(f.applyfilter(threadEntries, f.FILTER_DEAMONS));
    }

    /**
     * private void VMTracker(){
     * Map<Integer, LocalVirtualMachine> vm = LocalVirtualMachine.getAllVirtualMachines()
     * for (final Map.Entry<Integer, LocalVirtualMachine> entry : vm.entrySet()) {
     * System.out.println(entry.getKey() + " : " + entry.getValue().displayName());
     * entry.getValue()
     * }
     * }
     **/
    
    private void startRefreshLoop() {
    	Thread t = new Thread(() -> {
    		while(true) {
	    		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	refreshThreads();
    		}
    	});
    	t.setDaemon(true);
    	t.start();
    }

    public ObservableList<ThreadEntry> getThreadEntries() {
        return threadEntries;
    }
    
    public void refreshThreads() {
        if(updateFlag) {
            // First of all get the array of existing threadEntries
            ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
            while (rootGroup.getParent() != null) {
                rootGroup = rootGroup.getParent();
            }
            Thread[] threadArray = new Thread[rootGroup.activeCount()];
            rootGroup.enumerate(threadArray);

            // Transfer all existing threadEntries to a List
            List<Thread> threadList = new ArrayList<>();
            for (Thread thread : threadArray) {
                if (thread != null) threadList.add(thread);
            }
            threads.clear();
            threads.addAll(threadList);

            // For every threadEntry entry check whether thread still exists
            List<ThreadEntry> entriesToRemove = new ArrayList<>();
            loop:
            for (ThreadEntry threadEntry : threadEntries) {
                for (Thread thread : threadArray) {
                    // If the thread exists, merely update its entry and remove the thread from the list
                    if (thread.getId() == threadEntry.PID.get()) {
                        threadEntry.update(thread);
                        threadList.remove(thread);
                        continue loop;
                    }
                }
                // If the thread no longer exists, remove its entry
                entriesToRemove.add(threadEntry);
            }
            threadEntries.removeAll(entriesToRemove);

            // Create entries for all remaining threadEntries in the list
            for (Thread thread : threadList) {
                threadEntries.add(new ThreadEntry(thread));
            }
            filterThreads();
        }
    }

    public void filterThreads() {
        for(Predicate<ThreadEntry> filter: filters) {
            Objects.requireNonNull(filter);
            int i = 0;
            while (i < threadEntries.size()) {
                if (!filter.test(threadEntries.get(i))) {
                    threadEntries.remove(i);
                } else {
                    i++;
                }
            }
        }
    }

    public void setFilter(Predicate<ThreadEntry> filter){
        filters.clear();
        filters.add(filter);
    }

    public boolean terminateThread(long PID){
        for(Thread thread: threads){
            if(thread.getId() == PID){
                thread.stop();
                return true;
            }
        }
        return false;
    }
    public void setUpdateFlag(boolean value){
        updateFlag = value;
    }

    public void changeThreadName(String name, long PID) {
        for(Thread thread: threads){
            if(thread.getId() == PID){
                thread.setName(name);
                break;
            }
        }
    }

    public void createNewThread() {

        Thread newThread = new Thread(() -> {while(true){}});
        newThread.setDaemon(true);
        newThread.setName("Custom thread " + newThreadID);
        newThread.start();

        newThreadID ++;
    }
}
