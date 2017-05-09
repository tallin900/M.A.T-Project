package application;

// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.ArrayList;

import Communication.*;

/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole 
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge  
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ClientConnection implements ChatIF 
{
  //Class variables *************************************************
  
  /**
   * The default port to connect on.
   */
  private Object message;
  
  
  public Object getMessage() {
	return message;
}

final public static int DEFAULT_PORT = 5555;
  public volatile boolean answerFromServer;
  //Instance variables **********************************************
  
  /**
   * The instance of the client that created this ConsoleChat.
   */
  ChatClient client;

  
  //Constructors ****************************************************

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   */
  

  
  public ClientConnection(String host, int port) 
  {
    try 
    {
      client= new ChatClient(host, port, this);
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection!"
                + " Terminating client.");
      System.exit(1);
    }
  }

  
  //Instance methods ************************************************
  
  /**
   * This method waits for input from the console.  Once it is 
   * received, it sends it to the client's message handler.
   */
  public void SendMessageToServer(Object message) 
  {
	  
	  try{
		  client.handleMessageFromClientUI(message);
	  }
	  catch(Exception ex)
	  {
		  System.out.println("error");
	  }
	  
  }

  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */
  public void SetMessage(Object message) 
  {
	this.message=message;
	setAnswerFromServer(true);
    //System.out.println("> " + message);
  }


public void setAnswerFromServer(boolean answer) {
	// TODO Auto-generated method stub
	this.answerFromServer=answer;
}

public boolean getAnswerFromServer()
{
	return this.answerFromServer;
}



  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] The host to connect to.
   */

}
//End of ConsoleChat class
