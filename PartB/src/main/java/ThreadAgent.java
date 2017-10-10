import BackEnd.ThreadTracer;
import FrontEnd.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ThreadAgent.fxml"));
		try {
			Scene scene = new Scene(loader.load());
			scene.getStylesheets().add(getClass().getResource("ThreadAgent.css").toString());
			primaryStage.setScene(scene);
		} catch (IOException e) {
			System.out.println("Error loading fxml file");
			System.err.println();
			Platform.exit();
		}
		
		Controller controller = loader.getController();
		controller.setThreadTracer(new ThreadTracer());
		primaryStage.setResizable(true);
		primaryStage.setTitle("ThreadAgent");
		primaryStage.show();
	}
}

