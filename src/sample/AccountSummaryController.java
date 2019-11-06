/*
 * Account Summary Controller
 * Displays account information of the user that is currently logged in.
 */
package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccountSummaryController {

  @FXML
  private Label lblName;

  @FXML
  private Label lblPhoneNum;

  @FXML
  private Label lblDOB;

  @FXML
  private Label lblEmail;

  @FXML
  private void returnToDashboard(Event event){
    Main.createNewScene(event, "Dashboard.fxml");
  }

  /**
   * Deletes the user's account information and redirects them to the login page.
   * @param event - passes in the mouse button click action
   */
  @FXML
  private void deleteAccount(Event event){
    Main.currentUser = null;
    Main.createNewScene(event, "Login.fxml");
  }

  /**
   * Initialize method to replace labels with corresponding values. Temporary for the sake of
   * prototype.
   */
  @FXML
  public void initialize() {
    lblName.setText(Main.currentUser.getName());
    lblEmail.setText(Main.currentUser.getEmail());
    lblPhoneNum.setText(Main.currentUser.getPhone());
    lblDOB.setText(Main.currentUser.getDateOfBirth());
  }

}

