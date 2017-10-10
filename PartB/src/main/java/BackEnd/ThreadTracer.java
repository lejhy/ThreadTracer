package BackEnd;


import java.util.ArrayList;
import java.util.List;

public class ThreadTracer {

    private List<Thread> threads;
    public ThreadTracer() {
        threads = getThreads(Thread.currentThread().getThreadGroup());
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


    private void displayThreads(List<Thread> td) {
        for (Thread current : td)
            System.out.println(current.getId() + ":" + current.getName() + " [" + current.getThreadGroup().getName() + "]");
    }

    public static List<Thread> getThreads(ThreadGroup x) {
        List<Thread> threads = new ArrayList<Thread>();
        ThreadGroup root = x;
        while (root.getParent() != null) {
            root = root.getParent();
        }
        List<ThreadGroup> que = new ArrayList<ThreadGroup>();
        que.add(root);

        while (que.size() > 0) {
            ThreadGroup selected = que.remove(0);
            ThreadGroup[] childen = new ThreadGroup[selected.activeCount()];
            selected.enumerate(childen);
            Thread[] childenThreads = new Thread[selected.activeCount()];
            selected.enumerate(childenThreads);

            for (ThreadGroup current : childen) {
                if (current != null)
                    que.add(current);
            }
            for (Thread current : childenThreads) {
                if (!threads.contains(current))
                    threads.add(current);
            }
        }
        return threads;
    }
}
