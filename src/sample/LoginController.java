/*
 * Login
 * Handles the login screen where users enter their registered username & password
 */
package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class LoginController {

  //Username and password to login into database.
  private static final String databaseUsername = "";
  private static final String databasePassword = "";

  /**
   * The database driver is established using a string, and an h2 driver is entered into the
   * arugment. The url can be found in the database properties. It is copied and pasted into the
   * string argument for url.
   */
  private static final String jcbdDriver = "org.h2.Driver";
  final String DB_URL = "jdbc:h2:./res/RebuDB";



  /**
   * Declaring the object Connection, imports all of the fields and methods used to establish a
   * conneciton. Declaring the object Statement will allow queries to be passed to the database, so
   * that it can be accessed.
   */
  private Connection conn = null;

  private Statement stmt = null;




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

  /**
   * Once the login button is clicked by the mouse, the data entered into the text fields
   * will be read using .getText(), and compared the information in the database
   * to verify that the information belongs to an existing user.
   */

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
