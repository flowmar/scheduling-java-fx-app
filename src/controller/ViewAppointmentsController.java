package controller;

import databaseAccess.JDBC;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Appointment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewAppointmentsController implements Initializable
{

@FXML
private TableView<Appointment> appointmentsTableView;

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
 * Initializes the View Appointments window
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle )
{
  System.out.println("View Appointments Opening");
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


public void deleteButtonListener( ActionEvent actionEvent){};

public void updateButtonListener( ActionEvent actionEvent){};

public void addButtonListener( ActionEvent actionEvent){};

public void monthViewRadioButtonListener( ActionEvent actionEvent){};

public void weekViewRadioButtonListener(ActionEvent actionEvent){};
}
