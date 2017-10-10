package BackEnd;


import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ThreadTracer {

    private ObservableList<Thread> threads;
    
    public ThreadTracer() {
    	threads = FXCollections.observableArrayList();
    	refreshThreads();
    	startRefreshLoop();
        Filtration f = new Filtration();
        displayThreads(threads);
        System.out.println("\n\nFilter\n");
        displayThreads(f.applyfilter(threads, f.FILTER_DEAMONS));
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

    private void displayThreads(List<Thread> td) {
        for (Thread current : td)
            System.out.println(current.getId() + ":" + current.getName() + " [" + current.getThreadGroup().getName() + "]");
    }

    public ObservableList<Thread> getThreads() {
        return threads;
    }
    
    public void refreshThreads() {
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        while (rootGroup.getParent() != null) {
            rootGroup = rootGroup.getParent();
        }
        Thread[] threadArray = new Thread[rootGroup.activeCount()];

        rootGroup.enumerate(threadArray);
        threads.clear();
        for (Thread thread : threadArray) {
        	threads.add(thread);
        }
    }
}
