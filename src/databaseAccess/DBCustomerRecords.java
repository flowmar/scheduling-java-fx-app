package databaseAccess;

import tools.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomerRecords
{

/**
 * Retrieves all customer records from the customer table.
 */
  public static void getAllCustomerRecords() throws SQLException
  {
    // Retrieve all stored customers from the database
    try
    {
      
      String sql = "SELECT * FROM customers";
  
      PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
  
      ResultSet customersResultSet = preparedStatement.executeQuery();
      
      while (customersResultSet.next())
      {
        int customerId = customersResultSet.getInt("Customer_ID");
        String customerName = customersResultSet.getString("Customer_Name");
        String customerAddress = customersResultSet.getString("Address");
        String customerPostalCode = customersResultSet.getString("Postal_Code");
        String customerPhoneNumber = customersResultSet.getString("Phone");
        int customerDivisionId = customersResultSet.getInt("Division_ID");
        
        
        // Use the Division ID to look up the name of the Division
        String customerDivisionName = lookUpDivisionName(customerDivisionId);
  
        // Use the Division name to look up the Country ID
        int customerCountryId = lookUpCountryId(customerDivisionId);
  
        // Use the Country ID to look up the Country name
        String customerCountryName = lookUpCountryName(customerCountryId);
  
        System.out.println("Customer Information: " + customerId + " " + customerName + " " + customerAddress + " "
                               + customerPostalCode + " " + customerPhoneNumber + " " + customerDivisionId + " " + customerDivisionName + " " + customerCountryId + " " + customerCountryName);
        
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    
    
  }

/**
 * Uses a Division ID to look up the name of the Division in the first_level_divisions table.
 * @param divisionId The ID number of the Division
 * @return Returns the name of the Division
 */
public static String lookUpDivisionName(int divisionId) throws SQLException
  {
    String divisionName = "";
  
    try {
      
      String sql = "SELECT * FROM first_level_divisions WHERE Division_ID =" + divisionId;
      
      PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
      
      ResultSet firstLevelDivisionsResultSet = preparedStatement.executeQuery();
      
      
      while (firstLevelDivisionsResultSet.next())
      {
        divisionName = firstLevelDivisionsResultSet.getString("Division");
        
        System.out.println(divisionName);
      }
      
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    
    return divisionName;
  }

/**
 * Uses a Division ID to look up the ID number of the Country that it belongs to in the first_level_divisions table.
 * @param divisionId The ID number of the Division.
 * @return Returns ID number of the Country that the Division belongs to.
 */
public static int lookUpCountryId(int divisionId) throws SQLException
{
  int countryId = 0;
  
  try {
    
    String sql = "SELECT * FROM first_level_divisions WHERE Division_ID =" + divisionId;
    
    PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
    
    ResultSet firstLevelDivisionsResultSet = preparedStatement.executeQuery();
    
    
    while (firstLevelDivisionsResultSet.next())
    {
      countryId = firstLevelDivisionsResultSet.getInt("Country_ID");
      
      System.out.println(countryId);
    }
    
  }
  catch (SQLException e)
  {
    e.printStackTrace();
  }
  
  return countryId;
}

/**
 * Uses a Country ID number to look up the corresponding Country name.
 * @param countryId The ID number of the Country.
 * @return The name of the Country assigned to the countryId.
 * @throws SQLException Throws a SQLException if the SQL is malformed.
 */
public static String lookUpCountryName(int countryId) throws SQLException
  {
    String countryName = "";
    
    try
    {
      String sql = "SELECT * FROM countries WHERE Country_ID=" + countryId;
      
      PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);
      
      ResultSet countriesResultSet = preparedStatement.executeQuery();
      
      while (countriesResultSet.next())
      {
        countryName = countriesResultSet.getString("Country");
        
        System.out.println(countryName);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return countryName;
  }
}