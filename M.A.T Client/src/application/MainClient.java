package application;

import java.io.IOException;
import java.util.Scanner;

import com.sun.prism.Image;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainClient extends Application {

	//private static final int DEFAULT_PORT = 5555;
	public static ClientConnection client;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Scanner reader = new Scanner(System.in);
		Parent root = FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
		String IP;
		int Port;
		
		
		System.out.println("Welcome!\nInsert Server IP: ");
		IP=reader.nextLine();
		System.out.println("\nInsert port number: ");
		Port=reader.nextInt();			
		client = new ClientConnection(IP, Port);
		
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("ProtoType");
		//primaryStage.getIcons().add(new Image("/server_earth.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
		client.setAnswerFromServer(false);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
