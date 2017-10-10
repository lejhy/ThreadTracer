package main.java.BackEnd;

public class threadRep {
    String name;
    long PID;
    String state;
    int priority;
    boolean daemon;
    public threadRep(Thread tread){
        name = tread.getName();
        PID = tread.getId();
        state = tread.getState().name();
        priority = tread.getPriority();
        daemon = tread.isDaemon();
    }
}
