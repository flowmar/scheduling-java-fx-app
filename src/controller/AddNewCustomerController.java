package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddNewCustomerController {

/**
 * Fields
 */

@FXML
private Button cancelButton;

@FXML
private Button saveButton;

@FXML
private TextField customerIDTextField;

@FXML
private TextField customerNameTextField;

@FXML
private TextField addressTextField;

@FXML
private TextField postalCodeTextField;

@FXML
private TextField phoneNumberTextField;

/**
 * Methods
 */

/**
 * Closes out the 'Add Customer' window
 * @param actionEvent User click on the 'Cancel' button
 */
public void cancelButtonListener( ActionEvent actionEvent)
{
  Stage stage = (Stage) cancelButton.getScene().getWindow();
  stage.close();
}

/**
 * Saves the information from the Text Fields into the database as a new customer.
 * @param actionEvent User click on the 'Save' button
 * @throws SQLException Throws an exception if SQL is malformed.
 */
public void saveButtonListener( ActionEvent actionEvent) throws SQLException
{
  System.out.println("Save Button Clicked!");
}
}
