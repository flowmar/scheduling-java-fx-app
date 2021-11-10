package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {
/**
 * Fields
 */

@FXML
private Button cancelButton;

@FXML
private Button updateButton;

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


@Override
public void initialize( URL url, ResourceBundle resourceBundle )
{
  System.out.println("Initialize Update Customer");
}

/**
 * Closes out the 'Update Customer' window
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
public void updateButtonListener( ActionEvent actionEvent) throws SQLException
{
  System.out.println("Update Button Clicked!");
}
}
