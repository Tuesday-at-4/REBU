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

    // storing information from text fields
    int userID = 3;
    LocalDate DOB = dateB_DOB.getValue();
    String firstName = txtField_firstName.getText();
    String lastName = txtField_lastName.getText();
    String email = txtField_email.getText();
    String phone = txtField_phone.getText();
    String username = txtField_createUsername.getText();
    String password = txtField_createPassword.getText();
    int carID = 0;
    int carID2 = 0;
    // creating a new registered user, holding their information
    Account dummy = new Account(userID, username, password, firstName, lastName, phone, email, DOB.toString(), carID,
        carID2);
    DatabaseAccessor.addAccount(dummy);
    Main.createNewScene(event, "AccountSummary.fxml");
  }
}
