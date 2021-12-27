package controller;

import databaseAccess.DBQuery;
import databaseAccess.JDBC;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

/**
 * Fields
 */

@FXML
private TextField usernameTextField;

@FXML
private TextField passwordTextField;

@FXML
private Button loginButton;

@FXML
private Button exitButton;

@FXML
private Label loginErrorLabel;

@FXML
private Label localeLabel;

@FXML
private Label loginInstructionsLabel;

@FXML
private Label usernameLabel;

@FXML
private Label passwordLabel;

@FXML
private Label loginTitleLabel;

/**
 * Methods
 */

/**
 * Initializes the Login Form.
 *
 * @param url
 * @param resourceBundle
 */
@Override
public void initialize( URL url, ResourceBundle resourceBundle ) {
  System.out.println( "Initialize" );
  // Obtain the user's system locale and translate if necessary
  findUserLocation( );
  translateLabels( );
}


/**
 * Obtains the user's system locale and displays it on the Login form.
 */
public void findUserLocation( ) {
  Locale locale = Locale.getDefault( );
  System.out.println( locale );
  localeLabel.setText( "Locale: " + locale.getDefault( ).toString( ) );

//  Locale localeFr = new Locale.Builder( ).setLanguage( "fr" ).setRegion( "US" ).build( );
//  Locale.setDefault( localeFr );
//  System.out.println( locale.getDefault( ) );
  localeLabel.setText( "Locale: " + locale.getDefault( ).toString( ) );
  
}

/**
 * Translates the Login Form if the user's set language is French.
 */
public void translateLabels( ) {
  Locale locale = Locale.getDefault( );
  
  if ( locale.toString( ).equals( "fr_US" ) ) {
    // Try-with-resources for French properties
    try ( InputStream inputProperties = new FileInputStream( "src/resources/LoginFormResource_fr.properties" ) ) {
      // Create a new Properties Object
      Properties propertiesFr = new Properties( );
      // Load the information from the French translation file
      propertiesFr.load( inputProperties );
      
      // Translate label text
      loginButton.setText( propertiesFr.getProperty( "loginButton" ) );
      exitButton.setText( propertiesFr.getProperty( "exitButton" ) );
      loginErrorLabel.setText( propertiesFr.getProperty( "loginErrorLabel" ) );
      loginErrorLabel.setTextFill( Color.WHITE );
      loginTitleLabel.setText( propertiesFr.getProperty( "title" ) );
      loginInstructionsLabel.setText( propertiesFr.getProperty( "instructions" ) );
      usernameLabel.setText( propertiesFr.getProperty( "usernameLabel" ) );
      passwordLabel.setText( propertiesFr.getProperty( "passwordLabel" ) );
      
      
      System.out.println( propertiesFr.getProperty( "title" ) );
      System.out.println( propertiesFr.getProperty( "instructions" ) );
      System.out.println( propertiesFr.getProperty( "usernameLabel" ) );
      System.out.println( propertiesFr.getProperty( "passwordLabel" ) );
      System.out.println( propertiesFr.getProperty( "localeLabel" ) );
      System.out.println( propertiesFr.getProperty( "loginButton" ) );
      System.out.println( propertiesFr.getProperty( "exitButton" ) );
      
    }
    catch ( IOException e ) {
      e.getMessage( );
      e.printStackTrace( );
    }
  }
}


/**
 * Handles click of 'Login' Button. Checks to see if the username and password are correct. Opens the Customer Records
 * Menu in a new window and closes out the Login window if correct. Displays an error message if incorrect.
 *
 * @param actionEvent A user click on the button
 */
public void loginButtonListener( ActionEvent actionEvent ) throws SQLException {
  // Get the text from the Username and Password TextFields
  System.out.println( "Login Clicked!" );
  String usernameText = usernameTextField.getText( );
  String passwordText = passwordTextField.getText( );
  
  boolean checkPassed = checkCredentials( usernameText, passwordText );
  
  // Check if username and password combination are correct
  if ( checkPassed ) {
    // Delay to simulate 'logging in' action
    try {
      logLoginAttempt( createLoginAttempt( true ) );
      // Display confirmation
      loginErrorLabel.setText( "Welcome! Logging in..." );
      loginErrorLabel.setTextFill( Color.GREEN );
      Thread.sleep( 2000 );
    }
    catch ( InterruptedException e ) {
      e.printStackTrace( );
      e.getMessage( );
    }
    
    // Close out Login window and display Customer Records
    Parent secondRoot = null;
    try {
      secondRoot = FXMLLoader.load( getClass( ).getResource( "../views/viewCustomerRecords.fxml" ) );
    }
    catch ( IOException e ) {
      e.printStackTrace( );
    }
    
    // New Scene
    Scene secondScene = new Scene( secondRoot, 1200, 400 );
    
    // New Stage
    Stage secondStage = new Stage( );
    secondStage.setTitle( "Customer Records" );
    secondStage.setScene( secondScene );
    
    // Close the 'Login' window
    Stage stage = ( Stage ) loginButton.getScene( ).getWindow( );
    stage.close( );
    
    // Open the new window
    secondStage.show( );
  }
  else {
    logLoginAttempt( createLoginAttempt( false ) );
//     Display error message if credentials are incorrect
    loginErrorLabel.setText( "Invalid Credentials." );
    loginErrorLabel.setTextFill( Color.RED );
  }
  
}

