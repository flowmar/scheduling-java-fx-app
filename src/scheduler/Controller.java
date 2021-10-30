package scheduler;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
/**
 * Fields
 */

@FXML
public TextField usernameTextField;

@FXML
public TextField passwordTextField;

@FXML
public Button loginButton;

@FXML
public Button exitButton;

@Override
public void initialize( URL url, ResourceBundle resourceBundle) {
  System.out.println("Initialize");
}

public void loginButtonListener(ActionEvent actionEvent){
  // Get the text from the Username and Password TextFields
  System.out.println("Login Clicked!");
  // Check if username and password are correct
  
  
}

public void exitButtonListener(ActionEvent actionEvent) {
  System.out.println("Exit Clicked!");
  Platform.exit();
}
}
