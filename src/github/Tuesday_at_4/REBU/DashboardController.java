/***********************************************************
 * File   : DashboardController.Java
 * Author(s)  : Michael Carracino
 * Class   : CEN 3031
 * Purpose : Handles the events on the hompepage/dashboard
 ************************************************************/
package github.Tuesday_at_4.REBU;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class DashboardController {

    @FXML
    private Button button_logout;

    @FXML
    private TextArea textAreaDashboard;

    public void initialize(){
      populateNotificationsArea();
    }

  public void populateNotificationsArea() {
    //Arraylist made of the notifications for this particular user.
    ArrayList<Notification> notificationArrayList = DatabaseAccessor.getNotifications(Main.currentUser.getUserID());
    //While going through the list, the text area will populate with the notifications.
    for (Notification item : notificationArrayList) {
      if (item.getNotificationType() == 0){ //If the note is for the passenger(2)
        textAreaDashboard.appendText(item.getNotificationText());
      }
    }
  }

    public void handlePassengerButton(ActionEvent event){
        Main.createNewScene(event,"Passenger.FXML");
    }

  @FXML
  void logout(MouseEvent event) {
    Main.createNewScene(event,"Login.FXML");

  }

    @FXML
    private void handleAccountButton(ActionEvent event){
        Main.createNewScene(event,"AccountSummary.FXML");
    }

    @FXML
    private void handleDriverButton(ActionEvent event){
        Main.createNewScene(event,"Driver.FXML");
    }
}
