package databaseAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class JDBC {
 private static final String protocol = "jdbc";
     private static final String vendor = ":mysql:";
         private static final String location = "//localhost/";
             private static final String databaseName = "client_schedule";
                 private static final String jdbcUrl = protocol + vendor + location + databaseName +
                                                           "?connectionTimeZone = " + "US/Central"; // LOCAL
        private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
        private static final String userName = "sqlUser"; // Username
        private static final String password = "p3opl3?!"; // Password
        private static Connection connection = null;  // Connection Interface
        private static PreparedStatement preparedStatement;
        
        // Method to make the connection
         public static void makeConnection()
         {
          try
          {
              Class.forName(driver); // Locate Driver
              //password = Details.getPassword(); // Assign password
              connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
              System.out.println("Connection successful!");
          }
                  catch(Exception e)
                  {
                    System.out.println( "Error:" + e.getMessage( ) );
                  }
         }

            /// Method to retrieve the connection.
            public static Connection getConnection()
            {
                return connection;
            }
            
            // Method to close connection.
            public static void closeConnection()
            {
                 try
                 {
                     connection.close();
                     System.out.println("Connection closed!");
                 } catch (Exception e) {
//                     System.out.println(e.getMessage());
                 }
             }
           
           public static void makePreparedStatement(String sqlStatement, Connection conn) throws SQLException
           {
           if (conn != null)
               preparedStatement = conn.prepareStatement(sqlStatement);
           else
               System.out.println("Prepared Statement Creation Failed!");
         }
          public static PreparedStatement getPreparedStatement() throws SQLException
          {
           if (preparedStatement != null)
               return preparedStatement;
           else System.out.println("Null reference to Prepared Statement");
           return null;
          }



}