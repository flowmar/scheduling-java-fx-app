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
import models.Appointment;
import scheduler.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {
  
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
  private TextField startTimeTextField;
  
  @FXML
  private TextField endTimeTextField;
  
  @FXML
  private Button cancelButton;
  
  @FXML
  private Button updateButton;

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
    // populateComboBoxes
    populateContactComboBox();
    populateCustomerIdComboBox();
    populateUserIdComboBox();
    populateAppointmentTypeComboBox();
    
    if (Main.selectedAppointment != null)
    {
      // Get the selected Appointment information from the database and populate fields
      retrieveAndPopulateAppointment( );
    }
  }

/**
 * Populates the <code>contactComboBox</code> with data from the database
 */
public void populateContactComboBox()
  {
    // Get all contacts from the database
    ObservableList<String> contactsList = DBAppointments.getContacts();
    // Populate the contactComboBox
    contactComboBox.setItems( contactsList );
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
  
  public void retrieveAndPopulateAppointment(){
  
  Appointment selectedAppointmentToUpdate = Main.selectedAppointment;
    
//    if (selectedAppointmentToUpdate != null) {
      // Fill in TextFields with data from database
      appointmentIdTextField.setText(String.valueOf(selectedAppointmentToUpdate.appointmentIdProperty().getValue()));
      appointmentTitleTextField.setText(String.valueOf( selectedAppointmentToUpdate.titleProperty().getValue() ));
      appointmentDescriptionTextField.setText(String.valueOf( selectedAppointmentToUpdate.descriptionProperty().getValue()));
      appointmentLocationTextField.setText(String.valueOf( selectedAppointmentToUpdate.locationProperty().getValue()));
      
      // Get the customerID of the selected Appointment
      int selectedAppointmentCustomerId = selectedAppointmentToUpdate.customerIdProperty().getValue();
      System.out.println( selectedAppointmentCustomerId );
      
      for ( String a : customerIdComboBox.getItems())
      {
        System.out.println(customerIdComboBox.getItems());
        System.out.println("Appointment: " +  a );
//        String customerIdFullString =
        if( String.valueOf( selectedAppointmentToUpdate ).equals( a ))
        {
          customerIdComboBox.setValue( a );
          break;
        }
      }
      // Get the userID of the selected Appointment
//      int selectedAppointmentUserId = selectedAppointmentToUpdate.getUserId();
      // Get the appointmentType of the selected Appointment
//      String selectedAppointmentType = selectedAppointmentToUpdate.getType();
      // Get the contactID of the selected Appointment
//      int selectedAppointmentContactId = selectedAppointmentToUpdate.getContactId();
//    }
  }
  
  
public void updateAppointmentButtonListener( ActionEvent actionEvent)
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