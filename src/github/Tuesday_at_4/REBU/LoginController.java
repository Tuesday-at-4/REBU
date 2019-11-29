/**
 * *********************************************************
 * File : LoginController.Java
 * Author(s): Breanna Rhodes
 * Class : CEN 3031
 * Purpose : Allows the user to enter their information into text fields and
 * then the user is directed to their dashboard. If the user does not
 * enter sufficient credentials, the text fields will clear and a message
 * will pop-up telling the user to enter the information again. The database
 * is searched for the credentials, if they are not there,the pop-up will take place.
 * **********************************************************
 */
package github.Tuesday_at_4.REBU;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController {
  private String username;
  private String password;

  @FXML
  private AnchorPane Anchor_Login;

  @FXML
  private TextField txtField_username;

  @FXML
  private Button btn_login;

  @FXML
  private Button btn_signUp;

  @FXML
  private PasswordField password_textField;

  @FXML
  private void login(MouseEvent event) {

    //Username and Password Strings are taken from the text fields.
    username = txtField_username.getText();
    password = password_textField.getText();

    int userID = DatabaseAccessor.searchForAccount(username, password);
    if(userID != 0){
      Main.currentUser = DatabaseAccessor.getAccount(userID);
      Main.createNewScene(event, "Dashboard.fxml");
    }else {
      txtField_username.clear();
      password_textField.clear();
      Anchor_Login.getChildren().add(new Label("Oops! No account with that username/password, please try again!"));
    }
  }

  @FXML
  private void signUp(ActionEvent event) {
    Main.createNewScene(event, "AccountRegistration.fxml");
  }
}
