package controller;

import databaseAccess.DBAppointments;
import databaseAccess.DBQuery;
import databaseAccess.JDBC;
import javafx.beans.property.*;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
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
  System.out.println( "Add appointment clicked!" );
  System.out.println( "CustomerIdComboBox: " + customerIdComboBox.getValue() );
  System.out.println( "UserIdComboBox: " + userIdComboBox.getValue() );
  System.out.println( "ContactIdComboBox: " + contactComboBox.getValue() );
  
  // Obtain the value from the customerIdComboBox
  ObjectProperty<String> customerIdProperty = new SimpleObjectProperty<String>(customerIdComboBox.getValue());
  System.out.println("CustomerIdProperty: " + customerIdProperty );
  String customerIdString = String.valueOf(customerIdProperty.getValue()).substring(0,
      String.valueOf(customerIdProperty.getValue()).indexOf(
      " ",
      0));
  System.out.println("CustomerIdString: " + customerIdString);
  int customerIdInt = Integer.parseInt( customerIdString );
  
  // Obtain the value from the userIdComboBox
  StringProperty userIdProperty = new SimpleStringProperty(userIdComboBox.getValue());
  System.out.println(userIdProperty);
  String userIdString = userIdProperty.getValue().substring(0,userIdProperty.getValue().indexOf(" ", 0));
  System.out.println("User ID String: " + userIdString);
  int userIdInt = Integer.parseInt(userIdString);
  // 1 test
//  String userIdString = userIdProperty.getValue().substring(0,userIdProperty.toString().indexOf( " ", 0 ));
  StringProperty titleProperty = new SimpleStringProperty(appointmentTitleTextField.getText());
  StringProperty descriptionProperty = new SimpleStringProperty(appointmentDescriptionTextField.getText());
  StringProperty locationProperty = new SimpleStringProperty(appointmentLocationTextField.getText());
  StringProperty appointmentTypeProperty = new SimpleStringProperty(appointmentTypeComboBox.getValue());
  
  // Obtain the value from the contactComboBox
  StringProperty contactProperty = new SimpleStringProperty(contactComboBox.getValue());
  System.out.println("Contact Property: " + contactProperty);
  String contactIdString = contactProperty.getValue().substring(0,contactProperty.getValue().indexOf(" ", 0));
  int contactIdInt = Integer.parseInt( contactIdString );
  
  // Get the LocalDate from the startDatePicker
  LocalDate startDate = startDatePicker.getValue();
  // Get the value in the startTimeTextField and convert to a LocalTime
  LocalTime startTime = LocalTime.parse(startTimeTextField.getText());
  System.out.println(startTime);
  // Create a LocalDateTime from the LocalDate and LocalTime values
  LocalDateTime ldt = startDate.atTime(startTime);
  System.out.println("LDT: " + ldt);
  // Create a ZonedDateTime from the LocalDateTime
  ZonedDateTime startTimeInUTC = ZonedDateTime.of(ldt, ZoneId.of( "UTC" ) );
   System.out.println("UTC: " + startTimeInUTC);
   // Convert the ZonedDateTime into a Timestamp so that it can be used in the database
  Timestamp startTimestamp = Timestamp.valueOf(startTimeInUTC.toLocalDateTime());
  ObjectProperty<Timestamp> startProperty = new SimpleObjectProperty<Timestamp>( startTimestamp );
  
   
   // Get the LocalDate from the endDatePicker
  LocalDate endDate = endDatePicker.getValue();
  // Get the value in the endTimeTextField and convert it to a LocalTime
  LocalTime endTime = LocalTime.parse(endTimeTextField.getText());
  // Create a LocalDateTime from the LocalDate and LocalTime values
  LocalDateTime eldt = endDate.atTime(endTime);
  // Create a ZonedDateTime from the LocalDateTime
  ZonedDateTime endTimeInUTC = ZonedDateTime.of(eldt, ZoneId.of("UTC"));
  // Convert the ZonedDateTime into a Timestamp so it can be used in the database
  Timestamp endTimestamp = Timestamp.valueOf(endTimeInUTC.toLocalDateTime());
  ObjectProperty<Timestamp> endProperty = new SimpleObjectProperty<Timestamp>( endTimestamp );
  
  // Create an IntegerProperty from the currentAppointmentId
  IntegerProperty currentAppointmentIdProperty = new SimpleIntegerProperty(currentAppointmentId);
  
  // Prepare the SQL statement and connection
  Connection connection = JDBC.getConnection();
  String insertStatement = "INSERT INTO client_schedule.appointments(Title, Description, Location, " +
                                "Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?,?,  " +
                                "? ,?)";
  
  try
  {
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
  catch ( SQLException e )
  {
  e.printStackTrace( );
}
  
  // Create a new Appointment Object and add it to the ObservableList
//  clientAppointments.add(new Appointment(currentAppointmentIdProperty, titleProperty, descriptionProperty,
//      locationProperty, appointmentTypeProperty, startProperty, endProperty, userIdProperty, customerIdProperty,
//      contactProperty) );
  
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
