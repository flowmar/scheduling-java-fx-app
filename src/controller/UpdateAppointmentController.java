package controller;

import databaseAccess.DBAppointments;
import databaseAccess.DBQuery;
import databaseAccess.JDBC;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.*;
import java.util.ResourceBundle;

import static controller.ViewAppointmentsController.clientAppointments;
import static databaseAccess.DBAppointments.getAllAppointments;

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
private Button updateAppointmentButton;

/**
 * Methods
 */

/**
 * Initializes the Update Appointment Form
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle ) {
  // populateComboBoxes
  populateContactComboBox( );
  populateCustomerIdComboBox( );
  populateUserIdComboBox( );
  populateAppointmentTypeComboBox( );
  
  if ( Main.selectedAppointment != null ) {
    // Get the selected Appointment information from the database and populate fields
    retrieveAndPopulateAppointment( );
  }
}

/**
 * Populates the <code>contactComboBox</code> with data from the database
 */
public void populateContactComboBox( ) {
  // Get all contacts from the database
  ObservableList<String> contactsList = DBAppointments.getContacts( );
  // Populate the contactComboBox
  contactComboBox.setItems( contactsList );
}

/**
 * Populates the <code>CustomerIdComboBox</code> with data from the database
 */
public void populateCustomerIdComboBox( ) {
  ObservableList<String> customersArrayList = DBAppointments.getCustomers( );
  customerIdComboBox.setItems( customersArrayList );
}

/**
 * Populates the <code>appointmentTypeComboBox</code> with the various meeting types
 */
public void populateAppointmentTypeComboBox( ) {
  ObservableList<String> appointmentTypesList = FXCollections.observableArrayList( "Consultation", "Planning " +
                                                                                                       "Session",
      "De-Briefing", "Closing", "Evaluation" );
  appointmentTypeComboBox.setItems( appointmentTypesList );
}

/**
 * Populates the <code>userIdComboBox</code> with the <code>User</code>s from the database
 */
public void populateUserIdComboBox( ) {
  ObservableList<String> userIdList = DBAppointments.getUsers( );
  userIdComboBox.setItems( userIdList );
}

/**
 * Retrieve the Appointment information from the database and populate the form fields
 */
