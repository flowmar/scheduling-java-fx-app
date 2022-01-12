package scheduler;

import databaseAccess.DBCountries;
import databaseAccess.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Appointment;
import models.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

/**
 * The Main method starts the program.
 */
public class Main extends Application {

/**
 * Fields
 */

/**
 * The running count of the ID numbers of each <code>Customer</code>
 */
public static int         currentId;
/**
 * The running count of the ID numbers of each <code>Appointment</code>
 */
public static int         currentAppointmentId;
/**
 * The customer currently selected in the customerRecordsTableView
 */
public static Customer    selectedCustomer    = null;
/**
 * The Appointment currently selected in the viewAppointmentsTableView
 */
public static Appointment selectedAppointment = null;

/**
 * Methods
 */

/**
 * Main method to start the program.
 *
 * @param args Command Line arguments passed into the program
 * @throws SQLException thrown if the SQL is malformed
 */
public static void main( String[] args ) throws SQLException {
  // Make the connection
  JDBC.makeConnection( );
  
  // Converts the Date and time.
//  DBCountries.checkDateConversion( );
  
  // Assign the connection to a variable
  Connection connection = JDBC.getConnection( );
  
  // Launch the program
  launch( args );
  
}

/**
 * Start method that loads the initial Stage and Scene
 *
 * @param primaryStage The first stage of the program, the login form
 * @throws Exception Thrown if there is an issue with loading a file
 */

@Override
public void start( Stage primaryStage ) throws Exception {
  Locale locale = Locale.getDefault( );
  
  System.out.println(locale);
  
  if ( locale.toString( ).equals( "en_US" ) ) {
    Parent root = FXMLLoader.load( getClass( ).getResource( "../views/login.fxml" ) );
    primaryStage.setTitle( "Welcome" );
    primaryStage.setScene( new Scene( root, 640, 378 ) );
    primaryStage.show( );
  }
  else if ( locale.toString( ).contains( "fr" ) ) {
    Parent root = FXMLLoader.load( getClass( ).getResource( "../views/login.fxml" ) );
    primaryStage.setTitle( "Bienvenue" );
    Scene scene = new Scene( root, 640, 380 );
    primaryStage.setScene( scene );
    primaryStage.show( );
  }
  
  
}
}
