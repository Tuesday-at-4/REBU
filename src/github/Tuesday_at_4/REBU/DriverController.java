package github.Tuesday_at_4.REBU;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DriverController {

  @FXML private Tab DriverTab;

  @FXML private Button home_Button;

  @FXML private Button Edit_Registration;

  @FXML private Tab Active_Rides;

  @FXML private TableView<Rides> DriverPendingRides;

  @FXML private TableColumn<?, ?> start_time;

  @FXML private TableColumn<?, ?> start_date;

  @FXML private TableColumn<?, ?> startLocation;

  @FXML private TableColumn<?, ?> endLocation;

  @FXML private TableColumn<?, ?> ride_ID;

  @FXML private TableColumn<?, ?> passenger_ID;

  @FXML private TableColumn<?, ?> RideStatus;

  @FXML private Button Accept_Ride;

  @FXML private Button DeclineRide;

  @FXML private Tab accepted_Rides;

  @FXML private TableView<?> Rides_Accepted;

  @FXML private TableColumn<?, ?> AcceptedTime;

  @FXML private TableColumn<?, ?> AcceptedDate;

  @FXML private TableColumn<?, ?> AcceptedStartLocation;

  @FXML private TableColumn<?, ?> AcceptedEndLocation;

  @FXML private TableColumn<?, ?> AcceptedRideID;

  @FXML private TableColumn<?, ?> AcceptedPassengerID;

  @FXML private TableColumn<?, ?> AcceptedRideStatus;

  @FXML private Button CancelRide;

  @FXML
  private void goEdit_Registration(Event event) {
    Main.createNewScene(event, "CarRegistration.fxml");
  }

  @FXML
  private void goHome(Event event) {
    Main.createNewScene(event, "Dashboard.fxml");
  }

  public void initialize() {
    ride_ID.setCellValueFactory(new PropertyValueFactory<>("rideID"));
    start_date.setCellValueFactory(new PropertyValueFactory<>("Date_OfRide"));
    start_time.setCellValueFactory(new PropertyValueFactory<>("Time_OfRide"));
    startLocation.setCellValueFactory(new PropertyValueFactory<>("startLocation"));
    endLocation.setCellValueFactory(new PropertyValueFactory<>("endLocation"));
    passenger_ID.setCellValueFactory(new PropertyValueFactory<>("passenger"));
    RideStatus.setCellValueFactory(new PropertyValueFactory<>("ride_status_id"));


    
    DriverPendingRides.setItems(oblist);
  }

  private ObservableList<Rides> oblist = FXCollections.observableArrayList();

  public void Cancel_Ride(ActionEvent actionEvent) {
    ObservableList<Rides> allRides, SingleRides;
    allRides = DriverPendingRides.getItems();
    SingleRides = DriverPendingRides.getSelectionModel().getSelectedItems();
    SingleRides.forEach(allRides::remove);
  }

  public void Decline_Ride(ActionEvent actionEvent) {
    ObservableList<Rides> allRides, SingleRides;
    allRides = DriverPendingRides.getItems();
    SingleRides = DriverPendingRides.getSelectionModel().getSelectedItems();
    SingleRides.forEach(allRides::remove);
  }
}
