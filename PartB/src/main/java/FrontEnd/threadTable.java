package main.java.FrontEnd;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TableView;
import main.java.BackEnd.ThreadTracer;
import main.java.BackEnd.threadRep;

import java.util.List;

public class threadTable extends Application{
    private TableView table;
    private ObservableList currentThreadSample;
    public static void main(String[] args) {
        threadTable tTable = new threadTable(
                ThreadTracer.getThreads(
                        Thread.currentThread().getThreadGroup()
                ));
        launch(args);
    }

    public threadTable(List<Thread> threadList){
        currentThreadSample = new FXCollections.observableArrayList(threadList);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Java Agent - 0.1");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);

        table = new TableView();
        TableColumn threadName          = new TableColumn("Name");
        TableColumn threadIdentifier    = new TableColumn("PID");
        TableColumn threadState         = new TableColumn("State");
        TableColumn threadPriority      = new TableColumn("Priority");
        TableColumn threadDeamon        = new TableColumn("Daemon");

        threadName.setCellValueFactory(
                new PropertyValueFactory<threadRep, String>("name")
        );

        threadIdentifier.setCellValueFactory(
                new PropertyValueFactory<threadRep, String>("PID")
        );

        threadState.setCellValueFactory(
                new PropertyValueFactory<threadRep, String>("state")
        );

        threadPriority.setCellValueFactory(
                new PropertyValueFactory<threadRep, String>("priority")
        );

        threadDeamon.setCellValueFactory(
                new PropertyValueFactory<threadRep, String>("daemon")
        );


        table.getColumns().addAll(threadName,threadIdentifier,threadState,threadPriority,threadDeamon);

        table.setItems(currentThreadSample);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(table);
        ((Group) scene.getRoot()).getChildren().add(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void update(List<Thread> threads){

    }
}
