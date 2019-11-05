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


public class Pending_Rides implements Initializable {


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
  private TableView<Accepted_Rides> Rides_Accepted;

  @FXML
  private TableColumn<Accepted_Rides, String> Passenger2;

  @FXML
  private TableColumn<Accepted_Rides, LocalDate> current_Date;

  @FXML
  private TableColumn<Accepted_Rides, LocalTime> current_Time;

  @FXML
  private Button decline_Ride;

  @FXML
  public ObservableList<Accepted_Rides> AcceptedRides = FXCollections.observableArrayList(new Accepted_Rides("breanna", LocalTime.of(4, 45), LocalDate.of(2019, Month.OCTOBER, 20)));
 @FXML
  public ObservableList<Rides> PendingRides = FXCollections.observableArrayList(
      new Rides("J Cole", LocalTime.of(12, 45),LocalDate.of(2019,Month.NOVEMBER,12)),
    new Rides("Kendrick Lamar", LocalTime.of(5, 35),LocalDate.of(2019,Month.NOVEMBER,23)),
    new Rides("LeBron James", LocalTime.of(6, 45), LocalDate.of(2019,Month.NOVEMBER,30)));
  public void initialize(URL url, ResourceBundle rb) {
    Passenger.setCellValueFactory(new PropertyValueFactory<Rides, String>("passengerName"));
    Date_OfRide.setCellValueFactory(new PropertyValueFactory<Rides, LocalDate>("Date_OfRide"));
    Time_OfRide.setCellValueFactory(new PropertyValueFactory<Rides, LocalTime>("Time_OfRide"));

    Passenger2.setCellValueFactory(new PropertyValueFactory<Accepted_Rides, String>("Passenger2"));
    current_Date.setCellValueFactory(new PropertyValueFactory<Accepted_Rides, LocalDate>("Current_Date"));
    current_Time.setCellValueFactory(new PropertyValueFactory<Accepted_Rides, LocalTime>("Current_Time"));

    Rides_Accepted.setItems(AcceptedRides);
    Pending_Rides.setItems(PendingRides);
  }
  @FXML
  private void goEdit_Registration(Event event){
    Main.createNewScene(event, "CarRegistration.fxml");
  }
}
