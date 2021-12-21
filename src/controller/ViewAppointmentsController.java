package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import models.Appointment;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewAppointmentsController implements Initializable {

@FXML
private TableView<Appointment> appointmentsTableView;

@FXML
private Button exitButton;

@FXML
private Button deleteButton;

@FXML
private Button viewCustomersButton;

@FXML
private Button updateButton;

@FXML
private Button addButton;

@Override
public void initialize( URL url, ResourceBundle resourceBundle ) {

}

}
