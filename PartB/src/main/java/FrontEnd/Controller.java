package FrontEnd;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import BackEnd.ThreadEntry;
import BackEnd.ThreadTracer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;

public class Controller {
	
	ThreadTracer threadTracer;
	Predicate<Thread> nameFilter;
	Predicate<Thread> groupFilter;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<ThreadEntry> threadTable;

    @FXML
    private TableColumn<ThreadEntry, String> name;

    @FXML
    private TableColumn<ThreadEntry, Long> pid;

    @FXML
    private TableColumn<ThreadEntry, String> state;

    @FXML
    private TableColumn<ThreadEntry, Integer> priority;

    @FXML
    private TableColumn<ThreadEntry, Boolean> daemon;
    
    @FXML 
    private TableColumn<ThreadEntry, String> group;

    @FXML
    private TextField searchInput;

    @FXML
    private Button btnNewThread;
    
    @FXML
    private ComboBox<String> threadGroupSelector;

    @FXML
    void initialize() {
    	assert threadTable != null : "fx:id=\"threadTable\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert pid != null : "fx:id=\"pid\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert state != null : "fx:id=\"state\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert priority != null : "fx:id=\"priority\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert daemon != null : "fx:id=\"daemon\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert group != null : "fx:id=\"daemon\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert  searchInput!= null : "fx:id=\"seachInput\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        name.setCellValueFactory(new PropertyValueFactory<ThreadEntry, String>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        pid.setCellValueFactory(new PropertyValueFactory<ThreadEntry, Long>("PID"));
        state.setCellValueFactory(new PropertyValueFactory<ThreadEntry, String>("state"));
        priority.setCellValueFactory(new PropertyValueFactory<ThreadEntry, Integer>("priority"));
        daemon.setCellValueFactory(new PropertyValueFactory<ThreadEntry, Boolean>("daemon"));
        group.setCellValueFactory(new PropertyValueFactory<ThreadEntry, String>("group"));
    }
    
    @FXML
    public void handleInterruptAction(ActionEvent event) {
        long PID = threadTable.getSelectionModel().getSelectedItem().getPID();
    	System.out.println("interrupt thread with pid " + PID);
    	if(threadTracer.interruptThread(PID))
    	    System.out.println("Success");
    }

	@FXML
	public void handleStopAction(ActionEvent event) {
		long PID = threadTable.getSelectionModel().getSelectedItem().getPID();
		System.out.println("stop thread with pid " + PID);
		if(threadTracer.stopThread(PID))
			System.out.println("Success");
	}
    
    @FXML
    public void handleDetailsAction(ActionEvent event) {
    	System.out.println("details about thread with pid " + threadTable.getSelectionModel().getSelectedItem().getPID());
    }
    
    public void setThreadTracer(ThreadTracer threadTracer) {
    	this.threadTracer = threadTracer;
    	threadTable.setItems(threadTracer.getThreadEntries());
    	threadGroupSelector.setItems(threadTracer.getThreadGroupNames());
    }


    @FXML
    void searchFilter(KeyEvent event) {
    	String searchQueary = "" + (searchInput.getCharacters());
    	threadTracer.removeFilter(nameFilter);
    	nameFilter = thread -> thread.getName().contains(searchQueary) == true;
    	threadTracer.addFilter(nameFilter);
    }

    @FXML
    void handleEditCancel(TableColumn.CellEditEvent<ThreadEntry, String> event) {
        threadTracer.setUpdateFlag(true);
    }

    @FXML
    void handleEditCommit(TableColumn.CellEditEvent<ThreadEntry, String> event) {
        event.getRowValue().name.setValue(event.getNewValue());
        threadTracer.changeThreadName(event.getNewValue(), event.getRowValue().PID.get());
        threadTracer.setUpdateFlag(true);
    }

    @FXML
    void handleEditStart(TableColumn.CellEditEvent<ThreadEntry, String> event) {
        threadTracer.setUpdateFlag(false);
    }


	@FXML
	void handlebtnNewThread(ActionEvent event) {
		threadTracer.createNewThread();
	}
	
	@FXML
    void threadGroupSelect(ActionEvent event) {
		String groupName = threadGroupSelector.getSelectionModel().getSelectedItem();
		threadTracer.removeFilter(groupFilter);
		groupFilter = thread -> {
			ThreadGroup threadGroup = thread.getThreadGroup();
			while (threadGroup != null) {
				if (threadGroup.getName().equals(groupName)) {
					return true;
				}
				threadGroup = threadGroup.getParent();
			} 
			return false;
		};
		threadTracer.addFilter(groupFilter);
    }
}