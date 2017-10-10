package BackEnd;


import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ThreadTracer {

    private ObservableList<ThreadEntry> threads;
    
    public ThreadTracer() {
    	threads = FXCollections.observableArrayList();
    	refreshThreads();
    	startRefreshLoop();
        Filtration f = new Filtration();
        System.out.println("\n\nFilter\n");
        // displayThreads(f.applyfilter(threads, f.FILTER_DEAMONS));
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

    public ObservableList<ThreadEntry> getThreads() {
        return threads;
    }
    
    public void refreshThreads() {
    	// First of all get the array of existing threads
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        while (rootGroup.getParent() != null) {
            rootGroup = rootGroup.getParent();
        }
        Thread[] threadArray = new Thread[rootGroup.activeCount()];
        rootGroup.enumerate(threadArray);
        
        // Transfer all existing threads to a List
        List<Thread> threadList = new ArrayList<>();
        for (Thread thread : threadArray) {
        	if (thread != null) threadList.add(thread);
        }
        
        // For every threadEntry entry check whether thread still exists
        List<ThreadEntry> entriesToRemove = new ArrayList<>();
        loop:
        for (ThreadEntry threadEntry : threads) {
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
        threads.removeAll(entriesToRemove);
        
        // Create entries for all remaining threads in the list
        for (Thread thread : threadList) {
        	threads.add(new ThreadEntry(thread));
        }
    }
}
