package application;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class MainClient extends Application {

	//private static final int DEFAULT_PORT = 5555;
	public static ClientConnection client;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		String IP;
		int Port;
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Welcome!\nInsert Server IP: ");
		IP=reader.nextLine();
		System.out.println("\nInsert port number: ");
		Port=reader.nextInt();			
		client = new ClientConnection(IP, Port);
		
		
		Parent root = FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("ProtoType");
		primaryStage.getIcons().add(new Image("/school_icon.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
		client.setAnswerFromServer(false);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
