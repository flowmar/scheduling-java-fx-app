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
import java.sql.Timestamp;
import java.time.LocalDate;
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
//  for (Customer c : customersArrayList)
//  {
//
//  }
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
 * Retrieve the Appointment information from the database and populate the form fields
 */
public void retrieveAndPopulateAppointment()
{
  
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
      
      // Loop through the customerIdComboBox options
      for ( String a : customerIdComboBox.getItems())
      {
        System.out.println(customerIdComboBox.getItems());
        System.out.println("Customer: " +  a );
        // Extract only the number from the ComboBox option
        int customerInt = Integer.parseInt( a.substring(0, a.indexOf(" ", 0)) );
        // Check if the selected value matches the current value being checked
        if( selectedAppointmentCustomerId == customerInt)
        {
          // If so, set the ComboBox value to the current value
          customerIdComboBox.setValue(a);
          break;
        }
      }
      
      // Get the userID of the selected Appointment
      int selectedAppointmentUserId = selectedAppointmentToUpdate.userIdProperty().getValue();
      System.out.println(selectedAppointmentUserId);
      // Loop through the userIdComboBox options
      for (String u: userIdComboBox.getItems())
      {
        // Extract only the number from the ComboBox option
        int userInt = Integer.parseInt(u.substring(0, u.indexOf(" ", 0)));
        // Check if the selected value matches the current value being checked
        if (selectedAppointmentUserId == userInt)
        {
          // If so, set the ComboBox value to the current value
          userIdComboBox.setValue(u);
          break;
        }
      }
      
      // Get the appointmentType of the selected Appointment
      String selectedAppointmentType = selectedAppointmentToUpdate.typeProperty().getValue();
      System.out.println( "Selected Appointment Type: " + selectedAppointmentType );
      // Loop through the appointmentTypeComboBox options
      for (String t : appointmentTypeComboBox.getItems())
      {
        // If the selected type matches an option in the ComboBox
        if(selectedAppointmentType.equals( t ))
        {
          // Set the value of the ComboBox to that value
          appointmentTypeComboBox.setValue(t);
          break;
        }
      }
      
      // Get the contactID of the selected Appointment
      int selectedAppointmentContactId = selectedAppointmentToUpdate.contactIdProperty().getValue();
      System.out.println("Selected Appointment Contact Id: " + selectedAppointmentContactId);
      System.out.println( "Contact: " + selectedAppointmentContactId );
      // Loop through the contactComboBox options
      for (String c : contactComboBox.getItems())
      {
        // Extract only the number from the ComboBoxOption
        int contactInt = Integer.parseInt( c.substring(0, c.indexOf(" ", 0)));
        // If the selected contactId matches an option in the ComboBox
        if (selectedAppointmentContactId == contactInt)
        {
          // Set the ComboBox value to that option
          contactComboBox.setValue(c);
          break;
        }
      }
      
      // Get the Start Timestamp from the database
      Timestamp selectedAppointmentStartTimestamp = selectedAppointmentToUpdate.startProperty().getValue();
      String timestampString = selectedAppointmentStartTimestamp.toString();
      System.out.println("Timestamp String: " + timestampString );
      // Separate the date from the time
      String startDate = timestampString.substring(0, timestampString.indexOf(" ", 0));
      String startTime = timestampString.substring(timestampString.indexOf(" ", 0), timestampString.length());
      
      System.out.println("Start Date: " + startDate);
      System.out.println("Start Time: " + startTime );
      // Set the Start Date and time from the database into the form fields
      startDatePicker.setValue( LocalDate.parse( startDate ) );
      startTimeTextField.setText( startTime );
      
      
      // Get the End Timestamp from the database
      Timestamp selectedAppointmentEndTimestamp = selectedAppointmentToUpdate.endProperty().getValue();
      String endTimestampString = selectedAppointmentEndTimestamp.toString();
      System.out.println("Timestamp String: " + endTimestampString );
      
      // Separate the date from the time
      String endDate = endTimestampString.substring(0, endTimestampString.indexOf( " ", 0 ));
      String endTime = endTimestampString.substring(endTimestampString.indexOf(" ", 0 ), endTimestampString.length());
      
      System.out.println("End Date: " + endDate);
      System.out.println("End Time: " + endTime );
      // Set the End Date and time from the database into the form fields
      endDatePicker.setValue(LocalDate.parse(endDate));
      endTimeTextField.setText(endTime);
      
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