public void retrieveAndPopulateAppointment( ) {
  
  Appointment selectedAppointmentToUpdate = Main.selectedAppointment;

  // Fill in TextFields with data from database
  appointmentIdTextField.setText( String.valueOf( selectedAppointmentToUpdate.appointmentIdProperty( ).getValue( ) ) );
  appointmentTitleTextField.setText( String.valueOf( selectedAppointmentToUpdate.titleProperty( ).getValue( ) ) );
  appointmentDescriptionTextField.setText( String.valueOf( selectedAppointmentToUpdate.descriptionProperty( ).getValue( ) ) );
  appointmentLocationTextField.setText( String.valueOf( selectedAppointmentToUpdate.locationProperty( ).getValue( ) ) );
  
  // Get the customerID of the selected Appointment
  int selectedAppointmentCustomerId = selectedAppointmentToUpdate.customerIdProperty( ).getValue( );
  System.out.println( selectedAppointmentCustomerId );
  
  // Loop through the customerIdComboBox options
  for ( String a : customerIdComboBox.getItems( ) ) {
    System.out.println( customerIdComboBox.getItems( ) );
    System.out.println( "Customer: " + a );
    // Extract only the number from the ComboBox option
    int customerInt = Integer.parseInt( a.substring( 0, a.indexOf( " ", 0 ) ) );
    // Check if the selected value matches the current value being checked
    if ( selectedAppointmentCustomerId == customerInt ) {
      // If so, set the ComboBox value to the current value
      customerIdComboBox.setValue( a );
      break;
    }
  }
  
  // Get the userID of the selected Appointment
  int selectedAppointmentUserId = selectedAppointmentToUpdate.userIdProperty( ).getValue( );
  System.out.println( selectedAppointmentUserId );
  // Loop through the userIdComboBox options
  for ( String u : userIdComboBox.getItems( ) ) {
    // Extract only the number from the ComboBox option
    int userInt = Integer.parseInt( u.substring( 0, u.indexOf( " ", 0 ) ) );
    // Check if the selected value matches the current value being checked
    if ( selectedAppointmentUserId == userInt ) {
      // If so, set the ComboBox value to the current value
      userIdComboBox.setValue( u );
      break;
    }
  }
  
  // Get the appointmentType of the selected Appointment
  String selectedAppointmentType = selectedAppointmentToUpdate.typeProperty( ).getValue( );
  System.out.println( "Selected Appointment Type: " + selectedAppointmentType );
  // Loop through the appointmentTypeComboBox options
  for ( String t : appointmentTypeComboBox.getItems( ) ) {
    // If the selected type matches an option in the ComboBox
    if ( selectedAppointmentType.equals( t ) ) {
      // Set the value of the ComboBox to that value
      appointmentTypeComboBox.setValue( t );
      break;
    }
  }
  
  // Get the contactID of the selected Appointment
  int selectedAppointmentContactId = selectedAppointmentToUpdate.contactIdProperty( ).getValue( );
  System.out.println( "Selected Appointment Contact Id: " + selectedAppointmentContactId );
  System.out.println( "Contact: " + selectedAppointmentContactId );
  // Loop through the contactComboBox options
  for ( String c : contactComboBox.getItems( ) ) {
    // Extract only the number from the ComboBoxOption
    int contactInt = Integer.parseInt( c.substring( 0, c.indexOf( " ", 0 ) ) );
    // If the selected contactId matches an option in the ComboBox
    if ( selectedAppointmentContactId == contactInt ) {
      // Set the ComboBox value to that option
      contactComboBox.setValue( c );
      break;
    }
  }
  
  // Get the Start Timestamp from the database
  String selectedAppointmentStartTimestamp = selectedAppointmentToUpdate.startProperty( ).getValue( );
  String    timestampString                   = selectedAppointmentStartTimestamp.toString( );
  System.out.println( "Timestamp String: " + timestampString );
  // Separate the date from the time
  String startDate             = timestampString.substring( 0, timestampString.indexOf( " ", 0 ) );
  int indexOfT = timestampString.indexOf("t");
  String startTime             = timestampString.substring(indexOfT + 2);
  
  System.out.println( "Start Date: " + startDate );
  System.out.println( "Start Time: " + startTime );
  // Set the Start Date and time from the database into the form fields
  startDatePicker.setValue( LocalDate.parse( startDate ) );
  startTimeTextField.setText( startTime );
  
  
  // Get the End Timestamp from the database
  String selectedAppointmentEndTimestamp = selectedAppointmentToUpdate.endProperty( ).getValue( );
  String    endTimestampString              = selectedAppointmentEndTimestamp.toString( );
  System.out.println( "Timestamp String: " + endTimestampString );
  
  // Separate the date from the time
  String endDate                  = endTimestampString.substring( 0, endTimestampString.indexOf( " ", 0 ) );
  int indexOfTEnd = timestampString.indexOf("t");
  String endTime = endTimestampString.substring( indexOfTEnd + 2);
  
  System.out.println( "End Date: " + endDate );
  System.out.println( "End Time: " + endTime );
  // Set the End Date and time from the database into the form fields
  endDatePicker.setValue( LocalDate.parse( endDate ) );
  endTimeTextField.setText( endTime );
  
}

/**
 * Uses the information in the form fields to update the selected <code>Appointment</code>
 *
 * @param actionEvent User click on the 'Update' <code>Button</code>
 */
