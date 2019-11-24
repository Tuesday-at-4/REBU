/**
 * *********************************************************
 * File : PassengerController.Java
 * Author: Breanna Rhodes
 * Class:CEN 3031
 * Purpose : Allows the Passenger to create
 * Rides, see their pending Rides, and their accepted Rides.
 * **********************************************************
 */

package github.Tuesday_at_4.REBU;

/* Line 12-25 are necessary import statements needed to connect the code with
corresponding .fxml file(s)*/

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalTime;
import javafx.scene.input.MouseEvent;

public class PassengerController {

  /* all of the @FXML code does with controls and containers used in the Passenger.fxml  */


  @FXML private Button home_Button;

  @FXML private Button button_clearNotifications;

  @FXML private Button btnCreateRide;

  @FXML DatePicker datePicker_scheduleRide;

  @FXML TextArea textArea_displayNotifications;

  @FXML private TableColumn<?, ?>col_pendRideID;

  @FXML private TableColumn<?, ?> col_pendDriverID;

  @FXML private TableColumn<?, ?> col_pendPassengerID;

  @FXML private TableColumn<?, ?> col_pendFrom;

  @FXML private TableColumn<?, ?> col_pendTo;

  @FXML private TableColumn<?, ?> col_pendDate;

  @FXML private TableColumn<?, ?> col_pendTime;

  @FXML private TableColumn<?, ?>col_pendRideStatusID;

  @FXML private TableView<Rides> tableView_pendingRides;

  @FXML private TextField textField_startLocation;

  @FXML private TextField textField_endLocation;

  @FXML private TextField textField_startTime;


  int rideStatusID;

  /* 103-106 will be expanded upon to record scheduled rides and have them stored in the DB when it works */

  @FXML
  public void createRide(MouseEvent event) {
    Rides createRides =
        new Rides(
            datePicker_scheduleRide.getValue(),
            textField_startLocation.getText(),
            textField_endLocation.getText(),
            LocalTime.parse(textField_startTime.getText()),
            rideStatusID);
    DatabaseAccessor.addRide(createRides);
    populateTableView();


    //tableView_pendingRides.getItems().add(createRides);

  }
  public void populateTableView(){
    col_pendRideID.setCellValueFactory(new PropertyValueFactory("Ride ID"));
    col_pendDriverID.setCellValueFactory(new PropertyValueFactory("Drive ID"));
    col_pendPassengerID.setCellValueFactory(new PropertyValueFactory("Passenger ID"));
    col_pendFrom.setCellValueFactory(new PropertyValueFactory("From"));
    col_pendTo.setCellValueFactory(new PropertyValueFactory("To"));
    col_pendDate.setCellValueFactory(new PropertyValueFactory("Date"));
    col_pendTime.setCellValueFactory(new PropertyValueFactory("Time"));
    col_pendRideStatusID.setCellValueFactory(new PropertyValueFactory("Ride Status ID"));
   // ObservableList<Rides> ridesOlist = FXCollections.observableArrayList();
    ArrayList<Rides> ridesArrayList = new ArrayList<Rides>();
   // ridesArrayList.clear();
    //ridesOlist.clear();
    ridesArrayList = DatabaseAccessor.getAllRides();
    System.out.println(ridesArrayList.toString());
   // ridesOlist.addAll(ridesArrayList);
  //  tableView_pendingRides.setItems(ridesOlist);
  }

  /* The next block of code (108-111) returns you to the dashboard via the home button. */

  @FXML
  void returnHome(ActionEvent event) {
    Main.createNewScene(event, "Dashboard.fxml");
  }

  /*  The next block of code (115-118) returns you to the account summary page via the edit account button. */

  @FXML
  void clearNotifications(MouseEvent event) {
    // DatabaseAccessor.deleteNotification(int user_id, int notification_type);
    System.out.println("Notifications have been cleared");
  }

  public void initialize() {
    // ArrayList<Notification> notificationList = new
    // ArrayList<Notification>(DatabaseAccessor.getNotifications(int user_ID));
    // textArea_displayNotifications.appendText(notificationList.toString());
    populateTableView();

  }
}
