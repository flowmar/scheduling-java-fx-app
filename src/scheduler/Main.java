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
import java.sql.SQLException;
import java.sql.Statement;

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
        
        // SQL Insert Statement
        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Update," +
                                     "Last_Updated_By) " +
                                     "VALUES " +
                                     "('US'," +
                                     "'2021-11-1 00:00:00', 'admin', NOW(),'admin');";
        
        // Execute Statement
        statement.executeUpdate(insertStatement);
        
        // Rows affected Confirmation
        if ( statement != null )
        {
            if(statement.getUpdateCount() > 0)
            {
                System.out.println(statement.getUpdateCount() + " rows affected.");
            }
            else
            {
                System.out.println("No changes occurred.");
            }
        }
    
        connection.close();
        
        launch(args);
    }
}
