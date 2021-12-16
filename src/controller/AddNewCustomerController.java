package controller;

import databaseAccess.DBCountries;
import databaseAccess.DBDivisions;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Country;
import models.Division;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static scheduler.Main.currentId;

public class AddNewCustomerController implements Initializable
{

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

@FXML
private ComboBox<Country> countryComboBox;

@FXML
private ComboBox<Division> divisionComboBox;

/**
 * Methods
 */

/**
 * Initializes the Add Customer Scene
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle )
{
  autoGenerateId();
  populateCountryComboBox();
  
  
}

/**
 * Automatically generate the new user ID number
 */
public void autoGenerateId()
{
  currentId += 1;
  customerIDTextField.setText( String.valueOf( currentId ) );
}

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
  // Get values within the text fields
  String customerName = customerNameTextField.getText();
  String address = addressTextField.getText();
  String postalCode = postalCodeTextField.getText();
  String phoneNumber = phoneNumberTextField.getText();
  
}

public void countryComboBoxListener(ActionEvent actionEvent)
{
  // Get the selected value in the ComboBox
  Country countryComboValue = countryComboBox.getValue();
  // Get Id
  int countryComboValueInt = countryComboValue.getId();
  
  // Populate the divisionComboBox with the corresponding territories
  populateDivisionComboBox(countryComboValueInt);
  }


/**
 *
 */
public void populateCountryComboBox()
{
  // Get the list of countries from the database
  ObservableList<Country> countryList = DBCountries.getAllCountries( );
  System.out.println( countryList.toString( ) );
  System.out.println( countryList );
  
  // Populate the ComboBox
  countryComboBox.setItems( countryList );
  countryComboBox.setPromptText( "Select a Country..." );
}


public void populateDivisionComboBox(int val)
{
  
  // Get the list of States and Provinces
  ObservableList<Division> divisionsList = DBDivisions.getAllDivisions();
  System.out.println(divisionsList);
  // Populate the ComboBox
  // If the selected country has an id of 1
  // Add the divisions between 1 and 54
  
  System.out.println(divisionsList);
  divisionComboBox.setItems(divisionsList);
  
  divisionComboBox.setPromptText( "Select a Division...");
  
  
}
}
