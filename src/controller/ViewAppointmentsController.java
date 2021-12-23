package controller;

import databaseAccess.DBAppointments;
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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Appointment;
import scheduler.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class ViewAppointmentsController implements Initializable
{

/**
 * Fields
 */
public static ObservableList<Appointment> clientAppointments = FXCollections.observableArrayList();


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

/**
 * Methods
 */


/**
 * Initializes the View Appointments window
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle )
{
  System.out.println("View Appointments Opening");
  try
  {
    displayAppointments();
  }
  catch (SQLException e)
  {
    e.printStackTrace();
  }
}

/**
 * Obtains the stored <code>Appointment</code> records and displays them within a <code>TableView</code>
 * @throws SQLException Thrown if the SQL is malformed
 */
public void displayAppointments() throws SQLException
{
  System.out.println("Display appointment records.");
  // Obtain all Appointments from the database
  clientAppointments = DBAppointments.getAllAppointments();
  Main.currentAppointmentId = clientAppointments.size();
  
  // Create the columns for the TableView
  TableColumn<Appointment, Number> colAppointmentId   = new TableColumn<>( "Appointment_ID" );
  TableColumn<Appointment, String>  colTitle = new TableColumn<>( "Title" );
  TableColumn<Appointment, String>  colDescription      = new TableColumn<>( "Description" );
  TableColumn<Appointment, String>  colLocation   = new TableColumn<>( "Location" );
  TableColumn<Appointment, String>  colType     = new TableColumn<>( "Type" );
  TableColumn<Appointment, Timestamp> colStart      = new TableColumn<>( "Start" );
  TableColumn<Appointment, Timestamp> colEnd        = new TableColumn<>( "End" );
  TableColumn<Appointment, Number>    colCustomerId = new TableColumn<>( "Customer_ID" );
  TableColumn<Appointment, Number>  colUserId        = new TableColumn<>( "User_ID" );
  TableColumn<Appointment, Number>  colContactId        = new TableColumn<>( "Contact_ID" );
  
  // Set the cell values for each column
  colAppointmentId.setCellValueFactory(cell -> cell.getValue().appointmentIdProperty());
  colTitle.setCellValueFactory( cell -> cell.getValue().titleProperty() );
  colDescription.setCellValueFactory( cell -> cell.getValue().descriptionProperty() );
  colLocation.setCellValueFactory( cell -> cell.getValue().locationProperty() );
  colType.setCellValueFactory(cell -> cell.getValue().typeProperty() );
  colStart.setCellValueFactory(cell -> cell.getValue().startProperty() );
  colEnd.setCellValueFactory(cell -> cell.getValue().endProperty() );
  colCustomerId.setCellValueFactory( cell -> cell.getValue().customerIdProperty() );
  colUserId.setCellValueFactory( cell -> cell.getValue().userIdProperty() );
  colContactId.setCellValueFactory( cell -> cell.getValue().contactIdProperty() );
  
  // Set the items in the TableView
  viewAppointmentsTableView.setItems(clientAppointments);
  viewAppointmentsTableView.getColumns().addAll(colAppointmentId, colTitle, colDescription, colLocation, colType,
      colStart, colEnd, colCustomerId, colUserId, colContactId);
  
}

/**
 * Switches the window to show all Customer Records
 * @param actionEvent User click on the button
 * @throws IOException Thrown if there is an error in finding the FXML file
 */
public void viewCustomersButtonListener( ActionEvent actionEvent) throws IOException
{
  // Open the ViewCustomers scene
  Parent viewCustomersFXML = FXMLLoader.load(getClass().getResource("../views/viewCustomerRecords.fxml"));
  Scene viewCustomersScene = new Scene(viewCustomersFXML, 975, 400);
  Stage viewCustomersStage = new Stage();
  viewCustomersStage.setTitle("View Customers");
  viewCustomersStage.setScene(viewCustomersScene);
  viewCustomersStage.show();
  
  // Close out the current scene
  Stage viewStage = (Stage) viewCustomersButton.getScene().getWindow();
  viewStage.close();
}



/**
 * Handles click of 'Exit' button. Closes the database connection and exits the application.
 * @param actionEvent User click on the button
 */
public void exitButtonListener( ActionEvent actionEvent)
{
  // Close out the application
  JDBC.closeConnection( );
  System.out.println( "Exit Clicked!" );
  Platform.exit();
};


public void deleteButtonListener( ActionEvent actionEvent){
//  // Get the customerId from the selected customer
//  Customer deleteSelection = viewAppointmentsTableView.getSelectionModel( ).getSelectedItem( );
//  int      appointmentId    = deleteSelection.getAppointmentId();
//  String   customerName    = deleteSelection.getCustomerName( );
//
//  Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
//  alert.setTitle( "Title" );
//  String s = "Confirm to clear text in text field !";
//  alert.setContentText( s );
//
//  Optional<ButtonType> result = alert.showAndWait( );
//
//  if ( ( result.isPresent( ) ) && ( result.get( ) == ButtonType.OK ) ) {
//
//    System.out.println( "Ok clicked!" );
//    // Connect to the database
////    JDBC.makeConnection( );
////    DBCountries.checkDateConversion( );
//    Connection connection = JDBC.getConnection( );
//
//    // Use SQL query to delete the customer at that ID
//    String deleteStatement = "DELETE FROM client_schedule.customers WHERE Customer_ID = ?";
//
//    // Prepare the statement
//    DBQuery.setPreparedStatement( connection, deleteStatement );
//    PreparedStatement preparedStatement = DBQuery.getPreparedStatement( );
//
//    // Execute the statement
//    preparedStatement.setString( 1, String.valueOf( customerId ) );
//    preparedStatement.execute( );
//
//    // Remove from ObservableList to update TableView
//    customerRecords.remove( deleteSelection );
//
////     Display Delete Confirmation
//    deleteConfirmationLabel.setText( "Customer " + deleteSelection.getCustomerName( ) + " has been deleted!" );
//  }
//
//  if ( ( result.isPresent( ) ) && ( result.get( ) == ButtonType.NO ) ) {
//    System.out.println( "Cancel clicked!" );
//  }
};

public void addButtonListener( ActionEvent actionEvent){
  
  try {
    Parent addAppointmentFXML = FXMLLoader.load(getClass().getResource("../views/addAppointment.fxml"));
    Stage addAppointmentStage = new Stage();
    Scene addAppointmentScene = new Scene(addAppointmentFXML, 975, 400);
    addAppointmentStage.setTitle("Add Appointment");
    addAppointmentStage.setScene(addAppointmentScene);
    addAppointmentStage.show();
  }
  catch ( IOException e ) {
    e.printStackTrace( );
  }
  
};

public void updateButtonListener( ActionEvent actionEvent){
  
  Main.selectedAppointment = viewAppointmentsTableView.getSelectionModel( ).getSelectedItem( );
  System.out.println("Selected Appointment: " + Main.selectedAppointment);
  
  Parent updateAppointmentFXML = null;
  try {
    updateAppointmentFXML = FXMLLoader.load(getClass().getResource("../views/updateAppointment.fxml"));
  }
  catch ( IOException e ) {
    e.printStackTrace( );
  }
  Stage updateAppointmentStage = new Stage();
  Scene updateAppointmentScene = new Scene(updateAppointmentFXML, 975, 400);
  updateAppointmentStage.setTitle("Update Appointment");
  updateAppointmentStage.setScene(updateAppointmentScene);
  updateAppointmentStage.show();
};


public void monthViewRadioButtonListener(ActionEvent actionEvent){};

public void weekViewRadioButtonListener(ActionEvent actionEvent){};


}
