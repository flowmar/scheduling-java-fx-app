package controller;

import databaseAccess.*;
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
import scheduler.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.ViewCustomerRecordsController.customerRecords;
import static databaseAccess.DBCustomerRecords.getAllCustomerRecords;
import static scheduler.Main.selectedCustomer;

/**
 * Controller for the Update Customer <code>Scene</code>
 */
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

@FXML
private ComboBox<Country> countryComboBox;

@FXML
private ComboBox<Division> divisionComboBox;

private int countryComboInt = 0;

/**
 * Methods
 */

/**
 * Initializes the <code>Update Customer Scene</code>
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle )
{
  System.out.println("Initialize Update Customer");
  populateCountryComboBox();
  if (selectedCustomer != null) {
    System.out.println( selectedCustomer );
    retrieveAndPopulateCustomer( );
  }
}

/**
 * Populates the <code>ComboBox</code> that contains the choices for <code>Country</code>
 */
public void populateCountryComboBox( ) {
  // Get the list of countries from the database
  ObservableList<Country> countryList = DBCountries.getAllCountries( );
  System.out.println( countryList.toString( ) );
  System.out.println( countryList );
  
  // Populate the ComboBox
  countryComboBox.setItems( countryList );
  countryComboBox.setPromptText( "Select a Country..." );
  divisionComboBox.setPromptText( "Select a Division..." );
  
}

/**
 * Populates the divisionComboBox based on the Country ID number of the selected choice in the countryComboBox
 * @param countryComboInt The integer that is associated with the <code>Country</code> selected in the Country ComboBox
 */
public void populateDivisionComboBox(int countryComboInt)
{
  ObservableList<Division> divisionsList = FXCollections.observableArrayList( );
  
  // Based the ID number of the Country selected in the first ComboBox, obtain the required divisions from the database
  switch ( countryComboInt )
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
}

/**
 * Retrieves the selected <code>Customer</code>'s data from the database and populates the Update Customer Form
 */
public void retrieveAndPopulateCustomer() {
  Customer selectedCustomerToUpdate = Main.selectedCustomer;
  if ( selectedCustomerToUpdate != null ) {
    customerIDTextField.setText( String.valueOf( selectedCustomerToUpdate.getCustomerId( ) ) );
    customerNameTextField.setText( String.valueOf( selectedCustomerToUpdate.getCustomerName( ) ) );
    addressTextField.setText( String.valueOf( selectedCustomerToUpdate.getAddress( ) ) );
    postalCodeTextField.setText( String.valueOf( selectedCustomerToUpdate.getPostalCode( ) ) );
    phoneNumberTextField.setText( String.valueOf( selectedCustomerToUpdate.getPhoneNumber( ) ) );
    
    // Get the countryId of the selected Customer
    int selectedCustomerCountryId = selectedCustomerToUpdate.getCountryId( );
    for ( Country c : countryComboBox.getItems( ) ) {
      
      System.out.println( countryComboBox.getItems( ) );
      
      System.out.println( "Country: " + c );
      if ( selectedCustomerCountryId == c.getId( ) ) {
        countryComboBox.setValue( c );
        countryComboInt = selectedCustomerCountryId;
        break;
      }
    }
    populateDivisionComboBox( countryComboInt );
    // Get the divisionId of the selected Customer
    int selectedCustomerDivisionId = selectedCustomerToUpdate.getDivisionId( );
    System.out.println( "Division Id: " + selectedCustomerDivisionId );
    
    for ( Division d : divisionComboBox.getItems( ) ) {
      
      System.out.println( "Division: " + d );
      if ( selectedCustomerDivisionId == d.getDivisionId( ) ) {
        divisionComboBox.setValue( d );
        break;
      }
    }
    
  }
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
  populateDivisionComboBox(countryComboValueInt);
}


/**
 * Closes out the 'Update Customer' window
 * @param actionEvent User click on the 'Cancel' button
 */
public void cancelButtonListener(ActionEvent actionEvent)
{
  Stage stage = (Stage) cancelButton.getScene().getWindow();
  stage.close();
}

/**
 * Saves the information from the Text Fields into the database as a new customer.
 * @param actionEvent <code>User</code> clicks on the 'Save' button
 * @throws SQLException Throws an exception if SQL is malformed.
 */
public void updateButtonListener(ActionEvent actionEvent) throws SQLException
{
  
  System.out.println("Update Button Clicked!");
  
  try {
    // Make the connection
    JDBC.makeConnection( );
    DBCountries.checkDateConversion( );
    Connection connection = JDBC.getConnection( );
    // Update SQL statement
    String updateStatement = "UPDATE client_schedule.customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, " +
                                 "Phone = ?, Division_ID= ? WHERE Customer_ID= ?";
    
    // Prepare the statement
    DBQuery.setPreparedStatement( connection, updateStatement );
    PreparedStatement preparedStatement = DBQuery.getPreparedStatement( );
    
    // Set the string values in the statement
    preparedStatement.setString( 1, customerNameTextField.getText( ) );
    preparedStatement.setString( 2, addressTextField.getText( ) );
    preparedStatement.setString( 3, postalCodeTextField.getText( ) );
    preparedStatement.setString( 4, phoneNumberTextField.getText( ) );
    
    // Get the value from the divisionComboBox and take only the DivisionID
    String divisionComboBoxValue = divisionComboBox.getValue( ).toString( );
    String divisionIdString      = divisionComboBoxValue.substring( 0, divisionComboBoxValue.indexOf( " ", 0 ) );
    preparedStatement.setString( 5, divisionIdString );
    preparedStatement.setString( 6, customerIDTextField.getText( ) );
    preparedStatement.execute();
    
    System.out.println("Customer Name: " + customerNameTextField.getText());
    System.out.println("address: " + addressTextField.getText());
    System.out.println("Postal Code" + postalCodeTextField.getText() );
    System.out.println(customerRecords.indexOf(customerIDTextField.getText()));;
    System.out.println("DivisionIdString" + divisionIdString);
    
    // Create a new ObservableList containing the updated record
    ObservableList<Customer> newCustomerList = FXCollections.observableArrayList(getAllCustomerRecords()) ;
    
    // Update the ObservableList to update the table
    customerRecords.setAll(newCustomerList);
    
    // Close the connection and the window
    System.out.println( "Successfully updated!" );
    connection.close();
    Stage stage = (Stage) updateButton.getScene().getWindow( );
    stage.close();
  }
  catch(SQLException e) {
    e.printStackTrace();
  }
}
}

