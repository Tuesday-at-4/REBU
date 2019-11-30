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

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
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

  @FXML private ChoiceBox<LocalTime> choiceBox_time;

  @FXML DatePicker datePicker_scheduleRide;

  @FXML
  RadioButton radio_am;

  @FXML RadioButton radio_pm;

  @FXML TextArea textArea_displayNotifications;

  @FXML private TableColumn<Rides, Integer> col_acceptRideID;

  @FXML private TableColumn<Rides, String> col_acceptFrom;

  @FXML private TableColumn<Rides, String> col_acceptTo;

  @FXML private TableColumn<Rides, String> col_acceptDate;

  @FXML private TableColumn<Rides, String> col_acceptTime;

  @FXML private TableColumn<Rides, Integer>col_pendRideID;

  @FXML private TableColumn<Rides, ?> col_pendDriverID;

  @FXML private TableColumn<Rides, ?> col_pendPassengerID;

  @FXML private TableColumn<Rides, String> col_pendFrom;

  @FXML private TableColumn<Rides, String> col_pendTo;

  @FXML private TableColumn<Rides, String> col_pendDate;

  @FXML private TableColumn<Rides, String> col_pendTime;

  @FXML private TableColumn<Rides, Integer>col_pendRideStatusID;

  @FXML private TableView<Rides> tableview_acceptedRides;

  @FXML private TableView<Rides> tableview_pendingRides;

  @FXML private TextField textField_startLocation;

  @FXML private TextField textField_endLocation;






  int rideStatusID;

  /* 103-106 will be expanded upon to record scheduled rides and have them stored in the DB when it works */

  @FXML
  public void createRide(MouseEvent event) {
    boolean am = radio_am.isSelected();
    boolean pm = radio_am.isSelected();
    LocalTime timeStart = choiceBox_time.getValue();
    if(am){
      timeStart = choiceBox_time.getValue();
    }
    else{
      timeStart = choiceBox_time.getValue().plusHours(12);
    }
    Rides createRides =
        new Rides(
            datePicker_scheduleRide.getValue(),
            textField_startLocation.getText(),
            textField_endLocation.getText(),
            timeStart,
            rideStatusID);
    DatabaseAccessor.addRide(createRides);
    populateTableView();
    Main.createNewScene(event, "Passenger.fxml");



  }

  public void populateTableView() {

    //These columns are going to display items from the Rides class
    col_pendRideID.setCellValueFactory(new PropertyValueFactory("ride_id"));
    col_pendTime.setCellValueFactory(new PropertyValueFactory("start_time"));
    col_pendDate.setCellValueFactory(new PropertyValueFactory("start_date"));
    col_pendFrom.setCellValueFactory(new PropertyValueFactory("start_location"));
    col_pendTo.setCellValueFactory(new PropertyValueFactory("end_location"));
    col_pendRideStatusID.setCellValueFactory(new PropertyValueFactory("ride_status_id"));
    col_pendDriverID.setCellValueFactory(new PropertyValueFactory("nameHidden"));
    col_pendPassengerID.setCellValueFactory(new PropertyValueFactory("firstName"));


    ArrayList<Rides> ridesArrayList = new ArrayList(DatabaseAccessor.getAllRides());
    tableview_pendingRides.getItems().addAll(ridesArrayList);



    //Accepted Rides
    col_acceptRideID.setCellValueFactory(new PropertyValueFactory("ride_id"));
    col_acceptFrom.setCellValueFactory(new PropertyValueFactory("start_location"));
    col_acceptTo.setCellValueFactory(new PropertyValueFactory("end_location"));
    col_acceptDate.setCellValueFactory(new PropertyValueFactory("start_date"));
    col_acceptTime.setCellValueFactory(new PropertyValueFactory("start_time"));

    //Loop to populate the accepted rides list with correct information.
    ArrayList<Rides> acceptedRidesList = new ArrayList(DatabaseAccessor.getAllRides());
    for (Rides item : acceptedRidesList) {
      int x = item.getDriver_id();
      int y = item.getPassenger();
      int z = Main.currentUser.getUserID();
      if ((z == x || z == y) && x!= 0 ) {
          tableview_acceptedRides.getItems().add(item);
      }
    }
    }

  public void populateNotificationsArea() {
    ArrayList<Notification> notificationArrayList =
        new ArrayList(DatabaseAccessor.getNotifications(Main.currentUser.getUserID()));
    for (Notification item : notificationArrayList) {
      textArea_displayNotifications.appendText(item.getNotificationText());
    }
}

public void populateChoiceBoxTime(){
    if (choiceBox_time.getItems().isEmpty()){
      ArrayList<LocalTime> startTimes = new ArrayList<>();

      for(int i=1; i<13; i++)
        for(int k=0;k<60;k=k+15)
      startTimes.add(LocalTime.of(i, k));
      choiceBox_time.getItems().addAll(startTimes);
    }else{
      choiceBox_time.show();
    }


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

  public void initialize(){
    populateNotificationsArea();
    populateTableView();
    populateChoiceBoxTime();

  }

}
