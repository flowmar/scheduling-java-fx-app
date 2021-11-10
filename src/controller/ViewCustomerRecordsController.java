package controller;

import databaseAccess.DBCustomerRecords;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tools.JDBC;

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
 * Handles click of 'Exit' button. Closes the database connection and exits the application.
 * @param actionEvent A user click on the button
 */
public void addButtonListener(ActionEvent actionEvent)
{
  JDBC.closeConnection();
  System.out.println("Exit Clicked!");
  Platform.exit();
}

/**
 * Handles click of 'Exit' button. Closes the database connection and exits the application.
 * @param actionEvent A user click on the button
 */
public void updateButtonListener(ActionEvent actionEvent)
{
  JDBC.closeConnection();
  System.out.println("Exit Clicked!");
  Platform.exit();
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