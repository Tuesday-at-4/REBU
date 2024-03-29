package github.Tuesday_at_4.REBU;

import java.time.LocalDate;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditAccountController {

  @FXML
  private Button btnSaveChanges;

  @FXML
  private Button btnCancelAccountChanges;

  @FXML
  private TextField txtField_firstName;

  @FXML
  private TextField txtField_lastName;

  @FXML
  private TextField txtField_email;

  @FXML
  private TextField txtField_phone;

  @FXML
  private TextField txtField_creditCard;

  @FXML
  private DatePicker dateB_DOB;

  @FXML
  private TextField txtField_createUsername;

  @FXML
  private PasswordField txtField_createPassword;

  // saving user account detail changes
  @FXML
  void saveChanges(MouseEvent event) {
    String email, firstName, lastName, phone, username, password, creditCard;
    LocalDate DOB;

    // storing information from text fields
    DOB = dateB_DOB.getValue();
    firstName = txtField_firstName.getText();
    lastName = txtField_lastName.getText();
    email = txtField_email.getText();
    phone = txtField_phone.getText();
    creditCard = txtField_creditCard.getText();
    username = txtField_createUsername.getText();
    password = txtField_createPassword.getText();

    // creating a new registered user, holding their information
    Account dummyAccount = new Account (Main.currentUser.getUserID(), username, password, firstName, lastName, phone, email, DOB, creditCard);
    Main.currentUser.printAccountDetails();
    DatabaseAccessor.editAccount(dummyAccount);
    Main.currentUser = DatabaseAccessor.getAccount(Main.currentUser.getUserID());
    Main.currentUser.printAccountDetails();
    // transitions to Account Details screen (AccountSummary) from Register Account screen
    Main.createNewScene(event, "AccountSummary.fxml");
    System.out.println("You have edited your account! \nConfirm information below is correct.");
  }

  @FXML
  private void returnWithoutChanges(Event event) {
    Main.createNewScene(event, "AccountSummary.fxml");
  }

  // initializing text fields with current account details so they may be edited by user
  @FXML
  public void initialize() {
    txtField_firstName.setText(Main.currentUser.getFirstName());
    txtField_lastName.setText(Main.currentUser.getLastName());
    txtField_email.setText(Main.currentUser.getEmail());
    txtField_phone.setText(Main.currentUser.getPhone());
    txtField_creditCard.setText(Main.currentUser.getCreditCard());
    dateB_DOB.setValue(LocalDate.parse(Main.currentUser.getDateOfBirth().toString()));
    txtField_createUsername.setText(Main.currentUser.getUsername());
    txtField_createPassword.setText(Main.currentUser.getPassword());
  }
}
