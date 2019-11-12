/***********************************************************
 * File   : AccountSummaryController.java
 * Author(s)  : Sabrina Kienholz
 * Class   : CEN 3031
 * Purpose : Displays account info of the user that is
 * currently logged in.
 ************************************************************/

package github.Tuesday_at_4.REBU;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
  private Label lblUsername;

  @FXML
  private Button btnEditAccount;

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
    DatabaseAccessor.deleteAccount(Main.currentUser.getUser_id());
    Main.createNewScene(event, "Login.fxml");
  }

  @FXML
  private void editAccount(Event event) {
    Main.createNewScene(event, "EditAccount.fxml");
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
    lblUsername.setText(Main.currentUser.getUsername());
  }
}

