package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class EditTeacherController {
	
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
    void cancel(ActionEvent event) {
    	
    }

    @FXML
    void submitTU(ActionEvent event) {

    }


}
