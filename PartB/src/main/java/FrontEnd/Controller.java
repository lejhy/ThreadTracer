package FrontEnd;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import BackEnd.ThreadEntry;
import BackEnd.ThreadTracer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
	
	ThreadTracer threadTracer;

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
    void initialize() {
    	assert threadTable != null : "fx:id=\"threadTable\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert pid != null : "fx:id=\"pid\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert state != null : "fx:id=\"state\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert priority != null : "fx:id=\"priority\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert daemon != null : "fx:id=\"daemon\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        
        name.setCellValueFactory(new PropertyValueFactory<ThreadEntry, String>("name"));
        pid.setCellValueFactory(new PropertyValueFactory<ThreadEntry, Long>("PID"));
        state.setCellValueFactory(new PropertyValueFactory<ThreadEntry, String>("state"));
        priority.setCellValueFactory(new PropertyValueFactory<ThreadEntry, Integer>("priority"));
        daemon.setCellValueFactory(new PropertyValueFactory<ThreadEntry, Boolean>("daemon"));
    }
    
    public void setThreadTracer(ThreadTracer threadTracer) {
    	this.threadTracer = threadTracer;
    	threadTable.setItems(threadTracer.getThreads());
    }    
}