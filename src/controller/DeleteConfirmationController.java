package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteConfirmationController implements Initializable {

@FXML
private Label deleteConfirmationLabel;

@FXML
private Button deleteCustomerButton;

@FXML
private Button cancelDeleteButton;

@Override
public void initialize( URL url, ResourceBundle resourceBundle ) {
  deleteConfirmationLabel.setText("Are you sure you wish to delete this customer?");
  // Create a Dialog to
  Dialog<String> dialog = new Dialog<String>();

  dialog.setTitle("Delete Confirmation");
}

public void deleteCustomerButtonListener( ActionEvent actionEvent) {
    System.out.println( "Delete Clicked!" );
}

public void cancelDeleteButtonListener( ActionEvent actionEvent) {
  System.out.println( "Cancel Clicked!" );
  Stage stage = ( Stage ) cancelDeleteButton.getScene( ).getWindow( );
  stage.close( );
}
}
