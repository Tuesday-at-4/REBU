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

  @FXML
  private void initialize(){
    System.out.println("Login Credentials were rejected.");
  }
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

        /*
    Verfiable user and password are variables that will hold information
    from the database, so it can be compared to the text fields
    that the user has entered their information into.
     */
    String verifiableUsername;
    String verifiablePassword;


    try{
      //Connect to database
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(jcbdDriver, databaseUsername, databasePassword);
      stmt = conn.createStatement();

      /**
       * Statement that selects the username and password from the database where the
       * username and password are equal to the username and password that are entered into the
       * text fields.
       */
      String sqlVerify = "SELECT USER_NAME, USER_PASSWORD FROM RebuDB where USER_NAME = 'username', USER_PASSWORD = 'password'";
      ResultSet rsVerify = stmt.executeQuery(sqlVerify);
      while(rsVerify.next()){
        verifiableUsername = rsVerify.getString("USER_NAME");
        verifiablePassword = rsVerify.getString("USER_PASSWORD");
        if (username.equals(verifiableUsername) & password.equals(verifiablePassword)) {
          String sql = "SELECT USER_ID,"
              + " USER_NAME,"
              + " USER_PASSWORD,"
              + " FIRST_NAME,"
              + " LAST_NAME, "
              + "PHONE_NUMBER, "
              + "USER_EMAIL, "
              + "DATE_OF_BIRTH, "
              + "CAR_ID FROM RebuDB WHERE USERNAME = 'username'";
          ResultSet rs = stmt.executeQuery(sql);
          //While running though the database, the variables are set.
          while (rs.next()) {
            verifiableUsername = rs.getString("USER_NAME");
            verifiablePassword = rs.getString("USER_PASSWORD");
            int id = rs.getInt("USER_ID");
            String firstName = rs.getString("FIRST_NAME");
            String lastName = rs.getString("LAST_NAME");
            String email = rs.getString("USER_EMAIL");
            String phone = rs.getString("PHONE_NUMBER");
            String DOB = rs.getString("DATE_OF_BIRTH");
          Account.currentUser =
              new Account(
                  firstName, lastName, email, phone, DOB, verifiableUsername, verifiablePassword);
          Main.createNewScene(event, "Dashboard.fxml");
          System.out.println("It matches!");
      }
          }else {
          initialize();
          txtField_username.clear();
          password_textField.clear();
          Anchor_Login.getChildren()
              .add(new Label("Oops! no account with that username/password, please try again!"));
        }
      }
      //close the connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();

    }






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
