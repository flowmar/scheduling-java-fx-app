package controller;

import databaseAccess.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.ViewAppointmentsController.clientAppointments;
import static scheduler.Main.currentAppointmentId;

public class AddNewAppointmentController implements Initializable {

/**
 * Fields
 */
@FXML
private TextField appointmentIdTextField;

@FXML
private TextField customerIdTextField;

@FXML
private TextField userIdTextField;

@FXML
private TextField appointmentTitleTextField;

@FXML
private TextField appointmentDescriptionTextField;

@FXML
private TextField appointmentLocationTextField;

@FXML
private TextField appointmentTypeTextField;

@FXML
private ComboBox<String> contactComboBox;

@FXML
private DatePicker startDatePicker;

@FXML
private DatePicker endDatePicker;

@FXML
private Button cancelButton;

@FXML
private Button addAppointmentButton;

/**
 * Methods
 */

/**
 *
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle )
{
  // Create appointmentId for new appointment and
  autogenerateAppointmentId();
  populateContactComboBox();
  
}

/**
 * Auto generates a new <code>appointmentId</code>
 */
public void autogenerateAppointmentId()
{
  int appointmentsSize = clientAppointments.size();
  int lastAppointmentInt = appointmentsSize - 1;
  currentAppointmentId = clientAppointments.get(lastAppointmentInt).getAppointmentId();
  appointmentIdTextField.setText( String.valueOf( currentAppointmentId ) );
}

public void populateContactComboBox()
{
  ObservableList<String> contactsList = FXCollections.observableArrayList();
  
  contactsList = DBAppointments.getContacts();
  
  contactComboBox.setItems(contactsList);
}

public void addAppointmentButtonListener( ActionEvent actionEvent)
{
  System.out.println( "Add appointment!" );
  
//  Stage stage = (Stage) addAppointmentButton.getScene().getWindow();

}

public void cancelButtonListener(ActionEvent actionEvent)
{
  System.out.println( "Cancel add appointment!" );
  
  Stage stage = (Stage) cancelButton.getScene().getWindow();
  stage.close();
}

}
