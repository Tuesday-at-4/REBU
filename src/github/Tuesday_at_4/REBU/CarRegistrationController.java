
/**
 * *********************************************************
 * File : CarRegistrationController.Java
 * Author(s): Ben Cano
 * Class : CEN 3031
 * Purpose : Populates the tables of regsitered vehicles of the user.
 * **********************************************************
 */
package sample;

import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CarRegistrationController {


  @FXML
  private TableView<Car> RegisteredVehicles;

  @FXML
  private TableColumn<?,?> manufacturer;

  @FXML
  private TableColumn<?,?>  model;

  @FXML
  private TableColumn<?,?>  year;

  @FXML
  private TableColumn<?,?>  carType;

  @FXML
  private TableColumn<?,?>  licensePlate;

  @FXML
  private TableColumn<?,?>  CarColor;

  @FXML
  private TableColumn<?,?>  NumSeats;

  @FXML
  private TextField txtField_Manufacturer;

  @FXML
  private TextField txtField_Model;

  @FXML
  private TextField txtField_Year;

  @FXML
  private TextField txtField_carType;

  @FXML
  private TextField txtField_licensePlate;

  @FXML
  private Button Add_Button;

  @FXML
  private Button Delete_Button;

  @FXML
  private Button DriverPage;

  @FXML
  private TextField txtField_Color;

  @FXML
  private TextField txtField_Seats;


  @FXML
  private void goDriverPage(Event event){
    Main.createNewScene(event, "Driver.fxml");
  }
  @FXML
public void Add_Button(Event event){

Car vehicle = new Car(0,txtField_Manufacturer.getText(),txtField_Model.getText(),Integer.parseInt(txtField_Year.getText()),
    txtField_carType.getText(),txtField_licensePlate.getText(),txtField_Color.getText(),Integer.parseInt((txtField_Seats.getText())));
  RegisteredVehicles.getItems().add(vehicle);
  }
@FXML
  public void Delete_Button(){
  TableView<Car> table = new TableView<>();
  Delete_Button.setOnAction(e -> {
    Car selectedItem = table.getSelectionModel().getSelectedItem();
    table.getItems().remove(selectedItem);
  });
}
}
