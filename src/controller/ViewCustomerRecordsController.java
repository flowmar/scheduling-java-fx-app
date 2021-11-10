package controller;

import databaseAccess.DBCustomerRecords;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
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
private TableView customerRecordsTableView;

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
  try {
    DBCustomerRecords.getAllCustomerRecords();
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
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
  
  Scene addCustomerScene = new Scene(addCustomerFXML, 975, 400);
  
  Stage addCustomerStage = new Stage();
  addCustomerStage.setTitle("Add Customer");
  addCustomerStage.setScene(addCustomerScene);
  
  addCustomerStage.show();
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