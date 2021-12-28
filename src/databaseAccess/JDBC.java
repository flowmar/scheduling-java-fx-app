package databaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JDBC {

/**
 * Fields
 */
private static final String            protocol     = "jdbc";
private static final String            vendor       = ":mysql:";
private static final String            location     = "//localhost:3306/";
private static final String            databaseName = "client_schedule";
private static final String            jdbcUrl      = protocol + vendor + location + databaseName +
                                                          "?connectionTimeZone = " + "US/Central"; // LOCAL
private static final String            driver       = "com.mysql.cj.jdbc.Driver"; // Driver reference
private static final String            userName     = "sqlUser"; // Username
private static final String            password     = "Passw0rd!"; // Password
private static       Connection        connection   = null;  // Connection Interface
private static       PreparedStatement preparedStatement;

/**
 * Methods
 */

/**
 * Makes the connection to the database
 */
public static void makeConnection( ) {
  try {
    Class.forName( driver ); // Locate Driver
    //password = Details.getPassword(); // Assign password
    connection = DriverManager.getConnection( jdbcUrl, userName, password ); // reference Connection object
    System.out.println( "Connection successful!" );
  }
  catch ( Exception e ) {
    System.out.println( "Error:" + e.getMessage( ) );
  }
}

/**
 * Retrieves the Connection object
 * @return The Connection object
 */
public static Connection getConnection( ) {
  return connection;
}

/**
 * Attempts to close the connection
 */
public static void closeConnection( ) {
  try {
    connection.close( );
    System.out.println( "Connection closed!" );
  }
  catch ( Exception e ) {
//                     System.out.println(e.getMessage());
  }
}

/**
 * Helper function that creates a prepared statement
 * @param sqlStatement A SQL statement
 * @param conn A Connection object
 * @throws SQLException Thrown if SQL is malformed
 */
public static void makePreparedStatement( String sqlStatement, Connection conn ) throws SQLException {
  if ( conn != null ) { preparedStatement = conn.prepareStatement( sqlStatement ); }
  else { System.out.println( "Prepared Statement Creation Failed!" ); }
}

/**
 * Helper function to get a prepared statement
 * @return A Prepared Statement
 */
public static PreparedStatement getPreparedStatement( ) {
  if ( preparedStatement != null ) { return preparedStatement; }
  else { System.out.println( "Null reference to Prepared Statement" ); }
  return null;
}
}