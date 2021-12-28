package controller;

import databaseAccess.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static databaseAccess.DBAppointments.getAllAppointmentsForComparison;

public class ViewReportsController implements Initializable {

/**
 * Fields
 */
@FXML
private Button appointmentsReportButton;

@FXML
private Button schedulesReportButton;

@FXML
private Button usersReportButton;

@FXML
private Button viewAppointmentsButton;

@FXML
private Button viewCustomersButton;

@FXML
private Button exitButton;

@FXML
private ComboBox<Month> monthComboBox;

@FXML
private ComboBox<String> appointmentTypeComboBox;

@FXML
private ComboBox<String> contactComboBox;

@FXML
private ComboBox<String> usersComboBox;

@FXML
private ListView<String> appointmentsListView;

@FXML
private ListView<String> schedulesListView;

@FXML
private ListView<String> usersListView;

@FXML
private Label totalsLabel;

private Integer monthChoice = null;

private String typeChoice = null;

private Integer contactChoice = null;

private Integer userChoice = null;

public ViewReportsController( ) { }


/**
 * Methods
 */

/**
 * Initializes the Reports Menu
 *
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle ) {
  // Populate all comboBoxes
  populateContactComboBox();
  populateUsersComboBox();
  populateAppointmentTypeComboBox();
  populateMonthComboBox();
 
  
}

/**
 * Hides all controls except those associated with the Appointments Report
 */
public void appointmentsReportButtonListener( ) {
  hideSchedulesReportControls( );
  hideUsersReportControls( );
  appointmentTypeComboBox.setDisable( false );
  appointmentTypeComboBox.setVisible( true );
  monthComboBox.setDisable( false );
  monthComboBox.setVisible( true );
  appointmentsListView.setDisable( false );
  appointmentsListView.setVisible( true );
}

;

/**
 * Hides all controls except those associated with the Schedules Report
 */
public void schedulesReportButtonListener( ) {
  hideAppointmentsReportControls( );
  hideUsersReportControls( );
  contactComboBox.setDisable( false );
  contactComboBox.setVisible( true );
  schedulesListView.setDisable( false );
  schedulesListView.setVisible( true );
  
}

;

/**
 * Hides all controls except those associated with the Users Report
 */
public void usersReportButtonListener( ) {
  hideAppointmentsReportControls( );
  hideSchedulesReportControls( );
  usersComboBox.setDisable( false );
  usersComboBox.setVisible( true );
  usersListView.setDisable( false );
  usersListView.setVisible( true );
}

;

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
 * Handles click of 'View Appointments' button. Opens the 'View Appointments' window
 *
 * @param actionEvent A user click on the button
 */
public void viewAppointmentsButtonListener( ActionEvent actionEvent ) throws IOException {
  // Load the View Appointments FXML
  Parent viewAppointmentsFXML = FXMLLoader.load( getClass( ).getResource( "../views/viewAppointmentsAll.fxml" ) );
  // Create the new stage and scene
  Scene viewAppointmentsScene = new Scene( viewAppointmentsFXML, 975, 400 );
  Stage viewAppointmentsStage = new Stage( );
  viewAppointmentsStage.setTitle( "View Appointments" );
  viewAppointmentsStage.setScene( viewAppointmentsScene );
  viewAppointmentsStage.show( );
  
  // Close out the ViewCustomerRecords stage
//  Stage customerStage = ( Stage ) viewAppointmentsButton.getScene( ).getWindow( );
//  customerStage.close( );

}

/**
 * Handles click of the 'Exit' <code>Button</code>
 */
public void exitButtonListener() {
  Stage stage = (Stage) exitButton.getScene().getWindow();
  stage.close();
}

;

/**
 * Hides and disables the controls for the Appointments Report
 */
public void hideAppointmentsReportControls( ) {
  appointmentTypeComboBox.setVisible( false );
  appointmentTypeComboBox.setDisable( true );
  appointmentsListView.setVisible( false );
  appointmentsListView.setDisable( true );
  monthComboBox.setVisible( false );
  monthComboBox.setDisable( true );
  totalsLabel.setText("");
}

;

/**
 * Hides and disables the controls for the Schedules Report
 */
public void hideSchedulesReportControls( ) {
  contactComboBox.setVisible( false );
  contactComboBox.setDisable( true );
  schedulesListView.setVisible( false );
  schedulesListView.setDisable( true );
}

;

/**
 * Hides and disables the controls for the Users Report
 */
public void hideUsersReportControls( ) {
  usersComboBox.setVisible( false );
  usersComboBox.setDisable( true );
  usersListView.setVisible( false );
  usersListView.setDisable( true );
  totalsLabel.setText("");
}

;

public void populateMonthComboBox( ) {
  Month[] monthsEnumValues = Month.values();
  ObservableList<Month> months = FXCollections.observableArrayList(monthsEnumValues);
  monthComboBox.setItems( months );
}

;

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
 * Populates the <code>contactComboBox</code> with data from the database
 */
public void populateContactComboBox( ) {
  // Get all contacts from the database
  ObservableList<String> contactsList = DBAppointments.getContacts( );
  // Populate the contactComboBox
  contactComboBox.setItems( contactsList );
}

/**
 * Populates the <code>userIdComboBox</code> with the <code>User</code>s from the database
 */
public void populateUsersComboBox( ) {
  ObservableList<String> userIdList = DBAppointments.getUsers( );
  usersComboBox.setItems( userIdList );
}

/**
 * Listens for changes to the monthComboBox
 */
public void monthComboBoxListener(){
    // Get the value of the monthComboBox as an int
    Month monthComboBoxValue = monthComboBox.getValue();
    
    int monthComboBoxInt = monthComboBoxValue.getValue();
    
    monthChoice = monthComboBoxInt;
  
  if (typeChoice != null && monthChoice != null) {
    createAndDisplayAppointmentReport();
  }
    
    System.out.println( monthComboBoxInt );
}

/**
 * Listens for changes to the appointmentTypeComboBox
 */
public void appointmentTypeComboBoxListener( ) {
    String appointmentTypeComboBoxValue = appointmentTypeComboBox.getValue();
    
    typeChoice = appointmentTypeComboBoxValue;
    if (typeChoice != null && monthChoice != null) {
      createAndDisplayAppointmentReport();
    }
}

/**
 * Creates and displays the Appointments Report
 */
public void createAndDisplayAppointmentReport(){
  
  try {
    // Get all appointments
    ObservableList<Appointment> allAppointments = getAllAppointmentsForComparison();
    // Filter the allAppointments list. First, by the chosen month,
    // Then, filter the list further by the chosen type
    ObservableList<Appointment> filteredList =
        allAppointments.stream().filter(a -> a.getStartMonthInt() + 1 == monthChoice).filter(a -> a.getType().equals( typeChoice )).collect( Collectors.toCollection( FXCollections::observableArrayList ));
    
    System.out.println( "Filtered List: " + filteredList);
    System.out.println( "monthChoice: " + monthChoice);
    System.out.println("typeChoice: " + typeChoice);
    // Create a new list to hold the appointment information
    ObservableList<String> filteredAppointments = FXCollections.observableArrayList();
    String apptInfo = "";
    // Take the filtered list and extract the appointment ID for each appointment
    filteredList.forEach(a -> filteredAppointments.add( apptInfo.concat( "ID: " + a.getAppointmentId() + System.lineSeparator( ))));
    // Set the items in the ListView
    appointmentsListView.setItems(filteredAppointments);
    // Update the message under the ListView
    totalsLabel.setText( "Appointments Matching Criteria: " + String.valueOf( filteredAppointments.size()));
    
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  
}

/**
 * Listens for changes to the contactTypeComboBox
 */
public void contactComboBoxListener( ) {
  String contactComboBoxValue = contactComboBox.getValue();
  
  contactChoice = Integer.parseInt(contactComboBoxValue.substring(0, 1));
  System.out.println("Contact Choice: " + contactChoice);
  
  createAndDisplayScheduleReport();
}

/**
 * Creates and displays the schedule report
 */
public void createAndDisplayScheduleReport() {
  try {
    ObservableList<Appointment> allAppointments = getAllAppointmentsForComparison();
    ObservableList<Appointment> filteredAppointments =
        allAppointments.stream().filter(a -> a.getContactId() == contactChoice).collect( Collectors.toCollection( FXCollections::observableArrayList ));
    ObservableList<String> appointmentInfo = FXCollections.observableArrayList();
    String appointmentInfoString = "";
    filteredAppointments.forEach(a -> appointmentInfo.add(appointmentInfoString.concat("Appointment ID: " + a.getAppointmentId() + System.lineSeparator() + " | Title: " + a.getTitle() + System.lineSeparator() + " | Type: " + a.getType() + System.lineSeparator() + " | Description: " + a.getDescription() + System.lineSeparator() + " | Start: " + a.getStart() + System.lineSeparator() + " | End: " + a.getEnd() + System.lineSeparator() + " | Customer ID: " + a.getCustomerId() + System.lineSeparator( ))));
    
    System.out.println("all appointments: " + allAppointments);
    schedulesListView.setItems(appointmentInfo);
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
}

/**
 * Listens for changes to the <code>usersComboBox</code>
 */
public void usersComboBoxListener() {
    String userComboBoxValue = usersComboBox.getValue( );
    
    userChoice = Integer.parseInt(userComboBoxValue.substring(0, 1) );
    System.out.println("User Choice: " + userChoice);
    
    createAndDisplayUserReport();
}

/**
 * Creates and displays the User Report. This report shows a list of the Appointments that the selected User has
 * entered into the system. It also gives the total number of Appointments the user has scheduled.
 */
public void createAndDisplayUserReport(){
    try {
      ObservableList<Appointment> allAppointments = getAllAppointmentsForComparison();
      ObservableList<Appointment> filteredAppointments =
          allAppointments.stream().filter(a -> a.getUserId() == userChoice).collect(Collectors.toCollection( FXCollections::observableArrayList ));
      ObservableList<String> appointmentInfo = FXCollections.observableArrayList();
      String appointmentInfoString = "";
      filteredAppointments.forEach(a -> appointmentInfo.add(appointmentInfoString.concat("Appointment ID: " + a.getAppointmentId() + System.lineSeparator( ) + " Appointment Type: " + a.getType())));
      
      usersListView.setItems( appointmentInfo );
      totalsLabel.setText("Appointments set by user: " + filteredAppointments.size());
      
    }
    catch ( SQLException e){
      e.printStackTrace();
    }
}

}
