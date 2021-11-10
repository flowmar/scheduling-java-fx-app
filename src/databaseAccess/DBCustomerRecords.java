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
  public static void getAllCustomerRecords()
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
  
  
        System.out.println("Customer Information: " + customerId + " " + customerName + " " + customerAddress + " "
                               + customerPostalCode + " " + customerPhoneNumber + " " + customerDivisionId + " " + customerDivisionName);
        
        // Use the Country ID to look up the Country name
      }
    }
    catch ( SQLException e)
    {
      e.printStackTrace();
    }
    
    
  }

/**
 * Uses a Division ID to look up the name of the Division in the first_level_divisions table.
 * @param divisionId The ID number of the Division
 * @return Returns the name of the Division
 */
public static String lookUpDivisionName(int divisionId)
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
}
