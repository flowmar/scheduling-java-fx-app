package databaseAccess;

import models.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;



public class DBCountries
{

/**
 * Returns an <code>ObservableList</code> containing each <code>Country</code>
 * @return An <code>ObservableList</code> containing each <code>Country</code>
 */
  public static ObservableList<Country> getAllCountries()
  {
    ObservableList<Country> countryList = FXCollections.observableArrayList();
    
    try
    {
      String sql = "SELECT * FROM countries";
      
      PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
      
      ResultSet rs = ps.executeQuery();
      
      while (rs.next())
      {
        int countryId = rs.getInt("Country_ID");
        String  countryName = rs.getString("Country");
        Country c           = new Country(countryId, countryName);
        countryList.add(c);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    
    
    return countryList;
  }

  public static void checkDateConversion()
  {
    System.out.println("CREATE DATE TEST");
    String sql = "SELECT Create_Date FROM countries";
    try
    {
      PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
      ResultSet         rs = ps.executeQuery();
      while (rs.next())
      {
        Timestamp ts = rs.getTimestamp("Create_Date");
        System.out.println("CD: " + ts.toLocalDateTime().toString());
      }
    }
    catch ( SQLException err)
    {
      err.printStackTrace();
    }
  }
}
