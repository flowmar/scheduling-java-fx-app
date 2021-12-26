package controller;

import databaseAccess.DBCustomerRecords;
import databaseAccess.DBQuery;
import databaseAccess.JDBC;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Customer;
import scheduler.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static scheduler.Main.selectedCustomer;

public class ViewCustomerRecordsController implements Initializable {

/**
 * Fields
 */
public static ObservableList<Customer> customerRecords;

@FXML
public TableView<Customer> customerRecordsTableView;
@FXML
private TableColumn<Customer, Integer> colCustomerID;
@FXML
private TableColumn<Customer, String> colCustomerName;
@FXML
private TableColumn<Customer, String> colAddress;
@FXML
private TableColumn<Customer, String> colPostalCode;
@FXML
private TableColumn<Customer, String> colDivision;
@FXML
private TableColumn<Customer, String> colCountry;
@FXML
private TableColumn<Customer, String> colPhone;
@FXML
private Button exitButton;
@FXML
private Button deleteButton;
@FXML
private Button viewAppointmentsButton;
@FXML
private Button updateButton;
@FXML
private Button addButton;
@FXML
private Label deleteConfirmationLabel;

/**
 * Methods
 */

/**
 * Initializes the View Customer Records Menu
 *
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle ) {
  System.out.println( "View Customer Records Initialize" );
  try {
    displayCustomerRecords( );
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }

}

/**
 * Obtains the stored customer records from the database and displays it within the <code>TableView</code>
 * @throws SQLException Thrown if SQL is malformed
 */
public void displayCustomerRecords( ) throws SQLException {
  System.out.println( "Display customer records." );
  // Obtain all customer records from the database
  customerRecords = DBCustomerRecords.getAllCustomerRecords( );
  Main.currentId  = customerRecords.size( );
  
  // Create the columns for the TableView
  TableColumn<Customer, Integer> colCustomerID   = new TableColumn<>( "ID" );
  TableColumn<Customer, String>  colCustomerName = new TableColumn<>( "Name" );
  TableColumn<Customer, String>  colAddress      = new TableColumn<>( "Address" );
  TableColumn<Customer, String>  colPostalCode   = new TableColumn<>( "Postal Code" );
  TableColumn<Customer, String>  colDivision     = new TableColumn<>( "Division" );
  TableColumn<Customer, String>  colCountry      = new TableColumn<>( "Country" );
  TableColumn<Customer, String>  colPhone        = new TableColumn<>( "Phone" );
  
  // Set the cell values for each column
  colCustomerID.setCellValueFactory( new PropertyValueFactory<Customer, Integer>( "customerId" ) );
  colCustomerName.setCellValueFactory( new PropertyValueFactory<Customer, String>( "customerName" ) );
  colAddress.setCellValueFactory( new PropertyValueFactory<Customer, String>( "address" ) );
  colPostalCode.setCellValueFactory( new PropertyValueFactory<Customer, String>( "postalCode" ) );
  colDivision.setCellValueFactory( new PropertyValueFactory<Customer, String>( "division" ) );
  colCountry.setCellValueFactory( new PropertyValueFactory<Customer, String>( "country" ) );
  colPhone.setCellValueFactory( new PropertyValueFactory<Customer, String>( "phoneNumber" ) );
  
  customerRecordsTableView.setItems( customerRecords );
  // Add the columns to the TableView
  customerRecordsTableView.getColumns( ).addAll( colCustomerID, colCustomerName, colAddress, colPostalCode, colDivision
      , colCountry, colPhone );
//  customerRecordsTableView.getItems().addAll(customerRecordsTableView.getItems());

}

/**
 * Handles click of 'Exit' button. Closes the database connection and exits the application.
 *
 * @param actionEvent A user click on the button
 */
public void exitButtonListener( ActionEvent actionEvent ) {
  JDBC.closeConnection( );
  System.out.println( "Exit Clicked!" );
  Platform.exit( );
}

/**
 * Handles click of 'Exit' button. Closes the database connection and exits the application.
 *
 * @param actionEvent A user click on the button
 */
public void deleteButtonListener( ActionEvent actionEvent ) throws IOException, SQLException {
  // Get the customerId from the selected customer
  Customer deleteSelection = customerRecordsTableView.getSelectionModel( ).getSelectedItem( );
  int      customerId      = deleteSelection.getCustomerId( );
  String   customerName    = deleteSelection.getCustomerName( );
  
  Alert alert = new Alert( Alert.AlertType.CONFIRMATION );
  alert.setTitle( "Delete Customer Confirmation" );
  String deleteConfirmationText = "Are you sure you want to delete customer " + customerName + "?";
  alert.setContentText( deleteConfirmationText );
  
  Optional<ButtonType> answer = alert.showAndWait( );
  
  if ( ( answer.isPresent( ) ) && ( answer.get( ) == ButtonType.OK ) ) {
    
    System.out.println( "Ok clicked!" );
    // Connect to the database
//    JDBC.makeConnection( );
//    DBCountries.checkDateConversion( );
    Connection connection = JDBC.getConnection( );
    
    // Use SQL query to delete the customer at that ID
    String deleteStatement = "DELETE FROM client_schedule.customers WHERE Customer_ID = ?";
    
    // Prepare the statement
    DBQuery.setPreparedStatement( connection, deleteStatement );
    PreparedStatement preparedStatement = DBQuery.getPreparedStatement( );
    
    // Execute the statement
    preparedStatement.setString( 1, String.valueOf( customerId ) );
    preparedStatement.execute( );
    
    // Remove from ObservableList to update TableView
    customerRecords.remove( deleteSelection );

//     Display Delete Confirmation
    deleteConfirmationLabel.setText( "Customer " + deleteSelection.getCustomerName( ) + " has been deleted!" );
  }
  
  if ( ( answer.isPresent( ) ) && ( answer.get( ) == ButtonType.NO ) ) {
    System.out.println( "Cancel clicked!" );
  }

//  Dialog<String> dialog = new Dialog<String>();
//
//  dialog.setTitle("Delete Customer?");
//
//  ButtonType okType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
//
//  ButtonType cancelType = new ButtonType( "Cancel", ButtonBar.ButtonData.NO );
//
//  dialog.getDialogPane().getButtonTypes().add(okType);
//  dialog.getDialogPane().getButtonTypes().add(cancelType);
//
//  deleteButton.setOnAction( e -> {
//    dialog.showAndWait();
//  } );
//
//  Text text = new Text("Click the button to show the dialog.");
//
//  Button button = new Button("Show Dialog");
//  button.se
//
//  HBox pane = new HBox(15);
//
//  //Setting the space between the nodes of a HBox pane
//  pane.setPadding(new Insets(50, 150, 50, 60));
//  pane.getChildren().addAll(text, button);
//
//  Stage stage = new Stage();
//  //Creating a scene object
//  Scene scene = new Scene(new Group(pane), 595, 250, Color.BEIGE);
//  stage.setTitle("Dialog");
//  stage.setScene(scene);
//  stage.show();
//  try {
//    // Load the deleteCustomerConfirmation FXML
//    Parent deleteCustomerFXML = FXMLLoader.load(getClass( ).getResource( "../views/deleteCustomerConfirmation.fxml"
//    ) );
//    // Create the new Stage and Scene and display them
//    Scene deleteConfirmationScene = new Scene( deleteCustomerFXML, 700, 300 );
//    Stage deleteConfirmationStage = new Stage( );
//    deleteConfirmationStage.setTitle( "Delete Confirmation" );
//    deleteConfirmationStage.setScene( deleteConfirmationScene );
//    deleteConfirmationStage.show( );
//  }
//  catch (Exception e) {
//    e.printStackTrace();
//  }
//  Dialog<String> dialog = new Dialog<String>();
//
//  dialog.setTitle("Delete Confirmation");
//
//  ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE  );
//
//  dialog.setContentText("Ok to delete?");
//
//  dialog.getDialogPane().getButtonTypes().add(type);
//
//  Text text = new Text("Click the button to show the dialog.");
//
//  Button button = new Button("Show Dialog");
//
//  button.setOnAction( e -> {
//    dialog.showAndWait();
//  } );
//
//  HBox pane = new HBox(15);
//
//  //Setting the space between the nodes of a HBox pane
//  pane.setPadding(new Insets(50, 150, 50, 60));
//  pane.getChildren().addAll(text, button);
//
//  Stage stage = new Stage();
//  //Creating a scene object
//  Scene scene = new Scene(new Group(pane), 595, 250, Color.BEIGE);
//  stage.setTitle("Dialog");
//  stage.setScene(scene);
//  stage.show();
}

/**
 * Handles click of 'Add' button.
 *
 * @param actionEvent A user click on the button
 * @throws IOException Throws an exception if there is a problem reading or finding the FXML file.
 */
public void addButtonListener( ActionEvent actionEvent ) throws IOException {
  // Load the Add Customer FXML document
  Parent addCustomerFXML = null;
  try {
    addCustomerFXML = FXMLLoader.load( getClass( ).getResource( "../views/addCustomer.fxml" ) );
  }
  catch ( IOException e ) {
    e.printStackTrace( );
  }
  // Create the new Stage and Scene and display it
  try {
    Scene addCustomerScene = new Scene( addCustomerFXML, 975, 400 );
    Stage addCustomerStage = new Stage( );
    addCustomerStage.setTitle( "Add Customer" );
    addCustomerStage.setScene( addCustomerScene );
    addCustomerStage.show( );
  }
  catch ( Exception e ) {
    e.printStackTrace( );
  }
}

/**
 * Handles click of 'Update' button.
 *
 * @param actionEvent A user click on the button
 */
public void updateButtonListener( ActionEvent actionEvent ) {
  
  // Get the customer that is selected in the TableView
  selectedCustomer = customerRecordsTableView.getSelectionModel( ).getSelectedItem( );
  System.out.println( selectedCustomer );
  
  
  // Load the FXML document
  Parent updateCustomerFXML = null;
  try {
    updateCustomerFXML = FXMLLoader.load( getClass( ).getResource( "../views/updateCustomer.fxml" ) );
  }
  catch ( IOException e ) {
    e.printStackTrace( );
  }
  // Create a new Scene and Stage and launch them
  Scene updateCustomerScene = new Scene( updateCustomerFXML, 975, 400 );
  Stage updateCustomerStage = new Stage( );
  updateCustomerStage.setTitle( "Update Customer" );
  updateCustomerStage.setScene( updateCustomerScene );
  updateCustomerStage.show( );
  
}

/**
 * Handles click of 'View Appointments' button. Opens the 'View Appointments' window
 * @param actionEvent A user click on the button
 */
public void viewAppointmentsButtonListener( ActionEvent actionEvent ) throws IOException
{
  // Load the View Appointments FXML
  Parent viewAppointmentsFXML = FXMLLoader.load(getClass().getResource( "../views/viewAppointmentsAll.fxml" ));
  // Create the new stage and scene
  Scene viewAppointmentsScene = new Scene(viewAppointmentsFXML, 975, 400);
  Stage viewAppointmentsStage = new Stage();
  viewAppointmentsStage.setTitle( "View Appointments");
  viewAppointmentsStage.setScene(viewAppointmentsScene);
  viewAppointmentsStage.show();
  
  // Close out the ViewCustomerRecords stage
  Stage customerStage = (Stage) viewAppointmentsButton.getScene().getWindow();
  customerStage.close();
  
}

}