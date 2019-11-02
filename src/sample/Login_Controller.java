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
import javafx.scene.paint.Color;

public class Login_Controller {

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
  private void initialize(){
    System.out.println("Login Credentials were rejected.");
  }

  @FXML
  private void login(MouseEvent event) {
    username = txtField_username.getText();
    password = password_textField.getText();
    System.out.println("Logging " + username + " in.");
    if (username.equals("Kali") & password.equals("Ollie")){
      Account.currentUser = new Account("Kali","The Destroyer", "OfWorlds@aol.com","123-456-7890","00/00/0001", "Kali","Ollie");
      Main.createNewScene(event, "Dashboard.fxml");
      System.out.println("It matches!");
    } else {
      initialize();
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
