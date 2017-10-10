package BackEnd;

public class ThreadRep {
    String name;
    long PID;
    String state;
    int priority;
    boolean daemon;
    public ThreadRep(Thread tread){
        name = tread.getName();
        PID = tread.getId();
        state = tread.getState().name();
        priority = tread.getPriority();
        daemon = tread.isDaemon();
    }
}
