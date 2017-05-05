package application;

import java.io.IOException;

import com.sun.prism.Image;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainClient extends Application {

	private static final int DEFAULT_PORT = 5555;
	public static ClientConsole client;
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("ProtoType");
		//primaryStage.getIcons().add(new Image("/server_earth.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
		client = new ClientConsole("localhost", DEFAULT_PORT);
		client.setAnswerFromServer(false);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
