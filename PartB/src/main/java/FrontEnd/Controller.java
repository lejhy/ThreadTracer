package FrontEnd;

import java.net.URL;
import java.util.ResourceBundle;

import BackEnd.ThreadRep;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<ThreadRep> threadTable;

    @FXML
    private TableColumn<ThreadRep, String> name;

    @FXML
    private TableColumn<ThreadRep, String> pid;

    @FXML
    private TableColumn<ThreadRep, String> state;

    @FXML
    private TableColumn<ThreadRep, String> priority;

    @FXML
    private TableColumn<ThreadRep, String> daemon;

    @FXML
    void initialize() {
    	assert threadTable != null : "fx:id=\"threadTable\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert pid != null : "fx:id=\"pid\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert state != null : "fx:id=\"state\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert priority != null : "fx:id=\"priority\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        assert daemon != null : "fx:id=\"daemon\" was not injected: check your FXML file 'ThreadAgent.fxml'.";
        
        name.setCellValueFactory(new PropertyValueFactory<ThreadRep, String>("name"));
        pid.setCellValueFactory(new PropertyValueFactory<ThreadRep, String>("PID"));
        state.setCellValueFactory(new PropertyValueFactory<ThreadRep, String>("state"));
        priority.setCellValueFactory(new PropertyValueFactory<ThreadRep, String>("priority"));
        daemon.setCellValueFactory(new PropertyValueFactory<ThreadRep, String>("daemon"));
    }
    
    public void setThreadList(ObservableList<ThreadRep> observableList) {
    	threadTable.setItems(observableList);
	}
}