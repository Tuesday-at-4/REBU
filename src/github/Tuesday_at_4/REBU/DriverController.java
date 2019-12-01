package github.Tuesday_at_4.REBU;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class DriverController {

  @FXML private Tab DriverTab;

  @FXML private Button home_Button;

  @FXML private Button Edit_Registration;

  @FXML private Tab Available_Rides;

  @FXML private TableView<Rides> DriverAvailableRides;

  @FXML private TableColumn<?, ?> start_time;

  @FXML private TableColumn<?, ?> start_date;

  @FXML private TableColumn<?, ?> startLocation;

  @FXML private TableColumn<?, ?> endLocation;

  @FXML private TableColumn<?, ?> ride_ID;

  @FXML private TableColumn<?, ?> passenger_ID;

  @FXML private TableColumn<?, ?> RideStatus;

  @FXML private Button Accept_Ride;

  @FXML private Tab accepted_Rides;

  @FXML private TableView<Rides> Rides_Accepted;

  @FXML private TableColumn<?, ?> AcceptedTime;

  @FXML private TableColumn<?, ?> AcceptedDate;

  @FXML private TableColumn<?, ?> AcceptedStartLocation;

  @FXML private TableColumn<?, ?> AcceptedEndLocation;

  @FXML private TableColumn<?, ?> AcceptedRideID;

  @FXML private TableColumn<?, ?> AcceptedPassengerID;

  @FXML private TableColumn<?, ?> AcceptedRideStatus;

  @FXML private Button CancelRide;

  @FXML private TextArea textAreaDriver;

  @FXML
  private void goEdit_Registration(Event event) {
    Main.createNewScene(event, "CarRegistration.fxml");
  }

  @FXML
  private void goHome(Event event) {
    Main.createNewScene(event, "Dashboard.fxml");
  }

  public void initialize() {
    populateNotificationsArea();
    FillInTableView();
  }

  public void FillInTableView() {

    ride_ID.setCellValueFactory(new PropertyValueFactory<>("rideID"));
    start_date.setCellValueFactory(new PropertyValueFactory<>("Date_OfRide"));
    start_time.setCellValueFactory(new PropertyValueFactory<>("Time_OfRide"));
    startLocation.setCellValueFactory(new PropertyValueFactory<>("startLocation"));
    endLocation.setCellValueFactory(new PropertyValueFactory<>("endLocation"));
    passenger_ID.setCellValueFactory(new PropertyValueFactory<>("passenger"));
    RideStatus.setCellValueFactory(new PropertyValueFactory<>("ride_status_id"));

    ArrayList<Rides> pendingRidesArrayList = new ArrayList<>();
    for (Rides x : DatabaseAccessor.getAllRides()) {
      if (x.getRide_status_id() == 1
          && x.getDriver_id() == 0
          && Main.currentUser.getUserID() == x.getPassenger()) {
        pendingRidesArrayList.add(x);
      }
      DriverAvailableRides.getItems().addAll(pendingRidesArrayList);

      AcceptedTime.setCellValueFactory(new PropertyValueFactory<>("Time_OfRide"));
      AcceptedDate.setCellValueFactory(new PropertyValueFactory<>("Date_OfRide"));
      AcceptedStartLocation.setCellValueFactory(new PropertyValueFactory<>("startLocation"));
      AcceptedEndLocation.setCellValueFactory(new PropertyValueFactory<>("endLocation"));
      AcceptedRideID.setCellValueFactory(new PropertyValueFactory<>("rideID"));
      AcceptedPassengerID.setCellValueFactory(new PropertyValueFactory<>("passenger"));
      AcceptedRideStatus.setCellValueFactory(new PropertyValueFactory<>("ride_status_id"));

      ArrayList<Rides> acceptedRidesArrayList = new ArrayList(DatabaseAccessor.getAllRides());
      for (Rides item : acceptedRidesArrayList) {
        if (item.getRide_status_id() == 0
            && item.getDriver_id() != 0
            && Main.currentUser.getUserID() == item.getPassenger())
          Rides_Accepted.getItems().add(item);
      }
    }
  }

  public void Cancel_Ride(ActionEvent actionEvent) {
    ObservableList<Rides> allRides, SingleRides;
    allRides = Rides_Accepted.getItems();
    SingleRides = Rides_Accepted.getSelectionModel().getSelectedItems();
    SingleRides.forEach(allRides::remove);
  }

  public void Accept_Ride() {
    Rides selection = DriverAvailableRides.getSelectionModel().getSelectedItem();
    if (selection != null) {
      Rides_Accepted.getItems()
          .add(
              new Rides(
                  selection.getPassenger(),
                  selection.getDriver(),
                  selection.getTime_OfRide(),
                  selection.getDate_OfRide(),
                  selection.getStartLocation(),
                  selection.getEndLocation(),
                  selection.getRide_status_id()));
      ObservableList<Rides> allRides, SingleRides;
      allRides = DriverAvailableRides.getItems();
      SingleRides = DriverAvailableRides.getSelectionModel().getSelectedItems();
      SingleRides.forEach(allRides::remove);
    }
  }

  public void ClearNotificationsButton(ActionEvent event) {
    textAreaDriver.clear();
    System.out.println("Notifications have been cleared");
  }

  private void populateNotificationsArea() {
    // Arraylist made of the notifications for this particular user.
    ArrayList<Notification> notificationArrayList =
        DatabaseAccessor.getNotifications(Main.currentUser.getUserID());
    // While going through the list, the text area will populate with the notifications.
    for (Notification item : notificationArrayList) {
      if (item.getNotificationType() == 1) { // If the note is for the passenger(2)
        textAreaDriver.appendText(item.getNotificationText());
      }
    }
  }
}
