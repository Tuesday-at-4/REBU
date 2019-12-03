/**
 * ********************************************************* File : CarRegistrationController.Java
 * Author(s): Ben Cano, wilkenson germain Class : CEN 3031 Purpose : Populates the tables of regsitered vehicles of the
 * user. **********************************************************
 */
package github.Tuesday_at_4.REBU;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CarRegistrationController {

  @FXML private TableView<Car> RegisteredVehicles;

  @FXML private TableColumn<Car, String> manufacturer;

  @FXML private TableColumn<Car, String> model;

  @FXML private TableColumn<Car, Integer> year;

  @FXML private TableColumn<Car, String> carType;

  @FXML private TableColumn<Car, String> licensePlate;

  @FXML private TableColumn<Car, String> CarColor;

  @FXML private TableColumn<Car, Integer> NumSeats;

  @FXML private TableColumn<Car, Integer> car_ID;

  @FXML private TextField txtField_Manufacturer;

  @FXML private TextField txtField_Model;

  @FXML private TextField txtField_Year;

  @FXML private TextField txtField_carType;

  @FXML private TextField txtField_licensePlate;

  @FXML private Button Add_Button;

  @FXML private Button Delete_Button;

  @FXML private Button DriverPage;

  @FXML private TextField txtField_Color;

  @FXML private TextField txtField_Seats;

  @FXML
  private void goDriverPage(Event event) {
    Main.createNewScene(event, "Driver.fxml");
  }

  public void initialize() {
    manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    model.setCellValueFactory(new PropertyValueFactory<>("model"));
    year.setCellValueFactory(new PropertyValueFactory<>("year"));
    carType.setCellValueFactory(new PropertyValueFactory<>("carType"));
    licensePlate.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
    CarColor.setCellValueFactory(new PropertyValueFactory<>("CarColor"));
    NumSeats.setCellValueFactory(new PropertyValueFactory<>("NumSeats"));
    car_ID.setCellValueFactory(new PropertyValueFactory<>("carID"));
    RegisteredVehicles.setItems(observableList);
  }

  ObservableList<Car> observableList = FXCollections.observableArrayList();

  @FXML
  public void Add_Button(ActionEvent actionEvent) {
    String manufacturer = txtField_Manufacturer.getText();
    String model = txtField_Model.getText();
    int year = Integer.parseInt(txtField_Year.getText());
    String carType = txtField_carType.getText();
    String licensePlate = txtField_licensePlate.getText();
    String CarColor = txtField_Color.getText();
    int NumSeats = Integer.parseInt(txtField_Seats.getText());
    int carID = 2;
    Car dummyCar =
        new Car(carID, manufacturer, model, year, carType, licensePlate, CarColor, NumSeats);
    DatabaseAccessor.addCar(dummyCar);
    RegisteredVehicles.getItems().add(dummyCar);
  }

  @FXML
  public void Delete_Button() {
    ObservableList<Car> allCars, SingleCars;
    allCars = RegisteredVehicles.getItems();
    SingleCars = RegisteredVehicles.getSelectionModel().getSelectedItems();
    SingleCars.forEach(allCars::remove);
  }
}
