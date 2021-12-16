package controller;

import databaseAccess.DBCountries;
import databaseAccess.DBCustomerRecords;
import databaseAccess.DBDivisions;
import javafx.collections.FXCollections;
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
import tools.DBQuery;
import tools.JDBC;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static scheduler.Main.currentId;

public class AddNewCustomerController implements Initializable {

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
 *
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle ) {
  autoGenerateId( );
  populateCountryComboBox( );
  
}

/**
 * Automatically generate the new user ID number
 */
public void autoGenerateId( ) {
  currentId += 1;
  customerIDTextField.setText( String.valueOf( currentId ) );
}

/**
 *
 */
public void populateCountryComboBox( ) {
  // Get the list of countries from the database
  ObservableList<Country> countryList = DBCountries.getAllCountries( );
  System.out.println( countryList.toString( ) );
  System.out.println( countryList );
  
  // Populate the ComboBox
  countryComboBox.setItems( countryList );
  countryComboBox.setPromptText( "Select a Country..." );
}

/**
 * Closes out the 'Add Customer' window
 *
 * @param actionEvent User click on the 'Cancel' button
 */
public void cancelButtonListener( ActionEvent actionEvent ) {
  Stage stage = ( Stage ) cancelButton.getScene( ).getWindow( );
  stage.close( );
}

/**
 * Saves the information from the Text Fields into the database as a new customer.
 *
 * @param actionEvent User click on the 'Save' button
 * @throws SQLException Throws an exception if SQL is malformed.
 */
public void saveButtonListener( ActionEvent actionEvent ) throws SQLException {
  System.out.println( "Save Button Clicked!" );
  // Get values within the text fields
  String name = customerNameTextField.getText( );
  String address      = addressTextField.getText( );
  String postalCode   = postalCodeTextField.getText( );
  String phoneNumber  = phoneNumberTextField.getText( );
  Country country = countryComboBox.getValue( );
  Division division = divisionComboBox.getValue();
  String divisionIdString = DBCustomerRecords.lookUpDivisionId(division);
  System.out.println( division );
  System.out.println("Division ID String: " +divisionIdString);
  
  System.out.println(name + " "
      + address + " "
      + postalCode + " "
      + phoneNumber + " "
      +country + " "
      + division);
  
  // Make the connection
  JDBC.makeConnection();
  // Converts the date and time
  DBCountries.checkDateConversion();
  
  // Assign the connection to a variable
  Connection connection = JDBC.getConnection();
  
  String insertStatement =
      "INSERT INTO client_schedule.customers(Customer_Name, Address, Postal_Code," +
          "Phone, Division_ID) VALUES (?,?,?,?,?)";
  
  DBQuery.setPreparedStatement(connection, insertStatement);
  
  PreparedStatement ps = DBQuery.getPreparedStatement();
  
  ps.setString(1, name);
  ps.setString(2, address);
  ps.setString(3, postalCode);
  ps.setString(4, phoneNumber);
  ps.setString(5, divisionIdString);
  ps.execute();
  
  Stage stage = ( Stage ) cancelButton.getScene( ).getWindow( );
  stage.close( );
}

/**
 *
 * @param actionEvent
 */
public void countryComboBoxListener( ActionEvent actionEvent ) {
  // Get the selected value in the ComboBox
  Country countryComboValue = countryComboBox.getValue( );
  // Get Id
  int countryComboValueInt = countryComboValue.getId( );
  
  // Populate the divisionComboBox with the corresponding territories
  populateDivisionComboBox( countryComboValueInt );
}

/**
 *
 * @param val
 */
public void populateDivisionComboBox( int val )
{
  ObservableList<Division> divisionsList = FXCollections.observableArrayList( );
  
  // Based on val, obtain the required divisions from the database
  switch ( val )
  {
    case 1:
      divisionsList = DBDivisions.getUSADivisions( );
      System.out.println( divisionsList );
      break;
    case 2:
      divisionsList = DBDivisions.getCanadaDivisions( );
      System.out.println( divisionsList );
      
      break;
    case 3:
      divisionsList = DBDivisions.getUKDivisions( );
      System.out.println( divisionsList );
      
      break;
    default:
      System.out.println( "(nothing)" );
      break;
  }
  
  // Populate the ComboBox
  System.out.println( divisionsList );
  divisionComboBox.setItems( divisionsList );
  
  divisionComboBox.setPromptText( "Select a Division..." );
  
  
}
}
