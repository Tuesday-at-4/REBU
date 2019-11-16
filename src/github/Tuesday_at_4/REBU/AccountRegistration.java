
/**
 * *********************************************************
 * File : RegsiterController.Java
 * Author(s): Breanna Rhodes
 * Class : CEN 3031
 * Purpose : Allows the user to create a new account by prompting them to enter
 * their information into text fields. The text fields are then stored into a variable
 * and the variable stored in the database.
 * **********************************************************
 */
package github.Tuesday_at_4.REBU;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AccountRegistration {

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
    Account dummy = new Account(username, password, firstName, lastName, phone, email, DOB, "");
    DatabaseAccessor.addAccount(dummy);
    Main.currentUser = dummy;
    Main.createNewScene(event, "AccountSummary.fxml");
  }
}
