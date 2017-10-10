import BackEnd.ThreadTracer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.lang.instrument.Instrumentation;

public class ThreadAgent extends Application {
	
	public static Instrumentation instrumentation;
	
	/**
	 * main method is called when ThreadAgent is run on its own and simply launches the JavaFX application.
	 * @param args
	 */

    public static void main(String[] args) {
        System.out.println("Standalone");
        launch(args);
    }
    
    /**
     * premain method is called when ThreadAgent is run as javaagent and needs to first wait for the monitored program to start so that it can see if it is using JavaFX and we should only create a new Stage or we can simply launch a new JavaFX application
     * @param agentArgument
     * @param instrumentation
     */

    public static void premain(String agentArgument, Instrumentation instrumentation) {
        System.out.println("Agent");
        String[] args = {agentArgument};
        ThreadAgent.instrumentation = instrumentation;
        // Create a separate thread for ThreadAgent so that the monitored program's main method can be called
        new Thread(() -> {
        	// Give the monitored program some time to start
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	// Try a standalone launch
        	try {
        		launch(args);
        	} catch (IllegalStateException iSE){
        		// Make use of an existing Platform
        		Platform.runLater(() -> {
    				Application threadAgent = new ThreadAgent();
            		Stage threadAgentStage = new Stage();
            		try {
    					threadAgent.start(threadAgentStage);
    				} catch (Exception e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			});
        	}
        }).start();
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Basic UI just for testing
		String message = "Hello World!";
		Button btnHello = new Button("Exit");
		btnHello.setOnAction((event) -> {
			Platform.exit();
		});
		StackPane root = new StackPane();
		root.getChildren().add(btnHello);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle(message);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Our monitor thread printing into console
        Thread monitor = new Thread() {
        	@Override
        	public void run() {
        		while (true) {
        		    System.out.println("\n\n\n\t>>>>");
        			ThreadTracer tt = new ThreadTracer();
        			try {
        			    Thread.sleep(1000);
        			} catch (InterruptedException e) {
        				break;
        			}
                }
        	}
        };
        //Set Daemon to true so that the thread terminates when no other user threads are active
        monitor.setDaemon(true);
        monitor.start();
	}
}

