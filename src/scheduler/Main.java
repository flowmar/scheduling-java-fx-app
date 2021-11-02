package scheduler;

import databaseAccess.DBCountries;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.DBQuery;
import tools.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main extends Application
{

/**
 *
 * @param primaryStage
 * @throws Exception
 */

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

/**
 *
 * @param args
 * @throws SQLException
 */
public static void main(String[] args) throws SQLException
    {
        // Make the connection
        JDBC.makeConnection();
        
        // Converts the Date and time.
        DBCountries.checkDateConversion();
        
        // Assign the connection to a variable
        Connection connection = JDBC.getConnection();
        
        // Create the Statement object
        DBQuery.setStatement(connection);
        
        // Get the Statement Reference
        Statement statement = DBQuery.getStatement();
        
        
        String selectStatement = "SELECT * FROM countries"; // SELECT statement
        
        statement.execute(selectStatement); // Execute statement
        
        ResultSet rs = statement.getResultSet();
        
        // Forward Scroll ResultSet
        while (rs.next())
        {
            int       countryId   = rs.getInt("Country_ID");
            String    countryName = rs.getString("Country");
            LocalDate createDate  = rs.getDate("Create_Date").toLocalDate();
            LocalTime time        = rs.getTime("Create_Date").toLocalTime();
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            
            // Display
            System.out.println( countryId + " | " + countryName + " | " + createDate + " |" + time + " |" + createdBy + " |" + lastUpdate);
        }
        
        
        // SQL Insert Statement
//        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Update," +
//                                     "Last_Updated_By) " +
//                                     "VALUES " +
//                                     "('US'," +
//                                     "'2021-11-1 00:00:00', 'admin', NOW(),'admin');"; // Insert Statement
        
        // Variable Insert
//        String countryName = "Canada";
//        String createDate = "2020-11-2 00:00:00";
//        String createdBy = "admin";
//        String lastUpdate = "admin";
//
//        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Update, " +
//                                     "Last_Updated_By) VALUES(" +
//                                     "'" + countryName + "'," +
//                                     "'" + createDate + "'," +
//                                     "'" + createdBy + "'," +
//                                     "'" + lastUpdate + "')";
//        // Execute Statement
//        statement.execute(insertStatement);
        
        // Rows affected Confirmation
        if ( statement != null )
        {
            if(statement.getUpdateCount() > 0)
            {
                System.out.println(statement.getUpdateCount() + " row(s) affected.");
            }
            else
            {
                System.out.println("No changes occurred.");
            }
        }
        
        launch(args);
    
        connection.close();
    
    }
}
