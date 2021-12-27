package databaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDivisions {

/**
 * Retrieves an <code>ObservableList</code> containing each US <code>Division</code>
 * @return An <code>ObservableList</code> containing each US <code>Division</code>
 */
public static ObservableList<Division> getUSADivisions( ) {
  ObservableList<Division> divisionList = FXCollections.observableArrayList( );
  
  try {
    String sql = "SELECT * FROM client_schedule.first_level_divisions " +
                     "WHERE Country_ID=1";
    
    PreparedStatement ps = JDBC.getConnection( ).prepareStatement( sql );
    
    ResultSet rs = ps.executeQuery( );
    
    while ( rs.next( ) ) {
      int    divisionId    = rs.getInt( "Division_ID" );
      String divisionName  = rs.getString( "Division" );
      String date          = rs.getString( "Create_Date" );
      String createdBy     = rs.getString( "Created_By" );
      String lastUpdate    = rs.getString( "Last_Update" );
      String lastUpdatedBy = rs.getString( "Last_Updated_By" );
      int    countryId     = rs.getInt( "Country_ID" );
      Division d = new Division( divisionId, divisionName, date, createdBy, lastUpdate, lastUpdatedBy,
          countryId );
      divisionList.add( d );
    }
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  
  
  return divisionList;
}

/**
 *Retrieves an <code>ObservableList</code> containing each Canadian <code>Division</code>
 * @return An <code>ObservableList</code> containing each Canadian <code>Division</code>
 */
public static ObservableList<Division> getCanadaDivisions( ) {
  ObservableList<Division> divisionList = FXCollections.observableArrayList( );

  try {
    String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 2";

    PreparedStatement ps = JDBC.getConnection( ).prepareStatement( sql );

    ResultSet rs = ps.executeQuery( );

    while ( rs.next( ) ) {
      int    divisionId    = rs.getInt( "Division_ID" );
      String divisionName  = rs.getString( "Division" );
      String date          = rs.getString( "Create_Date" );
      String createdBy     = rs.getString( "Created_By" );
      String lastUpdate    = rs.getString( "Last_Update" );
      String lastUpdatedBy = rs.getString( "Last_Updated_By" );
      int    countryId     = rs.getInt( "Country_ID" );
      Division d = new Division( divisionId, divisionName, date, createdBy, lastUpdate, lastUpdatedBy,
          countryId );
      divisionList.add( d );
    }
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }


  return divisionList;
}

/**
 *Retrieves an <code>ObservableList</code> containing each UK <code>Division</code>
 * @return An <code>ObservableList</code> containing each UK <code>Division</code>
 */
public static ObservableList<Division> getUKDivisions( ) {

  ObservableList<Division> divisionList = FXCollections.observableArrayList( );

  try {
    String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = 3";

    PreparedStatement ps = JDBC.getConnection( ).prepareStatement( sql );

    ResultSet rs = ps.executeQuery( );

    while ( rs.next( ) ) {
      int    divisionId    = rs.getInt( "Division_ID" );
      String divisionName  = rs.getString( "Division" );
      String date          = rs.getString( "Create_Date" );
      String createdBy     = rs.getString( "Created_By" );
      String lastUpdate    = rs.getString( "Last_Update" );
      String lastUpdatedBy = rs.getString( "Last_Updated_By" );
      int    countryId     = rs.getInt( "Country_ID" );

      Division d = new Division( divisionId, divisionName, date, createdBy, lastUpdate, lastUpdatedBy,
          countryId );

      divisionList.add( d );
    }
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  
  return divisionList;

}
  
}