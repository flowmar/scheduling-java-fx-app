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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

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
        Parent root = FXMLLoader.load(getClass().getResource( "../view/login.fxml" ));
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
        
//        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By)" +
//                                     "VALUES (?, ?, ?, ?)";
    
        String updateStatement = "UPDATE countries SET Country = ?, Created_By = ?, WHERE Country = ?";
        
        // Create PreparedStatement Object
        DBQuery.setPreparedStatement(connection, updateStatement);
    
        PreparedStatement ps = DBQuery.getPreparedStatement();
        
        String countryName, newCountry, createdBy;
//        String countryName;
//        String createDate = "2021-11-02 00:00:00";
//        String createdBy = "admin";
//        String lastUpdateBy = "admin";
        
        // Get user input
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Enter new country: ");
        newCountry = keyboard.nextLine();
    
        System.out.print("Enter user: " );
        createdBy = keyboard.nextLine();
    
        System.out.print("Enter old country: " );
        countryName = keyboard.nextLine();
        
        // Key-value mapping
//        ps.setString(1, countryName);
//        ps.setString(2, createDate);
//        ps.setString(3, createdBy);
//        ps.setString(4, lastUpdateBy);
        
        ps.setString(1, countryName);
        ps.setString(2, createdBy);
        ps.setString(3, newCountry);
       
    
        ps.execute(); // Execute the PreparedStatement
        
        // Confirming rows affected
        if(ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " row(s) affected.");
        else
            System.out.println("No rows changed.");
        
        launch(args);
    
        connection.close();
    
    }
}
