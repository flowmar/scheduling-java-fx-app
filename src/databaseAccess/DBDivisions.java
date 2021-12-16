package databaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Division;
import tools.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivisions
{
  public static ObservableList<Division> getAllDivisions()
  {
  ObservableList<Division> divisionList = FXCollections.observableArrayList();
  
  try
  {
    String sql = "SELECT * FROM first_level_divisions";
  
    PreparedStatement ps = JDBC.getConnection( ).prepareStatement(sql);
  
    ResultSet rs = ps.executeQuery();
    
    while (rs.next())
    {
      int divisionId = rs.getInt("Division_ID");
      String   divisionName = rs.getString("Division");
      String     date         = rs.getString( "Create_Date" );
      String   createdBy    = rs.getString("Created_By" );
      String    lastUpdate = rs.getString( "Last_Update" );
      String   lastUpdatedBy = rs.getString("Last_Updated_By");
      int      countryId = rs.getInt("Country_ID");
      Division d            = new Division( divisionId, divisionName, date, createdBy, lastUpdate, lastUpdatedBy,
          countryId );
      divisionList.add(d);
    }
  }
  catch ( SQLException e)
  {
    e.printStackTrace();
  }
  
  
  return divisionList;
  }
}
