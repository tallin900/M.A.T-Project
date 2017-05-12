package application;


import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class EditTeacherController{
	
	private MainGUIController main;
	
    @FXML // fx:id="teacherName"
    private Label teacherName; // Value injected by FXMLLoader

    @FXML // fx:id="teacherID"
    private Label teacherID; // Value injected by FXMLLoader

    @FXML // fx:id="TUfield"
    private TextField TUfield; // Value injected by FXMLLoader

    @FXML // fx:id="submitBtn"
    private Button submitBtn; // Value injected by FXMLLoader

    @FXML // fx:id="cancelBtn"
    private Button cancelBtn; // Value injected by FXMLLoader
    
    @FXML
    private Label operationMsg;

    @FXML
    void cancel(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
    }

    @FXML
    void submitTU(ActionEvent event) throws InterruptedException {
    	String TU=TUfield.getText();
    	
    	HashMap<String, String> queryHash = new HashMap<String, String>();

    	/*Creating the query for server*/
    	queryHash.put("msgType","update");
    	queryHash.put("query","update teachers set Teaching_Unit='"+TU+"' where ID='" + teacherID.getText() +"';");
    	MainClient.client.sendMessageToServer(queryHash);
    	
    	/*Creating thread which wait for the server response*/	
    	MessageThread thread = new MessageThread(MainClient.client);
    		
    	thread.start();
		synchronized (thread) {
			thread.wait(); //sending the thread to sleep untill message from server is received
			/*Meassage from server received*/
			int status= (int)MainClient.client.getMessage();
			if(status==0){
				operationMsg.setText("Could no change TU");
				operationMsg.setTextFill(Color.web("#ff0000"));
			}else{
				operationMsg.setText("Updated TU successfully");
				operationMsg.setTextFill(Color.web("#0000ff"));
			}
			operationMsg.setVisible(true);
			main.updateInfo(TU);
			
			}
			
		
    	
    	
    }
    
    public void setFields(ArrayList<String> data,MainGUIController main){
    	this.main=main;
    	teacherID.setText(data.get(1));
    	teacherName.setText(data.get(0));
    }


}
