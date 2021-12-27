package databaseAccess;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Helper functions to access Customer Records
 */
public class DBCustomerRecords {

/**
 * Retrieves all customer records from the customer table.
 *
 * @return Returns an ArrayList containing all the customers from the database.
 *
 * @throws SQLException Throws a SQLException if the SQL is malformed.
 */
public static ObservableList<Customer> getAllCustomerRecords( ) throws SQLException {
//    ObservableList<Customer> customers = new ArrayList<Customer>();
  ObservableList<Customer> customers = FXCollections.observableArrayList( );
  // Retrieve all stored customers from the database
  try {
    
    String sql = "SELECT * FROM customers";
    
    PreparedStatement preparedStatement = JDBC.getConnection( ).prepareStatement( sql );
    
    ResultSet customersResultSet = preparedStatement.executeQuery( );
    
    while ( customersResultSet.next( ) ) {
      // Get the information from the database
      int    customerId          = customersResultSet.getInt( "Customer_ID" );
      String customerName        = customersResultSet.getString( "Customer_Name" );
      String customerAddress     = customersResultSet.getString( "Address" );
      String customerPostalCode  = customersResultSet.getString( "Postal_Code" );
      String customerPhoneNumber = customersResultSet.getString( "Phone" );
      int    customerDivisionId  = customersResultSet.getInt( "Division_ID" );
      String createDate          = customersResultSet.getString( "Create_Date" );
      String createdBy           = customersResultSet.getString( "Created_By" );
      String lastUpdate          = customersResultSet.getString( "Last_Update" );
      String lastUpdatedBy       = customersResultSet.getString( "Last_Updated_By" );
      
      // Convert all to Properties to display in the TableView
      IntegerProperty customerIdProperty          = new SimpleIntegerProperty( customerId );
      StringProperty  customerNameProperty        = new SimpleStringProperty( customerName );
      StringProperty  customerAddressProperty     = new SimpleStringProperty( customerAddress );
      StringProperty  customerPostalCodeProperty  = new SimpleStringProperty( customerPostalCode );
      StringProperty  customerPhoneNumberProperty = new SimpleStringProperty( customerPhoneNumber );
      IntegerProperty customerDivisionIdProperty  = new SimpleIntegerProperty( customerDivisionId );
      StringProperty  createDateProperty          = new SimpleStringProperty( createDate );
      StringProperty  createdByProperty           = new SimpleStringProperty( createdBy );
      StringProperty  lastUpdateProperty          = new SimpleStringProperty( lastUpdate );
      StringProperty  lastUpdatedByProperty       = new SimpleStringProperty( lastUpdatedBy );
      
      // Use the Division ID to look up the name of the Division
      String customerDivisionName = lookUpDivisionName( customerDivisionId );
      
      StringProperty customerDivisionNameProperty = new SimpleStringProperty( customerDivisionName );
      
      // Use the Division name to look up the Country ID
      int customerCountryId = lookUpCountryId( customerDivisionIdProperty );
      
      IntegerProperty customerCountryIdProperty = new SimpleIntegerProperty( customerCountryId );
      
      // Use the Country ID to look up the Country name
      String customerCountryName = lookUpCountryName( customerCountryId );
      
      StringProperty customerCountryNameProperty = new SimpleStringProperty( customerCountryName );
      
      System.out.println( "Customer Information: " + customerId + " " + customerName + " " + customerAddress + " "
                              + customerPostalCode + " " + customerPhoneNumber + " " + customerDivisionId + " " + customerDivisionName + " " + customerCountryId + " " + customerCountryName );
      
      
      // Create a new Customer using the data obtained from the database
      Customer currentCustomer = new Customer( customerIdProperty, customerNameProperty, customerAddressProperty,
          customerPostalCodeProperty,
          customerPhoneNumberProperty, createDateProperty, createdByProperty, lastUpdateProperty,
          lastUpdatedByProperty,
          customerDivisionIdProperty,
          customerDivisionNameProperty, customerCountryIdProperty, customerCountryNameProperty );
      
      // Add the customer to the customers ArrayList
      customers.add( currentCustomer );
    }
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  
  return customers;
}

/**
 * Uses a Division ID to look up the name of the Division in the first_level_divisions table.
 *
 * @param divisionId The ID number of the Division
 * @return Returns the name of the Division
 */
public static String lookUpDivisionName( int divisionId ) throws SQLException {
  String divisionName = "";
  
  try {
    
    String sql = "SELECT * FROM first_level_divisions WHERE Division_ID =" + divisionId;
    
    PreparedStatement preparedStatement = JDBC.getConnection( ).prepareStatement( sql );
    
    ResultSet firstLevelDivisionsResultSet = preparedStatement.executeQuery( );
    
    
    while ( firstLevelDivisionsResultSet.next( ) ) {
      divisionName = firstLevelDivisionsResultSet.getString( "Division" );
      
      System.out.println( divisionName );
    }
    
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  
  return divisionName;
}

/**
 * Uses a Division ID to look up the ID number of the Country that it belongs to in the first_level_divisions table.
 *
 * @param divisionIdProperty The ID number of the Division as an IntegerProperty
 * @return Returns ID number of the Country that the Division belongs to.
 */
public static int lookUpCountryId( IntegerProperty divisionIdProperty ) throws SQLException {
  int countryId  = 0;
  int divisionId = divisionIdProperty.getValue( );
  
  try {
    
    String sql = "SELECT * FROM first_level_divisions WHERE Division_ID =" + divisionId;
    
    PreparedStatement preparedStatement = JDBC.getConnection( ).prepareStatement( sql );
    
    ResultSet firstLevelDivisionsResultSet = preparedStatement.executeQuery( );
    
    while ( firstLevelDivisionsResultSet.next( ) ) {
      countryId = firstLevelDivisionsResultSet.getInt( "Country_ID" );
      
      System.out.println( "CountryID: " + countryId );
    }
    
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  
  return countryId;
}

/**
 * Uses a Country ID number to look up the corresponding Country name.
 *
 * @param countryId The ID number of the Country.
 * @return The name of the Country assigned to the countryId.
 *
 * @throws SQLException Throws a SQLException if the SQL is malformed.
 */
public static String lookUpCountryName( int countryId ) throws SQLException {
  String countryName = "";
  
  try {
    String sql = "SELECT * FROM countries WHERE Country_ID=" + countryId;
    
    PreparedStatement preparedStatement = JDBC.getConnection( ).prepareStatement( sql );
    
    ResultSet countriesResultSet = preparedStatement.executeQuery( );
    
    while ( countriesResultSet.next( ) ) {
      countryName = countriesResultSet.getString( "Country" );
      
      System.out.println( countryName );
    }
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  return countryName;
}

/**
 * Takes in the <code>division</code> from the <code>DivisionComboBox</code> and returns only the numerical value of the
 * <code>Division_ID</code>
 *
 * @param division The <code>division</code> selected in the <code>DivisionComboBox</code>
 * @return The <code>Division_ID</code> as a <code>String</code>
 */
public static String lookUpDivisionId( String division ) {
  System.out.println( "Division: " + division );
  // Remove all characters past the ID number
  String divisionIdString = division.toString( ).substring( 0, division.toString( ).indexOf( " ", 0 ) );
  System.out.println( "divisionIdString: " + divisionIdString );
  return divisionIdString;
}

}