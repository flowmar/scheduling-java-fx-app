package controller;

import databaseAccess.DBCustomerRecords;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Customer;
import tools.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewCustomerRecordsController implements Initializable {

/**
 * Fields
 */

@FXML
private TableView<Customer> customerRecordsTableView;

@FXML
private TableColumn colCustomerID;

@FXML
private TableColumn colCustomerName;

@FXML
private TableColumn colAddress;

@FXML
private TableColumn colPostalCode;

@FXML
private TableColumn colDivision;

@FXML
private TableColumn colCountry;

@FXML
private TableColumn colPhone;

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

public static ObservableList<Customer> customerRecords;
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
public void initialize( URL url, ResourceBundle resourceBundle )
{
  System.out.println( "View Customer Records Initialize" );
  
  // Obtain all customer records from the database
  try
  {
    customerRecords = FXCollections.observableArrayList( DBCustomerRecords.getAllCustomerRecords());
  }
  catch (SQLException err)
  {
    err.printStackTrace();
  }
  
  // Create the columns for the TableView
  TableColumn<Customer, Integer> colCustomerID = new TableColumn<>("ID");
  TableColumn<Customer, String> colCustomerName = new TableColumn<>("Name");
  TableColumn<Customer, String> colAddress = new TableColumn<>("Address");
  TableColumn<Customer, String> colPostalCode = new TableColumn<>("Postal Code");
  TableColumn<Customer, String> colDivision = new TableColumn<>("Division");
  TableColumn<Customer, String> colCountry = new TableColumn<>("Country");
  TableColumn<Customer, String> colPhone = new TableColumn<>("Phone");
  
  // Set the cell values for each column
  colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
  colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
  colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
  colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
  colDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
  colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
  colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
  
  //
  customerRecordsTableView.setItems(customerRecords);
  // Add the columns to the TableView
  customerRecordsTableView.getColumns().setAll(colCustomerID, colCustomerName, colAddress, colPostalCode, colDivision
      , colCountry, colPhone);
}

/**
 * Handles click of 'Exit' button. Closes the database connection and exits the application.
 * @param actionEvent A user click on the button
 */
public void exitButtonListener(ActionEvent actionEvent)
{
  JDBC.closeConnection();
  System.out.println("Exit Clicked!");
  Platform.exit();
}

/**
 * Handles click of 'Exit' button. Closes the database connection and exits the application.
 * @param actionEvent A user click on the button
 */
public void deleteButtonListener(ActionEvent actionEvent)
{
  JDBC.closeConnection();
  System.out.println("Exit Clicked!");
  Platform.exit();
}

/**
 * Handles click of 'Add' button.
 * @param actionEvent A user click on the button
 * @throws IOException Throws an exception if there is a problem reading or finding the FXML file.
 */
public void addButtonListener(ActionEvent actionEvent) throws IOException
{
  Parent addCustomerFXML = null;
  try {
    addCustomerFXML = FXMLLoader.load(getClass().getResource("../view/addCustomer.fxml"));
  }
  catch ( IOException e ) {
    e.printStackTrace( );
  }
  
  try
  {
    Scene addCustomerScene = new Scene( addCustomerFXML, 975, 400 );
  
    Stage addCustomerStage = new Stage( );
    addCustomerStage.setTitle( "Add Customer" );
    addCustomerStage.setScene( addCustomerScene );
  
    addCustomerStage.show( );
  }
  catch ( Exception e)
  {
    e.printStackTrace();
  }
}

/**
 * Handles click of 'Update' button.
 * @param actionEvent A user click on the button
 */
public void updateButtonListener(ActionEvent actionEvent)
{
  Parent updateCustomerFXML = null;
  try {
    updateCustomerFXML = FXMLLoader.load(getClass().getResource("../view/updateCustomer.fxml"));
  }
  catch ( IOException e ) {
    e.printStackTrace( );
  }
  
  Scene updateCustomerScene = new Scene(updateCustomerFXML, 975, 400);
  
  Stage updateCustomerStage = new Stage();
  updateCustomerStage.setTitle("Add Customer");
  updateCustomerStage.setScene(updateCustomerScene);
  
  updateCustomerStage.show();
}

/**
 * Handles click of 'Exit' button. Closes the database connection and exits the application.
 * @param actionEvent A user click on the button
 */
public void viewAppointmentsButtonListener(ActionEvent actionEvent)
{
  JDBC.closeConnection();
  System.out.println("Exit Clicked!");
  Platform.exit();
}

/**
 * Obtains the stored customer records from the database and displays it within the TableView
 */
public void displayCustomerRecords( ) throws SQLException
{
  System.out.println( "Display customer records." );
  // Get the data from the database and display it in the TableView
  
  
}

}