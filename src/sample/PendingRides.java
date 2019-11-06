/***********************************************************
 * File   : PendingRides.Java
 * Author(s)  : Benjamin Cano
 * Class   : CEN 3031
 * Purpose : This is the controller for the Driver.fxml containing all the functionality for the driver page
 ************************************************************/
package sample;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ResourceBundle;
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


public class PendingRides implements Initializable {


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
  private Button Accept_Ride;

  @FXML
  private Tab accepted_Rides;

  @FXML
  private TableView<AcceptedRides> Rides_Accepted;

  @FXML
  private TableColumn<AcceptedRides, String> Passenger2;

  @FXML
  private TableColumn<AcceptedRides, LocalDate> current_Date;

  @FXML
  private TableColumn<AcceptedRides, LocalTime> current_Time;

  @FXML
  private Button decline_Ride;

  @FXML
  private TableColumn<Rides, String> startLocation;

  @FXML
  private TableColumn<Rides, String> endLocation;

  @FXML
  private TableColumn<AcceptedRides, String> start_Loc;

  @FXML
  private TableColumn<AcceptedRides, String> end_Loc;
  @FXML
  public ObservableList<AcceptedRides> AcceptedRides = FXCollections.observableArrayList(
      new AcceptedRides("breanna", LocalTime.of(4, 45), LocalDate.of(2019, Month.OCTOBER, 20),"Orlando","Miami"));
  @FXML
  public ObservableList<Rides> PendingRides = FXCollections.observableArrayList(
      new Rides("J Cole", LocalTime.of(12, 45),LocalDate.of(2019,Month.NOVEMBER,12),"Fort Myers","Orlando"),
      new Rides("Kendrick Lamar", LocalTime.of(5, 35),LocalDate.of(2019,Month.NOVEMBER,23),"Sarasota","Tallahassee"),
      new Rides("LeBron James", LocalTime.of(6, 45), LocalDate.of(2019,Month.NOVEMBER,30),"Jacksonville","Gainesville"));
  public void initialize(URL url, ResourceBundle rb) {
    Passenger.setCellValueFactory(new PropertyValueFactory<Rides, String>("passengerName"));
    Date_OfRide.setCellValueFactory(new PropertyValueFactory<Rides, LocalDate>("Date_OfRide"));
    Time_OfRide.setCellValueFactory(new PropertyValueFactory<Rides, LocalTime>("Time_OfRide"));
    startLocation.setCellValueFactory(new PropertyValueFactory<Rides, String>("startLocation"));
    endLocation.setCellValueFactory(new PropertyValueFactory<Rides, String>("endLocation"));

    Passenger2.setCellValueFactory(new PropertyValueFactory<AcceptedRides, String>("Passenger2"));
    current_Date.setCellValueFactory(new PropertyValueFactory<AcceptedRides, LocalDate>("Current_Date"));
    current_Time.setCellValueFactory(new PropertyValueFactory<AcceptedRides, LocalTime>("Current_Time"));
    start_Loc.setCellValueFactory(new PropertyValueFactory<AcceptedRides, String>("start_Loc"));
    end_Loc.setCellValueFactory(new PropertyValueFactory<AcceptedRides, String>("end_Loc"));

    Rides_Accepted.setItems(AcceptedRides);
    Pending_Rides.setItems(PendingRides);
  }

  @FXML
  private void goEdit_Registration(Event event){
    Main.createNewScene(event, "CarRegistration.fxml");
  }
  @FXML
  private void goHome(Event event){
    Main.createNewScene(event, "Dashboard.fxml");
  }
}
