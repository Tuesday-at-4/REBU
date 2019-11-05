/*
 * Driver
 * Sets up the driver scene
 */
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DriverController {

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
  private TableColumn<Car, String> CarColor;

  @FXML
  private TableColumn<Car, String> NumSeats;

  @FXML
  private TableColumn<Car, String> licenseNum;

  @FXML
  private TextField getColor;

  @FXML
  private TextField getSeats;

  @FXML
  private TextField getLicenseNum;

  public void initialize(){
    ObservableList<Car> data = populateList();
    manufacturer.setCellValueFactory(new PropertyValueFactory<Car, String>("Manufacturer"));
    model.setCellValueFactory(new PropertyValueFactory<Car, String>("Model"));
    year.setCellValueFactory(new PropertyValueFactory<Car, String>("Year"));
    carType.setCellValueFactory(new PropertyValueFactory<Car, String>("CarType"));
    licensePlate.setCellValueFactory(new PropertyValueFactory<Car, String>("LicensePlate"));
    CarColor.setCellValueFactory(new PropertyValueFactory<Car, String>("CarColor"));
    NumSeats.setCellValueFactory(new PropertyValueFactory<Car, String>("NumSeats"));


    RegisteredVehicles.setItems(data);
  }
  public static ObservableList<Car> populateList() {
    return FXCollections.observableArrayList(
        new Car("Chevrolet", "Camaro", "2017", "Coupe", "900NGM","White","3"),
        new Car("Dodge", "Challenger", "2019", "Coupe", "NFS200","Black","4"),
        new Car("Ford", "Mustang", "2015", "Coupe", "FX2000","Red","1"),
        new Car("Toyota", "Camry", "2016", "Sedan", "WTX678","Blue","4"),
        new Car("Chevrolet", "Tahoe", "2008", "SUV", "RTX206","Silver","7"),
        new Car("Nissan", "Maxima", "2018", "Sedan", "ALL4YU","Gold","4"));


  }
}
  //@FXML
  //private void goHome(Event event){
   // Main.createNewScene(event, "Dashboard.fxml");
  //}
//}
