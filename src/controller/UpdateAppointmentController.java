package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Contact;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {
  
  /**
   * Fields
   */
  @FXML
  private TextField appointmentIdTextField;
  
  @FXML
  private TextField customerIdTextField;
  
  @FXML
  private TextField userIdTextField;
  
  @FXML
  private TextField appointmentTitleTextField;
  
  @FXML
  private TextField appointmentDescriptionTextField;
  
  @FXML
  private TextField appointmentLocationTextField;
  
  @FXML
  private TextField appointmentTypeTextField;
  
  @FXML
  private ComboBox<Contact> contactComboBox;
  
  @FXML
  private DatePicker startDatePicker;
  
  @FXML
  private DatePicker endDatePicker;
  
  @FXML
  private Button cancelButton;
  
  @FXML
  private Button updateButton;

/**
 * Methods
 */
  
  /**
   *
   * @param url
   * @param resourceBundle
   */
  @Override
  public void initialize( URL url, ResourceBundle resourceBundle )
  {
  
  }

public void updateAppointmentButtonListener( ActionEvent actionEvent)
{
  System.out.println( "Add appointment!" );

//  Stage stage = (Stage) addAppointmentButton.getScene().getWindow();

}

public void cancelButtonListener(ActionEvent actionEvent)
{
  System.out.println( "Cancel add appointment!" );
  
  Stage stage = (Stage) cancelButton.getScene().getWindow();
  stage.close();
}
}
