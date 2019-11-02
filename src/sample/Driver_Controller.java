/*
 * Driver
 * Sets up the driver scene
 */
package sample;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

public class Driver_Controller {

  @FXML
  private Label Rating;

  @FXML
  private Label label_Name;

  @FXML
  private Label label_Age;

  @FXML
  private TableView<Car> RegisteredVehicles;


  @FXML
  private TableColumn<Car, String> manufacturer;

  @FXML
  private TableColumn<Car, String> model;

  @FXML
  private TableColumn<Car, String> year;

  @FXML
  private TableColumn<Car, String> carType;

  @FXML
  private TableColumn<Car, String> licensePlate;

  @FXML
  private TextField getManufacturer;

  @FXML
  private TextField getModel;

  @FXML
  private TextField getYear;

  @FXML
  private TextField txt_carType;

  @FXML
  private TextField txt_licensePlate;

  @FXML
  private Button Add_Button;

  @FXML
  private Button Delete_Button;

  @FXML
  private Button home_Button;

  @FXML
  public void initialize(URL url, ResourceBundle rb) {
    TableColumn manufacturer = new TableColumn("Manufacturer");
    TableColumn model = new TableColumn("Model");
    TableColumn year = new TableColumn("Year");
    TableColumn carType = new TableColumn("Car Type");
    TableColumn licensePlate = new TableColumn("License Plate");
    RegisteredVehicles.getColumns().addAll(manufacturer, model, year, carType, licensePlate);

    final ObservableList<Car> data = FXCollections.observableArrayList(
        new Car("Chevrolet", "Camaro", "2017", "Coupe", "900NGM"),
        new Car("Dodge", "Challenger", "2019", "Coupe", "NFS200"),
        new Car("Ford", "Mustang", "2015", "Coupe", "FX2000"),
        new Car("Toyota", "Camry", "2016", "Sedan", "WTX678"),
        new Car("Chevrolet", "Tahoe", "2008", "SUV", "RTX206"),
        new Car("Nissan", "Maxima", "2018", "Sedan", "ALL4YU"));

    manufacturer.setCellValueFactory(new PropertyValueFactory<Car, String>("Manufacturer"));
    model.setCellValueFactory(new PropertyValueFactory<Car, String>("Model"));
    year.setCellValueFactory(new PropertyValueFactory<Car, String>("Year"));
    carType.setCellValueFactory(new PropertyValueFactory<Car, String>("Car Type"));
    licensePlate.setCellValueFactory(new PropertyValueFactory<Car, String>("License Plate"));

    RegisteredVehicles.setItems(data);
  }

  @FXML
  void Add_Vehicle(MouseEvent event) {
    System.out.println("Vehicle has been registered.");
  }

  @FXML
  void Delete_Item(MouseEvent event) {
    System.out.println("Item has been deleted.");
  }

  @FXML
  void DisplayVehicles(ActionEvent event) {
  }

  @FXML
  void Display_actRides(ActionEvent event) {

  }

  @FXML
  void Display_schRides(ActionEvent event) {

  }

  @FXML
  void add_Manu(InputMethodEvent event) {

  }

  @FXML
  void add_Model(InputMethodEvent event) {

  }

  @FXML
  void add_Year(InputMethodEvent event) {

  }

  @FXML
  void scheduleRide(MouseEvent event) {
    System.out.println("Active ride has been added.");
  }
  @FXML
  void delete_Ride(MouseEvent event) {
    System.out.println("Active ride has been deleted.");
  }
  @FXML
  void Decline_Ride(MouseEvent event) {
    System.out.println("Ride has been Declined.");
  }
  @FXML
  void Accept_Ride(MouseEvent event) {
    System.out.println("Ride has been Accepted.");
  }

  @FXML
  private void goHome(Event event){
    Main.createNewScene(event, "Dashboard.fxml");
  }
}
