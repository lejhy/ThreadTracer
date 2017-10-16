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
    private ObservableList<String> threadGroupNames;
    private List<Predicate<Thread>> filters;
    private boolean updateFlag;
    
    
    public ThreadTracer() {
        newThreadID = 0;
        updateFlag = true;
        threads = new ArrayList<>();
    	threadEntries = FXCollections.observableArrayList();
    	threadGroupNames = FXCollections.observableArrayList();
    	filters = new ArrayList<Predicate<Thread>>();
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
	        	refreshThreadGroups();
    		}
    	});
    	t.setDaemon(true);
    	t.start();
    }

    public ObservableList<ThreadEntry> getThreadEntries() {
        return threadEntries;
    }
    
    public ObservableList<String> getThreadGroupNames() {
    	return threadGroupNames;
    }
    
    private ThreadGroup getRootGroup() {
    	ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        while (rootGroup.getParent() != null) {
            rootGroup = rootGroup.getParent();
        }
        return rootGroup;
    }
    
    private <E> List<E> transferArrayToList(E[] array) {
    	List<E> list = new ArrayList<E>();
    	for (E item : array) {
            if (item != null) list.add(item);
        }
    	return list;
    }
    
    public void refreshThreads() {
        if(updateFlag) {
            // First of all get the array of existing threadEntries
            ThreadGroup rootGroup = getRootGroup();
            Thread[] threadArray = new Thread[rootGroup.activeCount()];
            rootGroup.enumerate(threadArray);

            List<Thread> threadList = transferArrayToList(threadArray);
            
            filterThreads(threadList);
            
            threads.clear();
            threads.addAll(threadList);

            // For every threadEntry entry check whether thread still exists
            List<ThreadEntry> entriesToRemove = new ArrayList<>();
            loop:
            for (ThreadEntry threadEntry : threadEntries) {
                for (Thread thread : threads) {
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
        }
    }
    
    public void refreshThreadGroups() {
    	ThreadGroup rootGroup  = getRootGroup();
    	ThreadGroup[] threadGroupArray = new ThreadGroup[rootGroup.activeGroupCount()];
    	rootGroup.enumerate(threadGroupArray);
    	
    	List<ThreadGroup> threadGroupList = transferArrayToList(threadGroupArray);
    	
    	threadGroupList.add(rootGroup);
    	
    	for(ThreadGroup threadGroup : threadGroupList) {
    		if (!threadGroupNames.contains(threadGroup.getName())) {
    			threadGroupNames.add(threadGroup.getName());
    		}
    	}
    }

    public void filterThreads(List<Thread> threads) {
        for(Predicate<Thread> filter: filters) {
            Objects.requireNonNull(filter);
            int i = 0;
            while (i < threads.size()) {
                if (!filter.test(threads.get(i))) {
                	threads.remove(i);
                } else {
                    i++;
                }
            }
        }
    }

    public void addFilter(Predicate<Thread> filter){
        filters.add(filter);
    }
    
    public void removeFilter(Predicate<Thread> filter) {
    	filters.remove(filter);
    }

    public boolean terminateThread(long PID){
        for(Thread thread: threads){
            if(thread.getId() == PID){
                try {thread.stop();} catch (Exception e) {}
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
