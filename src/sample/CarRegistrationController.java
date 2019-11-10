
/**
 * *********************************************************
 * File : DatabaseAccessor.Java
 * Author(s): Ben Cano
 * Class : CEN 3031
 * Purpose : Populates the tables of regsitered vehicles of the user.
 * **********************************************************
 */
package sample;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CarRegistrationController implements Initializable {
  private static String JDBC_DRIVER = "org.h2.Driver";
  private static String DB_URL = "jdbc:h2:./res/RebuDB";
  private static String PASS = "";
  private static String USER = "";

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
  private TableColumn<Car, String> CarColor;

  @FXML
  private TableColumn<Car, String> NumSeats;

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
  private ObservableList<Car> oblist =FXCollections.observableArrayList();
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try{
      Class.forName(JDBC_DRIVER);
      Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
      ResultSet rs = conn.createStatement().executeQuery("select * from CAR_LIST");
      while(rs.next()){
        oblist.add(new Car(rs.getInt(0),rs.getString("Manufacturer"),
            rs.getString("Model"),rs.getInt("year"),rs.getString("carType")
            ,rs.getString("licensePlate"),rs.getString("CarColor"),rs.getInt("NumSeats")));
      }
    }catch(SQLException | ClassNotFoundException ex){
      Logger.getLogger(CarRegistrationController.class.getName()).log(Level.SEVERE,null,ex);
    }

    manufacturer.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
    model.setCellValueFactory(new PropertyValueFactory<>("Model"));
    licensePlate.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
    year.setCellValueFactory(new PropertyValueFactory<>("year"));
    carType.setCellValueFactory(new PropertyValueFactory<>("carType"));
    NumSeats.setCellValueFactory(new PropertyValueFactory<>("NumSeats"));
    CarColor.setCellValueFactory(new PropertyValueFactory<>("CarColor"));

    RegisteredVehicles.setItems(oblist);
  }
}
