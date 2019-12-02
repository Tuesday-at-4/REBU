package github.Tuesday_at_4.REBU;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class DriverController {

  @FXML private Tab DriverTab;

  @FXML private Label lblDriver;

  @FXML private Button home_Button;

  @FXML private Button Edit_Registration;

  @FXML private Button button_completeRide;

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
  private Button ClearDriverNotifications;

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

    ride_ID.setCellValueFactory(new PropertyValueFactory<>("ride_id"));
    start_date.setCellValueFactory(new PropertyValueFactory<>("start_date"));
    start_time.setCellValueFactory(new PropertyValueFactory<>("start_time"));
    startLocation.setCellValueFactory(new PropertyValueFactory<>("start_location"));
    endLocation.setCellValueFactory(new PropertyValueFactory<>("end_location"));
    passenger_ID.setCellValueFactory(new PropertyValueFactory<>("passenger_id"));
    RideStatus.setCellValueFactory(new PropertyValueFactory<>("ride_status_id"));

    ArrayList<Rides> pendingRidesArrayList = new ArrayList<>();
    for (Rides x : DatabaseAccessor.getAllRides()) {
      if (x.getRide_status_id() == 1
          && x.getDriver_id() == 0
          && Main.currentUser.getUserID() != x.getPassenger_id()) {
        pendingRidesArrayList.add(x);
      }
    }

    DriverAvailableRides.getItems().addAll(pendingRidesArrayList);

    AcceptedTime.setCellValueFactory(new PropertyValueFactory<>("start_time"));
    AcceptedDate.setCellValueFactory(new PropertyValueFactory<>("start_date"));
    AcceptedStartLocation.setCellValueFactory(new PropertyValueFactory<>("start_location"));
    AcceptedEndLocation.setCellValueFactory(new PropertyValueFactory<>("end_location"));
    AcceptedRideID.setCellValueFactory(new PropertyValueFactory<>("ride_id"));
    AcceptedPassengerID.setCellValueFactory(new PropertyValueFactory<>("passenger_id"));
    AcceptedRideStatus.setCellValueFactory(new PropertyValueFactory<>("ride_status_id"));

    ArrayList<Rides> acceptedRidesArrayList = new ArrayList<>();
    for (Rides item : DatabaseAccessor.getAllRides()) {
      if (item.getRide_status_id() == 0
          && item.getDriver_id() == Main.currentUser.getUserID()
          && Main.currentUser.getUserID() != item.getPassenger_id());
    }
    Rides_Accepted.getItems().addAll(acceptedRidesArrayList);
  }

  public void Cancel_Ride(ActionEvent actionEvent) {
    ObservableList<Rides> allRides, SingleRides;
    allRides = Rides_Accepted.getItems();
    SingleRides = Rides_Accepted.getSelectionModel().getSelectedItems();
    SingleRides.forEach(allRides::remove);
  }

  @FXML
  void completeRide(MouseEvent event) {
    System.out.println("You have completed a ride!");

    Rides completedRide = Rides_Accepted.getSelectionModel().getSelectedItem();
    DatabaseAccessor.changeRideStatus(Main.currentUser.getUserID(), completedRide.getRide_id(), 2);
    Rides_Accepted.getItems().remove(completedRide);

  }

  public void Accept_Ride() {
    Rides selection = DriverAvailableRides.getSelectionModel().getSelectedItem();
    DatabaseAccessor.addDriverToRide(selection.getRide_id(), Main.currentUser.getUserID());
    DatabaseAccessor.changeRideStatus(Main.currentUser.getUserID(), selection.getRide_id(), 0);
    if (selection != null) {
      Rides_Accepted.getItems()
          .add(
              new Rides(
                  selection.getPassenger_id(),
                  selection.getDriver_id(),
                  selection.getStart_time(),
                  selection.getStart_date(),
                  selection.getStart_location(),
                  selection.getEnd_location(),
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
    ArrayList<Notification> notificationArrayList = DatabaseAccessor.getNotifications(Main.currentUser.getUserID());
    // While going through the list, the text area will populate with the notifications.
    for (Notification item : notificationArrayList) {
      if (item.getNotificationType() == 1) { // If the note is for the passenger(2)
        textAreaDriver.appendText(item.getNotificationText());
      }
    }
  }
}
