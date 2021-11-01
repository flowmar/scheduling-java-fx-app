package scheduler;

import Models.Countries;
import databaseAccess.DBCountries;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import tools.JDBC;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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

public TableColumn idCol;
public TableColumn nameCol;
public TableView   dataTable;


@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
  System.out.println("Initialize");
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
 *
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
