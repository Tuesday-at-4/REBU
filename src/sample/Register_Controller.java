/*
 * Register
 * Registers a new account for a user with the information inputted in the provided text fields.
 */
package sample;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Register_Controller {

  @FXML
  private TextField txtField_firstName;

  @FXML
  private TextField txtField_lastName;

  @FXML
  private TextField txtField_email;

  @FXML
  private TextField txtField_phone;

  @FXML
  private Button btn_createAccount;

  @FXML
  private TextField txtField_createUsername;

  @FXML
  private PasswordField txtField_createPassword;

  @FXML
  private DatePicker dateB_DOB;

  @FXML
  void create_account(MouseEvent event) {
    String email, firstName, lastName, phone, username, password;
    LocalDate DOB;

    // storing information from text fields
    DOB = dateB_DOB.getValue();
    firstName = txtField_firstName.getText();
    lastName = txtField_lastName.getText();
    email = txtField_email.getText();
    phone = txtField_phone.getText();
    username = txtField_createUsername.getText();
    password = txtField_createPassword.getText();

    // creating a new registered user, holding their information
    Account.currentUser = new Account (firstName, lastName, email, phone, DOB.toString(), username,
        password);

    // transitions to Account Details screen (AccountSummary) from Register Account screen
    Main.createNewScene(event, "Account_Summary.fxml");

    System.out.println("You have created an account! \nConfirm information below is correct.");

    System.out.println(
        "\nFirst name: " + firstName +
            "\nLast name: " + lastName +
            "\nEmail: " + email +
            "\nPhone: " + phone +
            "\nDate of Birth: " + DOB +
            "\nUsername: " + username +
            "\nPassword: " + password);
  }
}
