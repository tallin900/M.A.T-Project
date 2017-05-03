package application;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import application.ClientMsg.QueryType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ocsf.server.*;
import java.sql.*;


/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  LogController logController;
  Connection DBConn;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient(Object msg, ConnectionToClient client){
	  	logController.showMsg("Message received: " + msg + " from " + client);
    	HashMap<String, String> clientMsg = (HashMap<String, String>) msg;
    	if(clientMsg.get("msgType").equals("select")){
			selectQuery(clientMsg, client);
    	}else if(clientMsg.get("msgType").equals("update")){
    		updateQuery(clientMsg, client);
    	}
  }
  
  private void selectQuery(HashMap<String, String> clientMsg, ConnectionToClient client){
	  Statement stmt;
	  try {
			stmt = DBConn.createStatement();
			ResultSet result = stmt.executeQuery(clientMsg.get("query"));
			client.sendToClient(result);
		} catch (Exception e) {
			logController.showMsg("ERROR: server could not execute the query");
			e.printStackTrace();
		}
  }
  
  private void updateQuery(HashMap<String, String> clientMsg, ConnectionToClient client){
	  Statement stmt;
	  try {
  		stmt = DBConn.createStatement();
  		int result = stmt.executeUpdate(clientMsg.get("query"));
			client.sendToClient(result);
		} catch (Exception e) {
			logController.showMsg("ERROR: server could not execute the query");
			e.printStackTrace();
		}
  }

    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
	  logController.showMsg("Server listening for connection on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    logController.showMsg
      ("Server has stopped listening for connections.");
  }
  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
 * @throws IOException 
   */
  public void setServerCon(String user, String password, String portStr) throws IOException
  {
    int port = 0; //Port to listen on
    
    
    try 
	{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (Exception ex) {/* handle the error*/}
    
    //open log events controller
  	try {
  		Stage primaryStage = new Stage();
  		primaryStage.setTitle("EchoServer log system");
  		primaryStage.getIcons().add(new Image("/server_earth.png"));
  	  	FXMLLoader loader = new FXMLLoader();
  	  	Pane root;
  		root = loader.load(getClass().getResource("LogController.fxml").openStream());
  	  	
  	  	Scene scene = new Scene(root);			
  	  	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
  	  	
  	  	logController = loader.getController();
  	  	primaryStage.setScene(scene);	
  	  	primaryStage.show();
  	} catch (IOException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
    
    try 
    {
        DBConn = DriverManager.getConnection("jdbc:mysql://localhost/mat",user,password);
        logController.showMsg("SQL connection succeed");
    }catch (SQLException ex) 
	    {/* handle any errors*/
        logController.showMsg("SQLException: " + ex.getMessage());
        logController.showMsg("SQLState: " + ex.getSQLState());
        logController.showMsg("VendorError: " + ex.getErrorCode());
        }

    try
    {
      port = Integer.parseInt(portStr); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
    
    try 
    {
    	this.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      logController.showMsg("ERROR - Could not listen for clients!");
    }
  }
}
//End of EchoServer class
