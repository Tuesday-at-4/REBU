/***********************************************************
 * File   : PendingRidesController.Java
 * Author(s)  : Benjamin Cano
 * Class   : CEN 3031
 * Purpose : This is the controller for the Driver.fxml
 ************************************************************/
package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class PendingRides implements Initializable{
  private static String JDBC_DRIVER = "org.h2.Driver";
  private static String DB_URL = "jdbc:h2:./res/RebuDB";
  private static String PASS = "";
  private static String USER = "";

  @FXML
  private Tab DriverTab;

  @FXML
  private Button home_Button;

  @FXML
  private Button Edit_Registration;

  @FXML
  private Tab Active_Rides;

  @FXML
  private TableView<Rides> Pending_Rides;

  @FXML
  private TableColumn<Rides, String> Passenger;

  @FXML
  private TableColumn<Rides, LocalTime> Time_OfRide;

  @FXML
  private TableColumn<Rides, LocalDate> Date_OfRide;

  @FXML
  private TableColumn<Rides, String> startLocation;

  @FXML
  private TableColumn<Rides, String> endLocation;

  @FXML
  private Button Accept_Ride;

  @FXML
  private Tab accepted_Rides;


  @FXML
  private void goEdit_Registration(Event event){
    Main.createNewScene(event, "CarRegistration.fxml");
  }
  @FXML
  private void goHome(Event event){
    Main.createNewScene(event, "Dashboard.fxml");
  }

  private ObservableList<Rides> oblist2 =FXCollections.observableArrayList();
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try{
      Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
      ResultSet rs = conn.createStatement().executeQuery("select * from RIDES_LIST");
      while(rs.next()){
        oblist2.add(new Rides(rs.getInt(0),rs.getInt(0),rs.getInt(0),rs.getDate("start_date").toLocalDate(),rs.getString("start_location"),rs.getString("end_location"),
            rs.getTime("start_time").toLocalTime(),rs.getInt(0)));
      }
    }catch(SQLException ex){
      Logger.getLogger(CarRegistrationController.class.getName()).log(Level.SEVERE,null,ex);
    }

    Passenger.setCellValueFactory(new PropertyValueFactory<>("Passenger"));
    Time_OfRide.setCellValueFactory(new PropertyValueFactory<>("Time_OfRide"));
    Date_OfRide.setCellValueFactory(new PropertyValueFactory<>("Date_OfRide"));
    startLocation.setCellValueFactory(new PropertyValueFactory<>("startLocation"));
    endLocation.setCellValueFactory(new PropertyValueFactory<>("endLocation"));
    
    Pending_Rides.setItems(oblist2);
  }
}