/**
 * Checks the user input for username and password against the stored credentials in the database.
 *
 * @param username The user input from the Username Text Field.
 * @param password The user input from the Password Text Field.
 * @return Returns true if it passes the credential comparison.
 *
 * @throws SQLException Throws a SQLException if the SQL is malformed.
 */
public boolean checkCredentials( String username, String password ) throws SQLException {
  String  storedPassword = "";
  boolean passwordMatch  = false;
  
  try {
    // Check the database for the username from user input
    Connection connection = JDBC.getConnection( );
    String     sql        = "SELECT * FROM users WHERE User_Name = ?";
    
    // Create an injection-resistant PreparedStatement
    DBQuery.setPreparedStatement( connection, sql );
    PreparedStatement preparedStatement = DBQuery.getPreparedStatement( );
    preparedStatement.setString( 1, username );
    
    // Check the user input against the database
    ResultSet userResultSet = preparedStatement.executeQuery( );
    while ( userResultSet.next( ) ) {
      storedPassword = userResultSet.getString( "Password" );
      
      if ( storedPassword.equals( password ) ) {
        passwordMatch = true;
      }
      else {
        passwordMatch = false;
      }
    }
    
  }
  catch ( SQLException e ) {
    e.printStackTrace( );
  }
  return passwordMatch;
}

/**
 * Creates a login attempt string
 *
 * @param pass Whether the login attempt succeeds or fails
 * @return The <code>String</code> login attempt to be written to the log
 */
public String createLoginAttempt( boolean pass ) {
  String        attempt         = "";
  String        attemptUser     = usernameTextField.getText( );
  String        attemptSuccess;
  LocalDateTime currentDateTime = LocalDateTime.now( );
  
  // Extract the date from the LocalDateTime
  String date = currentDateTime.toString( ).substring( 0, currentDateTime.toString( ).indexOf( "T", 0 ) );
  System.out.println( "Date: " + date );
  
  // Extract the time from the LocalDateTime
  String time = currentDateTime.toString( ).substring( currentDateTime.toString( ).indexOf( "T", 0 ) + 1 );
  time = time.substring( 0, 8 );
  System.out.println( "Time: " + time );
  
  // Set the result of the login attempt
  if ( pass ) {
    attemptSuccess = "Successful";
    
  }
  else {
    attemptSuccess = "Unsuccessful";
    
  }
  
  // Create the String that is to be logged
  attempt = date + " | " + time + " | " + attemptUser + " | " + attemptSuccess + System.lineSeparator( );
  
  return attempt;
}

/**
 * Writes information about the login attempt to the log
 *
 * @param attempt The <code>String</code> that is to be written to the log
 */
public void logLoginAttempt( String attempt ) {
  // Create a new file object for the activity log
  File log = new File( "login_activity.txt" );
  
  // If the file exists...
  if ( log.exists( ) ) {
    try {
      // Create a FileWriter object
      FileWriter logger = new FileWriter( "login_activity.txt", true );
      // Write the login attempt to the log and close the file
      logger.write( attempt );
      logger.close( );
    }
    catch ( IOException e ) {
      e.printStackTrace( );
    }
  }
  // Otherwise, if the file doesn't exist...
  else {
    // Create the file
    try {
      log.createNewFile( );
    }
    catch ( IOException e ) {
      e.printStackTrace( );
    }
    
    try {
      // Create a FileWriter object
      FileWriter logger = new FileWriter( "login_activity.txt", true );
      // Write the header to the file
      String fileHeader = "Date | Time | Username | Login Success " + System.lineSeparator( ) +
                              "-----------------------------------------------" + System.lineSeparator( );
      logger.write( fileHeader );
      // Write the login attempt to the log and close the file
      logger.write( attempt );
      logger.close( );
    }
    catch ( IOException e ) {
      e.printStackTrace( );
    }
    
    
  }
}

/**
 * Handles click of 'Exit' button. Closes the database connection and exits the application.
 *
 * @param actionEvent A user click on the button.
 */
public void exitButtonListener( ActionEvent actionEvent ) {
  JDBC.closeConnection( );
  System.out.println( "Exit Clicked!" );
  Platform.exit( );
}
}
