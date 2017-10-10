package FrontEnd;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import BackEnd.ThreadRep;
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
    private TableView<Thread> threadTable;

    @FXML
    private TableColumn<Thread, String> name;

    @FXML
    private TableColumn<Thread, Long> pid;

    @FXML
    private TableColumn<Thread, Thread.State> state;

    @FXML
    private TableColumn<Thread, Integer> priority;

    @FXML
    private TableColumn<Thread, Boolean> daemon;

    @FXML
    void initialize() {
    	assert threadTable != null : "fx:id=\"threadTable\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert pid != null : "fx:id=\"pid\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert state != null : "fx:id=\"state\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert priority != null : "fx:id=\"priority\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert daemon != null : "fx:id=\"daemon\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        
        name.setCellValueFactory(new PropertyValueFactory<Thread, String>("name"));
        pid.setCellValueFactory(new PropertyValueFactory<Thread, Long>("id"));
        state.setCellValueFactory(new PropertyValueFactory<Thread, Thread.State>("state"));
        priority.setCellValueFactory(new PropertyValueFactory<Thread, Integer>("priority"));
        daemon.setCellValueFactory(new PropertyValueFactory<Thread, Boolean>("daemon"));
    }
    
    public void setThreadTracer(ThreadTracer threadTracer) {
    	this.threadTracer = threadTracer;
    	threadTable.setItems(threadTracer.getThreads());
    }    
}