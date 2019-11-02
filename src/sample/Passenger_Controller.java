/*
 * Passenger
 * Sets up the scene for the passenger user
 */
package sample;

import java.time.LocalDate;
import java.time.LocalTime;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Passenger_Controller {

  @FXML
  TextField textFieldEndLocation;

  @FXML
  TextField textFieldStartLocation;

  @FXML
  TextField textFieldStartTime;

  @FXML
  TextField textFieldStartDate;

  @FXML
  TextArea textAreaPendingRides;

  @FXML
  public void initialize(){
    String dummyStr = "";
    String dummyString = "";
    for (Rides x: Main.pendingRides) {
      dummyStr +=
          "RideID: " + x.getRideID() + "\n\tPassenger: " + x.getPassengerName() + "\n\tFrom: "
              + x.getStartLocation() + "\n\tTo: " + x.getEndLocation() + "\n\tDate: " + x
              .getDate_OfRide() + "\n\tTime: " + x.getTime_OfRide() + "\n\n";
    }
    textAreaPendingRides.setText(dummyStr);
    if (Main.confirmedRides.isEmpty()){
      dummyString = "No Confirmed Rides";
    } else{
      for (Rides y : Main.confirmedRides) {
        dummyString += "RideID: " + y.getRideID() + "\n\tDriver" + y.getDriver() + "\n\tDate: " + y
            .getDate_OfRide() + "\n\tTime: " + y.getTime_OfRide() + "\n\n";
      }
    }
  }

  @FXML
  private void returnHome(Event event){
    Main.createNewScene(event, "Dashboard.fxml");
  }

  @FXML
  private void createRide(Event event){
    String[] timeString = textFieldStartTime.getText().split(":");
    String[] dateString = textFieldStartDate.getText().split("-");
    Rides dummy = new Rides(
        Account.currentUser.getUsername(),
        LocalTime.of(Integer.parseInt(timeString[0]),Integer.parseInt(timeString[1])),
        LocalDate.of(Integer.parseInt(dateString[0]), Integer.parseInt(dateString[1]), Integer.parseInt(dateString[2])),
        textFieldStartLocation.getText(),
        textFieldEndLocation.getText());
    Main.pendingRides.add(dummy);
    initialize();
  }
}
