/*
 * Login
 * Handles the login screen where users enter their registered username & password
 */
package sample;

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
      Anchor_Login.getChildren().add(new Label("Oops! no account with that username/password, please try again!"));
    }
  }

  @FXML
  private void signUp(ActionEvent event) {
    Main.createNewScene(event, "Register.fxml");
  }
}
