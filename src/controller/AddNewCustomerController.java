package controller;

import databaseAccess.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import models.Customer;
import models.Division;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.ViewCustomerRecordsController.customerRecords;
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
 * Populates the ComboBox that contains the choices for <code>Country</code>
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
 * Populates the divisionComboBox based on the Country ID number of the selected choice in the countryComboBox
 * @param val The value of the Country ID
 */
public void populateDivisionComboBox( int val )
{
  ObservableList<Division> divisionsList = FXCollections.observableArrayList();
  
  // Based on val, the ID number of the Country, obtain the required divisions from the database
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

/**
 * Gets the value from the countryComboBox and populates the DivisionComboBox accordingly.
 * @param actionEvent User selects a choice from the countryComboBox
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
 * Closes out the 'Add Customer' window
 * @param actionEvent User click on the 'Cancel' button
 */
public void cancelButtonListener( ActionEvent actionEvent ) {
  Stage stage = ( Stage ) cancelButton.getScene( ).getWindow( );
  stage.close( );
}

/**
 * Saves the information from the TextFields into the database as a new customer.
 *
 * @param actionEvent User click on the 'Save' button
 * @throws SQLException Throws an exception if SQL is malformed.
 */
public void saveButtonListener( ActionEvent actionEvent ) throws SQLException {
  
  System.out.println( "Save Button Clicked!" );
  
  // Get values within the text fields
  StringProperty name    = new SimpleStringProperty(customerNameTextField.getText( ));
  StringProperty address = new SimpleStringProperty(addressTextField.getText( ));
  StringProperty postalCode = new SimpleStringProperty(postalCodeTextField.getText( ));
  StringProperty phoneNumber  = new SimpleStringProperty(phoneNumberTextField.getText( ));
  StringProperty country = new SimpleStringProperty(countryComboBox.getValue( ).toString());
  StringProperty  division         = new SimpleStringProperty(divisionComboBox.getValue().toString());
  
  // Create IntegerProperties using the rest of the values
  IntegerProperty divisionIdProperty =
      new SimpleIntegerProperty(Integer.parseInt(DBCustomerRecords.lookUpDivisionId(division.getValue())));
  
  int         divisionId       = divisionIdProperty.getValue();
  
  IntegerProperty countryIdProperty =
      new SimpleIntegerProperty(DBCustomerRecords.lookUpCountryId(divisionIdProperty));
  
  System.out.println("Division: " + division);
  
  System.out.println("Division ID String: " + divisionIdProperty.getValue());
  
  IntegerProperty currentIdProperty = new SimpleIntegerProperty(currentId);
  
  System.out.println(name + " "
      + address + " "
      + postalCode + " "
      + phoneNumber + " "
      +country + " "
      + division);
  
  
  // Assign the connection to a variable
  Connection connection = JDBC.getConnection();
  
  // SQL statement for inserting a new Customer into the database
  String insertStatement =
      "INSERT INTO client_schedule.customers(Customer_Name, Address, Postal_Code," +
          "Phone, Division_ID) VALUES (?,?,?,?,?)";
  
  // Prepare the SQL statement and inserts the parameters into their respective indexes
  DBQuery.setPreparedStatement(connection, insertStatement);
  PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
  
  // Set the Strings of the Insert Statement
  preparedStatement.setString(1, name.getValue());
  preparedStatement.setString(2, address.getValue());
  preparedStatement.setString(3, postalCode.getValue());
  preparedStatement.setString(4, phoneNumber.getValue());
  preparedStatement.setString(5, String.valueOf( divisionIdProperty.getValue() ) );
  preparedStatement.execute();
  
  // Add the new customer to the ObservableList
  customerRecords.add( new Customer( currentIdProperty, name, address, postalCode, division, country,
      phoneNumber,
      divisionIdProperty, countryIdProperty ));
  
  // Close out the window
  Stage stage = ( Stage ) saveButton.getScene( ).getWindow( );
  stage.close( );
}

}
