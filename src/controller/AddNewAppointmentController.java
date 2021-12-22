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
private ComboBox<String> customerIdComboBox;

@FXML
private ComboBox<String> userIdComboBox;

@FXML
private TextField appointmentTitleTextField;

@FXML
private TextField appointmentDescriptionTextField;

@FXML
private TextField appointmentLocationTextField;

@FXML
private ComboBox<String> appointmentTypeComboBox;

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
  populateCustomerIdComboBox();
  populateAppointmentTypeComboBox();
  populateUserIdComboBox();
  
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

/**
 * Populates the <code>contactComboBox</code> with data from the database
 */
public void populateContactComboBox()
{
  // Get all contacts from the database
  ObservableList<String> contactsList = DBAppointments.getContacts();
  // Populate the contactComboBox
  contactComboBox.setItems(contactsList);
}

/**
 * Populates the <code>CustomerIdComboBox</code> with data from the database
 */
public void populateCustomerIdComboBox()
{
  ObservableList<String> customersArrayList = DBAppointments.getCustomers();
  customerIdComboBox.setItems( customersArrayList );
}

/**
 * Populates the <code>appointmentTypeComboBox</code> with the various meeting types
 */
public void populateAppointmentTypeComboBox()
{
  ObservableList<String> appointmentTypesList =  FXCollections.observableArrayList( "Consultation", "Planning " + "Session",
      "De-Briefing", "Closing", "Evaluation" );
  appointmentTypeComboBox.setItems(appointmentTypesList);
}

/**
 * Populates the <code>userIdComboBox</code> with the <code>User</code>s from the database
 */
public void populateUserIdComboBox()
{
  ObservableList<String> userIdList = DBAppointments.getUsers();
  userIdComboBox.setItems(userIdList);
}


/**
 * Adds the new <code>Appointment</code> to the <code>ObservableList<Appointment></code>
 * @param actionEvent User click on the <code>Button</code>
 */
public void addAppointmentButtonListener( ActionEvent actionEvent)
{
  System.out.println( "Add appointment!" );
//  Stage stage = (Stage) addAppointmentButton.getScene().getWindow();

}

/**
 * Cancels the operation and closes the window
 * @param actionEvent User click on the <code>Button</code>
 */
public void cancelButtonListener(ActionEvent actionEvent)
{
  System.out.println( "Cancel add appointment!" );
  
  Stage stage = (Stage) cancelButton.getScene().getWindow();
  stage.close();
}

}
