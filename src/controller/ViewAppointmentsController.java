package controller;

import databaseAccess.DBQuery;
import databaseAccess.JDBC;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Appointment;
import scheduler.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static databaseAccess.DBAppointments.getAllAppointments;
import static databaseAccess.DBAppointments.getAllAppointmentsForComparison;

public class ViewAppointmentsController implements Initializable {

/**
 * Fields
 */
public static ObservableList<Appointment> clientAppointments = FXCollections.observableArrayList( );


@FXML
private TableView<Appointment> viewAppointmentsTableView;

@FXML
private TableColumn<Appointment, Number> colAppointmentId;

@FXML
private TableColumn<Appointment, String> colTitle;

@FXML
private TableColumn<Appointment, String> colDescription;

@FXML
private TableColumn<Appointment, String> colType;

@FXML
private TableColumn<Appointment, String> colLocation;

@FXML
private TableColumn<Appointment, Timestamp> colStart;

@FXML
private TableColumn<Appointment, Timestamp> colEnd;

@FXML
private TableColumn<Appointment, Number> colContactId;

@FXML
private TableColumn<Appointment, Number> colUserId;

@FXML
private TableColumn<Appointment, Number> colCustomerId;


@FXML
private Button exitButton;

@FXML
private Button deleteButton;

@FXML
private Button viewCustomersButton;

@FXML
private Button updateButton;

@FXML
private Button addButton;

@FXML
private RadioButton monthViewRadioButton;

@FXML
private RadioButton weekViewRadioButton;

@FXML
private RadioButton allViewRadioButton;

@FXML
private Label deleteAppointmentConfirmationLabel;

/**
 * Methods
 */


/**
 * Initializes the View Appointments window
 *
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle ) {
  System.out.println( "View Appointments Opening" );
  try {
    displayAppointments( );
  }
  catch ( SQLException | ParseException e ) {
    e.printStackTrace( );
  }
}

/**
 * Obtains the stored <code>Appointment</code> records and displays them within a <code>TableView</code>
 * Lambdas are used here to shorten the code and forego the use of a return statement and the type declaration of
 * each column, '(CellDataFeatures<Appointment, {Type}> cell)'
 * @throws SQLException Thrown if the SQL is malformed
 */
public void displayAppointments( ) throws SQLException, ParseException {
  System.out.println( "Display appointment records." );
  // Obtain all Appointments from the database
  clientAppointments        = getAllAppointments( );
//  Main.currentAppointmentId = clientAppointments.size( ) + 1;
  
  // Create the columns for the TableView
  TableColumn<Appointment, Number> colAppointmentId = new TableColumn<>( "Appointment_ID" );
  TableColumn<Appointment, String> colTitle         = new TableColumn<>( "Title" );
  TableColumn<Appointment, String> colDescription   = new TableColumn<>( "Description" );
  TableColumn<Appointment, String> colLocation      = new TableColumn<>( "Location" );
  TableColumn<Appointment, String> colType          = new TableColumn<>( "Type" );
  TableColumn<Appointment, String> colStart         = new TableColumn<>( "Start" );
  TableColumn<Appointment, String> colEnd           = new TableColumn<>( "End" );
  TableColumn<Appointment, Number> colCustomerId    = new TableColumn<>( "Customer_ID" );
  TableColumn<Appointment, Number> colUserId        = new TableColumn<>( "User_ID" );
  TableColumn<Appointment, Number> colContactId     = new TableColumn<>( "Contact_ID" );
  
  // Set the cell values for each column
  colAppointmentId.setCellValueFactory( cell -> cell.getValue( ).appointmentIdProperty( ) );
  colTitle.setCellValueFactory( cell -> cell.getValue( ).titleProperty( ) );
  colDescription.setCellValueFactory( cell -> cell.getValue( ).descriptionProperty( ) );
  colLocation.setCellValueFactory( cell -> cell.getValue( ).locationProperty( ) );
  colType.setCellValueFactory( cell -> cell.getValue( ).typeProperty( ) );
  colStart.setCellValueFactory( cell -> cell.getValue( ).startProperty( ) );
  colEnd.setCellValueFactory( cell -> cell.getValue( ).endProperty( ) );
  colCustomerId.setCellValueFactory( cell -> cell.getValue( ).customerIdProperty( ) );
  colUserId.setCellValueFactory( cell -> cell.getValue( ).userIdProperty( ) );
  colContactId.setCellValueFactory( cell -> cell.getValue( ).contactIdProperty( ) );
  
  // Set the items in the TableView
  viewAppointmentsTableView.setItems( clientAppointments );
  viewAppointmentsTableView.getColumns( ).addAll( colAppointmentId, colTitle, colDescription, colLocation, colType,
      colStart, colEnd, colCustomerId, colUserId, colContactId );
  
}

/**
 * Switches the window to show all Customer Records
 *
 * @param actionEvent User click on the button
 * @throws IOException Thrown if there is an error in finding the FXML file
 */
public void viewCustomersButtonListener( ActionEvent actionEvent ) throws IOException {
  // Open the ViewCustomers scene
  Parent viewCustomersFXML  = FXMLLoader.load( getClass( ).getResource( "../views/viewCustomerRecords.fxml" ) );
  Scene  viewCustomersScene = new Scene( viewCustomersFXML, 975, 400 );
  Stage  viewCustomersStage = new Stage( );
  viewCustomersStage.setTitle( "View Customers" );
  viewCustomersStage.setScene( viewCustomersScene );
  viewCustomersStage.show( );
  
  // Close out the current scene
  Stage viewStage = ( Stage ) viewCustomersButton.getScene( ).getWindow( );
  viewStage.close( );
}


/**
 * Handles click of 'Exit' button. Closes the database connection and exits the application.
 *
 * @param actionEvent User click on the button
 */
public void exitButtonListener( ActionEvent actionEvent ) {
  // Close out the application
  JDBC.closeConnection( );
  System.out.println( "Exit Clicked!" );
  Platform.exit( );
}

;

/**
 * Handles click of 'Delete' Button. Deletes the selected <code>Appointment</code>
 *
 * @param actionEvent User click on 'Delete' button
 * @throws SQLException Thrown if SQL is malformed
 */
public void deleteButtonListener( ActionEvent actionEvent ) throws SQLException {
  // Get the appointmentId from the selected Appointment
  Appointment deleteSelection = viewAppointmentsTableView.getSelectionModel( ).getSelectedItem( );
  int         appointmentId   = deleteSelection.getAppointmentId( );
  String      appointmentType = deleteSelection.getType( );
  
  // Create a delete confirmation alert
  Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
  alert.setTitle( "Delete Appointment" );
  String s = "Are you sure you want to delete " + appointmentType + " meeting with ID " + appointmentId;
  alert.setContentText( s );
  Optional<ButtonType> result = alert.showAndWait( );
  
  // Handle click of 'Ok' Button
  if ( ( result.isPresent( ) ) && ( result.get( ) == ButtonType.OK ) ) {
    
    System.out.println( "Ok clicked!" );
    
    Connection connection = JDBC.getConnection( );
    
    // Use SQL query to delete the customer at that ID
    String deleteStatement = "DELETE FROM client_schedule.appointments WHERE Appointment_ID = ?";
    
    // Prepare the statement
    DBQuery.setPreparedStatement( connection, deleteStatement );
    
    PreparedStatement preparedStatement = DBQuery.getPreparedStatement( );
    
    // Execute the statement
    preparedStatement.setString( 1, String.valueOf( appointmentId ) );
    preparedStatement.execute( );
    
    // Remove from ObservableList to update TableView
    clientAppointments.remove( deleteSelection );
    
    // Display Delete Confirmation
    deleteAppointmentConfirmationLabel.setText( appointmentType + " with ID " + appointmentId + " has been " +
                                                    "cancelled!" );
    
    viewAppointmentsTableView.refresh();
  }
  
  // Handle click of 'Cancel' button
  if ( ( result.isPresent( ) ) && ( result.get( ) == ButtonType.NO ) ) {
    System.out.println( "Cancel clicked!" );
  }
}

;

/**
 * Handles click of 'Add' <code>Button</code>, opens the Add Appointment form.
 *
 * @param actionEvent User click on 'Add' <code>Button</code>
 */
public void addButtonListener( ActionEvent actionEvent ) {
  
  try {
    Parent addAppointmentFXML  = FXMLLoader.load( getClass( ).getResource( "../views/addAppointment.fxml" ) );
    Stage  addAppointmentStage = new Stage( );
    Scene  addAppointmentScene = new Scene( addAppointmentFXML, 975, 400 );
    addAppointmentStage.setTitle( "Add Appointment" );
    addAppointmentStage.setScene( addAppointmentScene );
    addAppointmentStage.show( );
  }
  catch ( IOException e ) {
    e.printStackTrace( );
  }
  
}

;

/**
 * Handles click of 'Update' <code>Button</code>, opens the Update Appointment form.
 *
 * @param actionEvent User click on 'Update' <code>Button</code>
 */
public void updateButtonListener( ActionEvent actionEvent ) {
  
  Main.selectedAppointment = viewAppointmentsTableView.getSelectionModel( ).getSelectedItem( );
  System.out.println( "Selected Appointment: " + Main.selectedAppointment );
  
  Parent updateAppointmentFXML = null;
  try {
    updateAppointmentFXML = FXMLLoader.load( getClass( ).getResource( "../views/updateAppointment.fxml" ) );
  }
  catch ( IOException e ) {
    e.printStackTrace( );
  }
  Stage updateAppointmentStage = new Stage( );
  Scene updateAppointmentScene = new Scene( updateAppointmentFXML, 975, 400 );
  updateAppointmentStage.setTitle( "Update Appointment" );
  updateAppointmentStage.setScene( updateAppointmentScene );
  updateAppointmentStage.show( );
}

;

/**
 * Displays all scheduled appointments
 * @param actionEvent User click on 'All' <code>RadioButton</code>
 */
public void allViewRadioButtonListener( ActionEvent actionEvent )
{
  deleteAppointmentConfirmationLabel.setText("");
  // Clear the TableView
  viewAppointmentsTableView.getItems().clear();
  try {
    displayAppointments( );
  }
  catch ( SQLException | ParseException e ) {
    e.printStackTrace( );
  }
}

;

/**
 * Displays scheduled appointments in the current month.
 * A Lambda expression is used here to shorten the code and forego a return statement and declaring the type
 * 'Appointment'
 * @param actionEvent User click on 'Month' <code>RadioButton</code>
 */
public void monthViewRadioButtonListener( ActionEvent actionEvent )
{
  deleteAppointmentConfirmationLabel.setText("");
  // Check current month
  Month currentMonth = LocalDate.now().getMonth();
  int currentMonthInt = currentMonth.getValue();
  System.out.println( currentMonth + " : " + currentMonthInt);
  
  // Filter ObservableList clientAppointments for Appointments occurring this month
  try {
    // Create a new list containing all appointments
    ObservableList<Appointment> allAppointments = getAllAppointmentsForComparison();
    // Create a new list from the allAppointments list by filtering
    ObservableList<Appointment> appointmentsThisMonth =
        allAppointments.stream().filter( a -> a.getStartMonthInt() + 1 == currentMonthInt).collect( Collectors.toCollection(FXCollections::observableArrayList));
    // Clear the TableView
    viewAppointmentsTableView.getColumns().clear();
  
    // Create the columns for the TableView
    TableColumn<Appointment, Number> colAppointmentId = new TableColumn<>( "Appointment_ID" );
    TableColumn<Appointment, String> colTitle         = new TableColumn<>( "Title" );
    TableColumn<Appointment, String> colDescription   = new TableColumn<>( "Description" );
    TableColumn<Appointment, String> colLocation      = new TableColumn<>( "Location" );
    TableColumn<Appointment, String> colType          = new TableColumn<>( "Type" );
    TableColumn<Appointment, String> colStart         = new TableColumn<>( "Start" );
    TableColumn<Appointment, String> colEnd           = new TableColumn<>( "End" );
    TableColumn<Appointment, Number> colCustomerId    = new TableColumn<>( "Customer_ID" );
    TableColumn<Appointment, Number> colUserId        = new TableColumn<>( "User_ID" );
    TableColumn<Appointment, Number> colContactId     = new TableColumn<>( "Contact_ID" );
  
    // Set the cell values for each column
    colAppointmentId.setCellValueFactory( cell -> cell.getValue( ).appointmentIdProperty( ) );
    colTitle.setCellValueFactory( cell -> cell.getValue( ).titleProperty( ) );
    colDescription.setCellValueFactory( cell -> cell.getValue( ).descriptionProperty( ) );
    colLocation.setCellValueFactory( cell -> cell.getValue( ).locationProperty( ) );
    colType.setCellValueFactory( cell -> cell.getValue( ).typeProperty( ) );
    colStart.setCellValueFactory( cell -> cell.getValue( ).startProperty( ) );
    colEnd.setCellValueFactory( cell -> cell.getValue( ).endProperty( ) );
    colCustomerId.setCellValueFactory( cell -> cell.getValue( ).customerIdProperty( ) );
    colUserId.setCellValueFactory( cell -> cell.getValue( ).userIdProperty( ) );
    colContactId.setCellValueFactory( cell -> cell.getValue( ).contactIdProperty( ) );
  
    // Set the items in the TableView
    viewAppointmentsTableView.setItems( clientAppointments );
    viewAppointmentsTableView.getColumns( ).addAll( colAppointmentId, colTitle, colDescription, colLocation, colType,
        colStart, colEnd, colCustomerId, colUserId, colContactId );
    
    
    // Set the filtered items in the TableView
    viewAppointmentsTableView.setItems( appointmentsThisMonth );
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
}


/**
 * Displays scheduled appointments in the current week
 * A Lambda expression is used here to shorten the code and forego a return statement and declaring the type
 *  * 'Appointment'
 * @param actionEvent User click on 'Week' <code>RadioButton</code>
 */
public void weekViewRadioButtonListener( ActionEvent actionEvent )
{
  deleteAppointmentConfirmationLabel.setText("");
    // Create a Calendar Object and set the current Date on it
    Calendar      calendar    = Calendar.getInstance();
    // Get the current week number from the calendar
    int currentWeekInt = calendar.get(Calendar.WEEK_OF_YEAR);
    System.out.println( "Current week int: " + currentWeekInt );
  
    try {
      // Create a new list containing all appointments
      ObservableList<Appointment> allAppointments = getAllAppointmentsForComparison( );
      // Create a new list by filtering for appointments that match the current week
      ObservableList<Appointment> appointmentsThisWeek =
          allAppointments.stream().filter(a -> a.getStartWeek() == currentWeekInt).collect(Collectors.toCollection(FXCollections::observableArrayList));
      // Clear the TableView
      viewAppointmentsTableView.getColumns().clear();
  
      // Create the columns for the TableView
      TableColumn<Appointment, Number> colAppointmentId = new TableColumn<>( "Appointment_ID" );
      TableColumn<Appointment, String> colTitle         = new TableColumn<>( "Title" );
      TableColumn<Appointment, String> colDescription   = new TableColumn<>( "Description" );
      TableColumn<Appointment, String> colLocation      = new TableColumn<>( "Location" );
      TableColumn<Appointment, String> colType          = new TableColumn<>( "Type" );
      TableColumn<Appointment, String> colStart         = new TableColumn<>( "Start" );
      TableColumn<Appointment, String> colEnd           = new TableColumn<>( "End" );
      TableColumn<Appointment, Number> colCustomerId    = new TableColumn<>( "Customer_ID" );
      TableColumn<Appointment, Number> colUserId        = new TableColumn<>( "User_ID" );
      TableColumn<Appointment, Number> colContactId     = new TableColumn<>( "Contact_ID" );
  
      // Set the cell values for each column
      colAppointmentId.setCellValueFactory( cell -> cell.getValue( ).appointmentIdProperty( ) );
      colTitle.setCellValueFactory( cell -> cell.getValue( ).titleProperty( ) );
      colDescription.setCellValueFactory( cell -> cell.getValue( ).descriptionProperty( ) );
      colLocation.setCellValueFactory( cell -> cell.getValue( ).locationProperty( ) );
      colType.setCellValueFactory( cell -> cell.getValue( ).typeProperty( ) );
      colStart.setCellValueFactory( cell -> cell.getValue( ).startProperty( ) );
      colEnd.setCellValueFactory( cell -> cell.getValue( ).endProperty( ) );
      colCustomerId.setCellValueFactory( cell -> cell.getValue( ).customerIdProperty( ) );
      colUserId.setCellValueFactory( cell -> cell.getValue( ).userIdProperty( ) );
      colContactId.setCellValueFactory( cell -> cell.getValue( ).contactIdProperty( ) );
  
      // Set the items in the TableView
      viewAppointmentsTableView.setItems( clientAppointments );
      viewAppointmentsTableView.getColumns( ).addAll( colAppointmentId, colTitle, colDescription, colLocation, colType,
          colStart, colEnd, colCustomerId, colUserId, colContactId );
      
      // Set the filtered items in the TableView
      viewAppointmentsTableView.setItems(appointmentsThisWeek);
    } catch (SQLException e){
      e.printStackTrace();
    }
}

;

}
