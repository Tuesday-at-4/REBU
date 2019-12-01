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

  @FXML
  private DatePicker datePicker_scheduleRide;

  @FXML
  private RadioButton radio_am;

  @FXML
  private RadioButton radio_pm;

  @FXML
  private TextArea textArea_displayNotifications;

  @FXML
  private TableColumn<Rides, Integer> col_acceptRideID;

  @FXML
  private TableColumn<Rides, String> col_acceptFrom;

  @FXML private TableColumn<Rides, String> col_acceptTo;

  @FXML private TableColumn<Rides, String> col_acceptDriver;

  @FXML private TableColumn<Rides, String> col_acceptDate;

  @FXML private TableColumn<Rides, String> col_acceptTime;

  @FXML private TableColumn<Rides, Integer>col_pendRideID;

  @FXML private TableColumn<Rides, ?> col_pendDriverID;

  @FXML private TableColumn<Rides, String> col_pendFrom;

  @FXML private TableColumn<Rides, String> col_pendTo;

  @FXML private TableColumn<Rides, String> col_pendDate;

  @FXML private TableColumn<Rides, String> col_pendTime;

  @FXML private TableColumn<Rides, String>col_pendRideStatusID;

  @FXML private TableView<Rides> tableview_acceptedRides;

  @FXML private TableView<Rides> tableview_pendingRides;

  @FXML private TextField textField_startLocation;

  @FXML private TextField textField_endLocation;

  /* 103-106 will be expanded upon to record scheduled rides and have them stored in the DB when it works */

  @FXML
  public void createRide(MouseEvent event) {

    //If the am radio button is selected, the value will be true. Same for the pm radio button.
    boolean am = radio_am.isSelected();
    boolean pm = radio_am.isSelected();
    //Initialize the time selected by the user with its value chosen from the choice box.
    LocalTime timeStart = choiceBox_time.getValue();

    //If the am radio button is selected, then the time selected stays the same.
    if(am){
      timeStart = choiceBox_time.getValue();
    }
    //If the pm button is selected, then the time will have 12 hours added to it, for military time.
    else{
      timeStart = choiceBox_time.getValue().plusHours(12);
    }
    Rides createRides =
        new Rides(
            Main.currentUser.getUserID(),
            0,
            timeStart,
            datePicker_scheduleRide.getValue(),
            textField_startLocation.getText(),
            textField_endLocation.getText(),
            1); //This sets the ride as pending
    createRides.printRide();
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
    col_pendRideStatusID.setCellValueFactory(new PropertyValueFactory("rideStatusComment"));
    col_pendDriverID.setCellValueFactory(new PropertyValueFactory("driverName"));

    //Array list to hold the Rides being put into the tableview.
    ArrayList<Rides> pendingRidesArrayList = new ArrayList<>();
    for (Rides x: DatabaseAccessor.getAllRides()){
      //Test if the ride is pending, there is no driver, and the passenger equals the user
      if (x.getRide_status_id() == 1 && x.getDriver_id() == 0 && Main.currentUser.getUserID() == x.getPassenger()){
        pendingRidesArrayList.add(x);
      }
    }

    //Populate Table view for pending rides.
    tableview_pendingRides.getItems().addAll(pendingRidesArrayList);

    //Columns for Accepted rides tableview.
    col_acceptRideID.setCellValueFactory(new PropertyValueFactory("ride_id"));
    col_acceptFrom.setCellValueFactory(new PropertyValueFactory("start_location"));
    col_acceptTo.setCellValueFactory(new PropertyValueFactory("end_location"));
    col_acceptDate.setCellValueFactory(new PropertyValueFactory("start_date"));
    col_acceptTime.setCellValueFactory(new PropertyValueFactory("start_time"));
    col_acceptDriver.setCellValueFactory(new PropertyValueFactory("driverName"));

    //Array list to hold the rides that have been flagged as accepted.
    ArrayList<Rides> acceptedRidesArrayList = new ArrayList(DatabaseAccessor.getAllRides());
    for (Rides item : acceptedRidesArrayList) {
      if(item.getRide_status_id() == 0 && item.getDriver_id() != 0 && Main.currentUser.getUserID() == item.getPassenger())
          tableview_acceptedRides.getItems().add(item);
      }
    }

  public void populateNotificationsArea() {
    //Arraylist made of the notifications for this particular user.
    ArrayList<Notification> notificationArrayList =
        new ArrayList(DatabaseAccessor.getNotifications(Main.currentUser.getUserID()));
    //While going through the list, the text area will populate with the notifications.
    for (Notification item : notificationArrayList) {
      if (item.getNotificationType() == 2){ //If the note is for the passenger(2)
        textArea_displayNotifications.appendText(item.getNotificationText());
      }
    }
}

  public void populateChoiceBoxTime(){
    //If the choice box is empty, then it will proceed to get filled.
    if (choiceBox_time.getItems().isEmpty()){
      //An array is made to hold the times of the day, for the user to select.
      ArrayList<LocalTime> startTimes = new ArrayList<>();

      //First loop is going to be for the hours.
      for(int i = 1; i < 13; i++)
        //Second loop is going to be for the minutes.
        for(int k = 0; k < 60; k = k + 15)
      //The choice box will run through the loop.
      startTimes.add(LocalTime.of(i, k));
      choiceBox_time.getItems().addAll(startTimes);
    }else{
      //If the choicebox already has items in it, it will display the items to the user.
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

    //The text area is cleared.
    textArea_displayNotifications.clear();
   System.out.println("Notifications have been cleared");

  }

  public void initialize(){
    populateNotificationsArea();
    populateTableView();
    populateChoiceBoxTime();
  }
}
