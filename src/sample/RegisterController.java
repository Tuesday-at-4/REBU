/*
 * Register
 * Registers a new account for a user with the information inputted in the provided text fields.
 */
package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.xml.crypto.Data;

public class RegisterController {

  @FXML private TextField txtField_firstName;

  @FXML private TextField txtField_lastName;

  @FXML private TextField txtField_email;

  @FXML private TextField txtField_phone;

  @FXML private Button btn_createAccount;

  @FXML private TextField txtField_createUsername;

  @FXML private PasswordField txtField_createPassword;

  @FXML private DatePicker dateB_DOB;

  @FXML
  void create_account(MouseEvent event) {
    String email, firstName, lastName, phone, username, password;
    LocalDate DOB;

    // storing information from text fields
    int userID = 3;
    DOB = dateB_DOB.getValue();
    String DOBstring = DOB.toString();
    firstName = txtField_firstName.getText();
    lastName = txtField_lastName.getText();
    email = txtField_email.getText();
    phone = txtField_phone.getText();
    username = txtField_createUsername.getText();
    password = txtField_createPassword.getText();
    int carID = 0;
    int carID2 = 0;
    // creating a new registered user, holding their information
    Account dummy = new Account(userID, firstName, lastName, email, phone, DOBstring, username, password, carID,
        carID2);
    DatabaseAccessor.addAccount(dummy);
    Main.createNewScene(event, "Account_Summary.fxml");


  }
}