public void updateAppointmentButtonListener( ActionEvent actionEvent ) throws ParseException
{
  System.out.println( "Update appointment!" );
  
  try {
    // Get the connection
    Connection connection = JDBC.getConnection( );
    // Update SQL Statement
    String updateStatement = "UPDATE client_schedule.appointments SET Title = ?, Description = ?, Location = ?, Type " +
                                 "= " +
                                 "?, " +
                                 "Start = ?, " +
                                 "End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
    
    DBQuery.setPreparedStatement( connection, updateStatement );
    
    PreparedStatement preparedStatement = DBQuery.getPreparedStatement( );
    
    // Get the values from the startDatePicker and startTimeTextField and combine them into a Timestamp
    LocalDate startDate = startDatePicker.getValue( );
    LocalTime startTime = LocalTime.parse( startTimeTextField.getText( ) );
    // Create a LocalDateTime from the values
    LocalDateTime ldt = startDate.atTime( startTime );
    // Create a ZonedDateTime from the LocalDateTime
    ZonedDateTime startTimeInUTC = ZonedDateTime.of( ldt, ZoneId.of( "UTC" ) );
    // Convert the ZonedDateTime into a Timestamp so that it can be used in the database
    Timestamp startTimestamp = Timestamp.valueOf( startTimeInUTC.toLocalDateTime( ) );
    
    // Get the values from the endDatePicker and endTimeTextField and combine them into a Timestamp
    LocalDate endDate = endDatePicker.getValue( );
    LocalTime endTime = LocalTime.parse( endTimeTextField.getText( ) );
    // Create a LocalDateTime from the values
    LocalDateTime endldt = endDate.atTime( endTime );
    // Create a ZonedDateTime from the LocalDateTime
    ZonedDateTime endTimeInUTC = ZonedDateTime.of( endldt, ZoneId.of( "UTC" ) );
    // Convert the ZonedDateTime into a Timestamp so that it can be stored in the database
    Timestamp endTimestamp = Timestamp.valueOf( endTimeInUTC.toLocalDateTime( ) );
    
    // Get the value from the customerIdComboBox and extract only the integer
    String customerIdString = customerIdComboBox.getValue( );
    int    customerIdInt    = Integer.parseInt( customerIdString.substring( 0, customerIdString.indexOf( " ", 0 ) ) );
    
    // Get the value from the userIdComboBox and extract only the integer
    String userIdString = userIdComboBox.getValue( );
    int    userIdInt    = Integer.parseInt( userIdString.substring( 0, userIdString.indexOf( " ", 0 ) ) );
    
    // Get the value from the contactIdComboBox and extract only the integer
    String contactIdString = contactComboBox.getValue( );
    int    contactIdInt    = Integer.parseInt( contactIdString.substring( 0, contactIdString.indexOf( " ", 0 ) ) );
    
    // Set the values into the SQL statement
    preparedStatement.setString( 1, appointmentTitleTextField.getText( ) );
    preparedStatement.setString( 2, appointmentDescriptionTextField.getText( ) );
    preparedStatement.setString( 3, appointmentLocationTextField.getText( ) );
    preparedStatement.setString( 4, appointmentTypeComboBox.getValue( ) );
    preparedStatement.setString( 5, String.valueOf( startTimestamp ) );
    preparedStatement.setString( 6, String.valueOf( endTimestamp ) );
    preparedStatement.setString( 7, String.valueOf( customerIdInt ) );
    preparedStatement.setString( 8, String.valueOf( userIdInt ) );
    preparedStatement.setString( 9, String.valueOf( contactIdInt ) );
    preparedStatement.setString( 10, appointmentIdTextField.getText( ) );
    preparedStatement.execute( );
    
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  
  // Create a new ObservableList containing the updated Appointment
  ObservableList<Appointment> newAppointmentList = FXCollections.observableArrayList( getAllAppointments( ) );
  
  // Update the ObservableList to update the table
  clientAppointments.setAll( newAppointmentList );
  
  // Close out the window
  Stage stage = ( Stage ) updateAppointmentButton.getScene( ).getWindow( );
  stage.close( );
  
}

/**
 * Cancels the Update Appointment action and closes the window
 *
 * @param actionEvent User click on the 'Cancel' <code>Button</code>
 */
public void cancelButtonListener( ActionEvent actionEvent ) {
  System.out.println( "Cancel add appointment!" );
  
  Stage stage = ( Stage ) cancelButton.getScene( ).getWindow( );
  stage.close( );
}

}