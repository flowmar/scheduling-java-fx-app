package controller;

import databaseAccess.DBCountries;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import models.Countries;
import tools.JDBC;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
  findUserLocation();
  System.out.println("Initialize");
  translateLabels();
}

/**
 *
 */
public void findUserLocation()
{
  Locale locale = Locale.getDefault( );
  System.out.println( locale );
  localeLabel.setText("Locale: " + locale.getDefault().toString());
  
//  Locale localeFr = new Locale.Builder( ).setLanguage( "fr" ).setRegion( "US" ).build( );
//  Locale.setDefault( localeFr );
//  System.out.println( locale.getDefault( ) );
  localeLabel.setText("Locale: " + locale.getDefault().toString());
  
}

/**
 *
 */
public void translateLabels()
{
  Locale locale = Locale.getDefault();

  if (locale.toString().equals("fr_US"))
  {
    // Try-with-resources for French properties
    try (InputStream inputProperties = new FileInputStream("src/resources/LoginFormResource_fr.properties"))
    {
      // Create a new Properties Object
      Properties propertiesFr = new Properties();
      // Load the information from the French translation file
      propertiesFr.load(inputProperties);
      
      // Translate label text
      loginButton.setText(propertiesFr.getProperty("loginButton"));
      exitButton.setText(propertiesFr.getProperty("exitButton"));
      loginErrorLabel.setText(propertiesFr.getProperty("loginErrorLabel"));
      loginErrorLabel.setTextFill(Color.WHITE);
      loginTitleLabel.setText(propertiesFr.getProperty("title"));
      loginInstructionsLabel.setText(propertiesFr.getProperty("instructions"));
      usernameLabel.setText(propertiesFr.getProperty("usernameLabel"));
      passwordLabel.setText(propertiesFr.getProperty("passwordLabel"));
      
      
      System.out.println(propertiesFr.getProperty("title"));
      System.out.println(propertiesFr.getProperty("instructions"));
      System.out.println(propertiesFr.getProperty("usernameLabel"));
      System.out.println(propertiesFr.getProperty("passwordLabel"));
      System.out.println(propertiesFr.getProperty("localeLabel"));
      System.out.println(propertiesFr.getProperty("loginButton"));
      System.out.println(propertiesFr.getProperty("exitButton"));
      
    }
    catch (IOException e) {
      e.getMessage( );
      e.printStackTrace();
    }
  }
}

/**
 * Displays a list of countries
 * @param actionEvent
 */
public void displayCountries(ActionEvent actionEvent)
{
  
  ObservableList<Countries>  countryList = DBCountries.getAllCountries( );
  for (Countries c : countryList)
  {
    System.out.println("Country Id: " + c.getId() + "Name: " + c.getName());
  }
}

/**
 * Handles click of 'Login' Button
 * @param actionEvent
 */
public void loginButtonListener(ActionEvent actionEvent)
{
  // Get the text from the Username and Password TextFields
  System.out.println("Login Clicked!");
  String usernameText = usernameTextField.getText();
  String passwordText = passwordTextField.getText();
  
  // Check if username is correct.
  if (usernameText.equals("Bob2"))
  {
    System.out.println( "Username correct!" );
  } else {
    System.out.println( "Username incorrect!");
  }
  
  // Check if password is correct.
  if(passwordText.equals("opensesaME"))
  {
    loginErrorLabel.setText("Welcome! Logging in...");
    loginErrorLabel.setTextFill(Color.GREEN);
    System.out.println("Password correct!");
    System.out.println("Logging in...");
  }  else {
    loginErrorLabel.setText("Invalid credentials");
    loginErrorLabel.setTextFill(Color.RED);
    System.out.println( "Password incorrect!");
    
  }
}

/**
 * Handles click of 'Exit' button
 * @param actionEvent
 */
public void exitButtonListener(ActionEvent actionEvent)
{
  JDBC.closeConnection();
  System.out.println("Exit Clicked!");
  Platform.exit();
}
}
