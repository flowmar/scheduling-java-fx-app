package controller;

import databaseAccess.DBAppointments;
import databaseAccess.DBQuery;
import databaseAccess.JDBC;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Appointment;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

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
private TextField startTimeTextField;

@FXML
private TextField endTimeTextField;

@FXML
private Button cancelButton;

@FXML
private Button addAppointmentButton;

/**
 * Methods
 */

/**
 * Initializes the Add Appointment Form
 *
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle ) {
  // Create appointmentId for new appointment and
  autogenerateAppointmentId( );
  populateContactComboBox( );
  populateCustomerIdComboBox( );
  populateAppointmentTypeComboBox( );
  populateUserIdComboBox( );
  
}

/**
 * Auto generates a new <code>appointmentId</code>
 */
public void autogenerateAppointmentId( ) {
  int appointmentsSize   = clientAppointments.size( );
  int lastAppointmentInt = appointmentsSize - 1;
  currentAppointmentId = clientAppointments.get( lastAppointmentInt ).getAppointmentId( );
  if ( currentAppointmentId == lastAppointmentInt ) {
    int r = ( int ) ( Math.random( ) * ( 1000 - 1 ) + 1 );
    currentAppointmentId += r;
  }
  appointmentIdTextField.setText( String.valueOf( currentAppointmentId ) );
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
 * Adds the new <code>Appointment</code> to the <code>ObservableList<Appointment></code>
 *
 * @param actionEvent User click on the <code>Button</code>
 */
public void addAppointmentButtonListener( ActionEvent actionEvent ) {
  
  
  System.out.println( "Add appointment clicked!" );
  System.out.println( "CustomerIdComboBox: " + customerIdComboBox.getValue( ) );
  System.out.println( "UserIdComboBox: " + userIdComboBox.getValue( ) );
  System.out.println( "ContactIdComboBox: " + contactComboBox.getValue( ) );
  
  // Check if the appointment time is within office hours
  boolean timeCheck = checkIfWithinHours();
  
  if (timeCheck) {
    // Obtain the value from the customerIdComboBox
    String customerIdString = customerIdComboBox.getValue( );
    int customerIdInt = Integer.parseInt( customerIdString.substring( 0, customerIdString.indexOf(
        " ", 0 ) ) );
    IntegerProperty customerIdIntProperty = new SimpleIntegerProperty( customerIdInt );
  
  
    // Obtain the value from the userIdComboBox
    String          userIdString      = userIdComboBox.getValue( );
    int             userIdInt         = Integer.parseInt( userIdString.substring( 0, userIdString.indexOf( " ", 0 ) ) );
    IntegerProperty userIdIntProperty = new SimpleIntegerProperty( userIdInt );
  
    //  String userIdString = userIdProperty.getValue().substring(0,userIdProperty.toString().indexOf( " ", 0 ));
    StringProperty titleProperty           = new SimpleStringProperty( appointmentTitleTextField.getText( ) );
    StringProperty descriptionProperty     = new SimpleStringProperty( appointmentDescriptionTextField.getText( ) );
    StringProperty locationProperty        = new SimpleStringProperty( appointmentLocationTextField.getText( ) );
    StringProperty appointmentTypeProperty = new SimpleStringProperty( appointmentTypeComboBox.getValue( ) );
  
    // Obtain the value from the contactComboBox
    String contactIdString = contactComboBox.getValue( );
    int contactIdInt = Integer.parseInt( contactIdString.substring( 0, contactIdString.indexOf( " ",
        0 ) ) );
    IntegerProperty contactIdProperty = new SimpleIntegerProperty( contactIdInt );
  
    // Get the LocalDate from the startDatePicker
    LocalDate startDate = startDatePicker.getValue( );
    // Get the value in the startTimeTextField and convert to a LocalTime
    LocalTime startTime = LocalTime.parse( startTimeTextField.getText( ) );
    System.out.println( startTime );
    // Create a LocalDateTime from the LocalDate and LocalTime values
    LocalDateTime ldt = startDate.atTime( startTime );
    System.out.println( "LDT: " + ldt );
    // Create a ZonedDateTime from the LocalDateTime
    ZonedDateTime startTimeInUTC = ZonedDateTime.of( ldt, ZoneId.of( "UTC" ) );
    System.out.println( "UTC: " + startTimeInUTC );
    // Convert the ZonedDateTime into a Timestamp so that it can be used in the database
    Timestamp      startTimestamp = Timestamp.valueOf( startTimeInUTC.toLocalDateTime( ) );
    StringProperty startProperty  = new SimpleStringProperty( startTimestamp.toString( ) );
  
  
    // Get the LocalDate from the endDatePicker
    LocalDate endDate = endDatePicker.getValue( );
    // Get the value in the endTimeTextField and convert it to a LocalTime
    LocalTime endTime = LocalTime.parse( endTimeTextField.getText( ) );
    // Create a LocalDateTime from the LocalDate and LocalTime values
    LocalDateTime eldt = endDate.atTime( endTime );
    // Create a ZonedDateTime from the LocalDateTime
    ZonedDateTime endTimeInUTC = ZonedDateTime.of( eldt, ZoneId.of( "UTC" ) );
    // Convert the ZonedDateTime into a Timestamp so it can be used in the database
    Timestamp      endTimestamp = Timestamp.valueOf( endTimeInUTC.toLocalDateTime( ) );
    StringProperty endProperty  = new SimpleStringProperty( endTimestamp.toString( ) );
  
    // Create an IntegerProperty from the currentAppointmentId
    IntegerProperty currentAppointmentIdProperty = new SimpleIntegerProperty( currentAppointmentId );
  
    // Prepare the SQL statement and connection
    Connection connection = JDBC.getConnection( );
    String insertStatement = "INSERT INTO client_schedule.appointments(Title, Description, Location, " +
                                 "Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?,?,  " +
                                 "? ,?)";
  
    try {
      DBQuery.setPreparedStatement( connection, insertStatement );
    
      PreparedStatement preparedStatement = DBQuery.getPreparedStatement( );
    
      // Add the new appointment to the database
      preparedStatement.setString( 1, titleProperty.getValue( ) );
      preparedStatement.setString( 2, descriptionProperty.getValue( ) );
      preparedStatement.setString( 3, locationProperty.getValue( ) );
      preparedStatement.setString( 4, appointmentTypeProperty.getValue( ) );
      preparedStatement.setString( 5, String.valueOf( startProperty.getValue( ) ) );
      preparedStatement.setString( 6, String.valueOf( endProperty.getValue( ) ) );
      preparedStatement.setString( 7, String.valueOf( customerIdInt ) );
      preparedStatement.setString( 8, String.valueOf( userIdInt ) );
      preparedStatement.setString( 9, String.valueOf( contactIdInt ) );
      preparedStatement.execute( );
    }
    catch ( SQLException e ) {
      e.printStackTrace( );
    }
  
    // Create a new Appointment Object and add it to the ObservableList
    clientAppointments.add( new Appointment( currentAppointmentIdProperty, titleProperty, descriptionProperty,
        locationProperty, appointmentTypeProperty, startProperty, endProperty, userIdIntProperty, customerIdIntProperty,
        contactIdProperty ) );
  
    Stage stage = ( Stage ) addAppointmentButton.getScene( ).getWindow( );
    stage.close( );
  }
  else
  {
    // Create a new Alert
    Alert scheduleTimeError = new Alert( Alert.AlertType.ERROR);
    // Set the title
    scheduleTimeError.setTitle("Appointment Time Error");
    // Create the error message
    String timeError = "The time of the new appointment is not within office hours. Please adjust them so they are " +
                           "between 8:00am and 10:00pm Eastern Standard Time (8:00 and 22:00)";
    // Set the alert content
    scheduleTimeError.setContentText(timeError);
  
    scheduleTimeError.show();
  }
  
}

/**
 * Cancels the operation and closes the window
 *
 * @param actionEvent User click on the <code>Button</code>
 */
public void cancelButtonListener( ActionEvent actionEvent ) {
  System.out.println( "Cancel add appointment!" );
  
  Stage stage = ( Stage ) cancelButton.getScene( ).getWindow( );
  stage.close( );
}

/**
 * Checks to see if the times in the form are between office hours
 * @return Whether the times are within office hours
 */
public boolean checkIfWithinHours()
{
  boolean withinOfficeHours;
  
  // Convert the String in the TextField to a LocalTime
  LocalTime startTime = LocalTime.parse( startTimeTextField.getText( ) );
  LocalTime endTime = LocalTime.parse(endTimeTextField.getText());
  
  // Get the date from the DatePicker
  LocalDate startDate = startDatePicker.getValue( );
  LocalDate endDate = endDatePicker.getValue( );
  
  // Convert the LocalDate and LocalTime to a ZonedDateTime of the user's local timezone
  ZonedDateTime localZonedAppointmentStartTime = ZonedDateTime.of(startDate, startTime,
      ZoneId.of( TimeZone.getDefault( ).getID()));
  ZonedDateTime easternZonedAppointmentStartTime =
      localZonedAppointmentStartTime.withZoneSameInstant( ZoneId.of("America/New_York") );
  
  System.out.println( "User Timezone Start Time: " + localZonedAppointmentStartTime);
  System.out.println( "Eastern Timezone Converted Start Time: " + easternZonedAppointmentStartTime );
  
  ZonedDateTime localZonedAppointmentEndTime = ZonedDateTime.of(endDate, endTime,
      ZoneId.of( TimeZone.getDefault( ).getID()));
  ZonedDateTime easternZonedAppointmentEndTime = localZonedAppointmentEndTime.withZoneSameInstant( ZoneId.of("America/New_York") );
  
  System.out.println( "User Timezone End Time: " + localZonedAppointmentEndTime);
  System.out.println( "Eastern Timezone Converted End Time: " + easternZonedAppointmentEndTime );
  
  // Create the office hour times
  LocalTime officeOpenLocalTime = LocalTime.of(8,0,0);
  ZonedDateTime officeOpenTime = ZonedDateTime.of(startDate, officeOpenLocalTime, ZoneId.of("America/New_York"));
  LocalTime officeCloseLocalTime = LocalTime.of(22, 0, 0);
  ZonedDateTime officeCloseTime = ZonedDateTime.of(startDate, officeCloseLocalTime, ZoneId.of("America/New_York"));
  
  // Check if easternZonedAppointmentStartTime is between 8am and 10pm EST (8:00 and 22:00)
  withinOfficeHours =
      easternZonedAppointmentStartTime.isAfter( officeOpenTime ) && easternZonedAppointmentEndTime.isBefore( officeCloseTime );
  
  return withinOfficeHours;
}
}
