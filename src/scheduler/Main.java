package scheduler;

import databaseAccess.DBCountries;
import databaseAccess.DBQuery;
import databaseAccess.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;



/**
 * The Main method starts the program.
 */
public class Main extends Application
{

/**
 * Start method that loads the initial Stage and Scene
 * @param primaryStage The first stage of the program, the login form
 * @throws Exception Thrown if there is an issue with loading a file
 */

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Locale locale = Locale.getDefault();
        
        if (locale.toString().equals("en_US"))
        {
            Parent root = FXMLLoader.load( getClass( ).getResource( "../views/login.fxml" ) );
            primaryStage.setTitle( "Welcome" );
            primaryStage.setScene( new Scene( root, 640, 378 ) );
            primaryStage.show( );
        }
        else if (locale.toString().equals( "fr_US" ))
        {
            Parent root = FXMLLoader.load(getClass().getResource( "../views/login.fxml" ));
            primaryStage.setTitle("Bienvenue");
            Scene scene = new Scene(root, 640, 380);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        
        
        
    }

/**
 * Main method to start the program.
 * @param args Command Line arguments passed into the program
 * @throws SQLException thrown if the SQL is malformed
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
//        Scanner keyboard = new Scanner(System.in);
//
//        System.out.print("Enter new country: ");
//        newCountry = keyboard.nextLine();
//
//        System.out.print("Enter user: " );
//        createdBy = keyboard.nextLine();
//
//        System.out.print("Enter old country: " );
//        countryName = keyboard.nextLine();
        
        // Key-value mapping
//        ps.setString(1, countryName);
//        ps.setString(2, createDate);
//        ps.setString(3, createdBy);
//        ps.setString(4, lastUpdateBy);
        
//        ps.setString(1, countryName);
//        ps.setString(2, createdBy);
//        ps.setString(3, newCountry);
       
    
//        ps.execute(); // Execute the PreparedStatement
        
        // Confirming rows affected
        if(ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + " row(s) affected.");
        else
            System.out.println("No rows changed.");
        
        // Launch the program
        launch(args);
    
        // Closes the database connection
//        connection.close();
    
    }

/**
 * The running count of the ID numbers of each <code>Customer</code>
 */
public static int currentId;
public static Customer selectedCustomer = null;
//public static ObservableList<Customer> customerRecords  =
//    FXCollections.observableArrayList( DBCustomerRecords.getAllCustomerRecords( ) );
}
