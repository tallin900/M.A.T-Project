package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

import java.sql.*;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainGUIController extends MainClient {
	
	
	
    @FXML // fx:id="TeacherIDfield"
    private TextField TeacherIDfield; // Value injected by FXMLLoader

    @FXML // fx:id="nameField"
    private Label nameField; // Value injected by FXMLLoader

    @FXML // fx:id="IDfield"
    private Label IDfield; // Value injected by FXMLLoader

    @FXML // fx:id="EditBtn"
    private Button EditBtn; // Value injected by FXMLLoader

    @FXML // fx:id="TUfield"
    private Label TUfield; // Value injected by FXMLLoader

    @FXML // fx:id="CancelBtn"
    private Button CancelBtn; // Value injected by FXMLLoader

    @FXML // fx:id="SearchBtn"
    private Button SearchBtn; // Value injected by FXMLLoader
    

    @FXML // fx:id="infoPanel"
    private Pane infoPanel; // Value injected by FXMLLoader
    
    
    
    @FXML
    void cancel(ActionEvent event) {
    	System.exit(1);
    }

    @FXML
    void editTeacher(ActionEvent event) throws IOException {
    	Stage primaryStage = new Stage();
  		primaryStage.setTitle("Edit teaching unit");
  		//primaryStage.getIcons().add(new Image("/server_earth.png"));
  	  	FXMLLoader loader = new FXMLLoader();
  	  	Pane root;
  		root = loader.load(getClass().getResource("EditTeacher.fxml").openStream());
  	  	
  	  	Scene scene = new Scene(root);			
  	  	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
  	  	
  	  	primaryStage.setScene(scene);	
  	  	primaryStage.show();
    }

    @FXML
    void searchTeacher(ActionEvent event) throws InterruptedException {
    	
    	HashMap<String, String> queryHash = new HashMap<String, String>();

    	/*Creating the query for server*/
    	queryHash.put("msgType","select");
    	queryHash.put("query","select * from teachers where ID='"+TeacherIDfield.getText()+"'");
    	
    	
    	try{
    	client.accept(queryHash);	//Senting the query to the server
    	}
    	catch(Exception ex){
    		System.out.println("The client couldn't send the query");
    	}
    	
    	/*Creating thread which wait for the server response*/	
    	MessageThread thread = new MessageThread(client,nameField);
    		
    	thread.start();
		synchronized (thread) {
			thread.wait(); //sending the thread to sleep untill message from server is received
			/*Meassage from server received*/
			ArrayList<String> arrayList = (ArrayList<String>)(client.message);	
			nameField.setText((String) arrayList.get(0));
			IDfield.setText((String) arrayList.get(1));
			TUfield.setText((String) arrayList.get(2));
		}

    }



}
