package BackEnd;


import java.util.ArrayList;
import java.util.List;

public class ThreadTracer {

    public ThreadTracer() {
        displayThreads(getThreads(Thread.currentThread().getThreadGroup()));
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
    public synchronized void locked() {
    }


    private void displayThreads(List<Thread> td) {
        for (Thread current : td)
            System.out.println(current.getId() + ":" + current.getName() + " [" + current.getThreadGroup().getName() + "]");
    }

    private List<Thread> getThreads(ThreadGroup threadGroup) {
        List<Thread> threads = new ArrayList<Thread>();
        ThreadGroup rootGroup = threadGroup;
        while (rootGroup.getParent() != null) {
            rootGroup = rootGroup.getParent();
        }
        Thread[] threadArray = new Thread[rootGroup.activeCount()];

        rootGroup.enumerate(threadArray);
        for (Thread thread : threadArray) {
        	threads.add(thread);
        }
        return threads;
    }
}
