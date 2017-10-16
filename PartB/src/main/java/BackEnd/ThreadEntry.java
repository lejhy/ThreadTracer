package BackEnd;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ThreadEntry {
    public StringProperty name;
    public LongProperty PID;
    public StringProperty state;
    public IntegerProperty priority;
    public BooleanProperty daemon;
    public StringProperty group;
    
    
    public String getName() {
		return name.get();
	}

	public Long getPID() {
		return PID.get();
	}

	public String getState() {
		return state.get();
	}

	public Integer getPriority() {
		return priority.get();
	}

	public Boolean getDaemon() {
		return daemon.get();
	}
	
	public String getGroup() {
		return group.get();
	}

	public ThreadEntry(Thread thread){
        name = new SimpleStringProperty();
        PID = new SimpleLongProperty();
        state = new SimpleStringProperty();
        priority = new SimpleIntegerProperty();
        daemon = new SimpleBooleanProperty();
        group = new SimpleStringProperty();
        update(thread);
    }
    
    public void update(Thread thread) {
    	name.set(thread.getName());
        PID.set(thread.getId());;
        state.set(thread.getState().name());;
        priority.set(thread.getPriority());;
        daemon.set(thread.isDaemon());;
        group.set(thread.getThreadGroup().getName());
    }
    
}
