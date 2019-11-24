/*************************************************************
 * File : RegsiterController.Java
 * Author(s): Breanna Rhodes
 * Class : CEN 3031
 * Purpose : Allows the user to create a new account by prompting them to enter
 * their information into text fields. The text fields are then stored into a variable
 * and the variable stored in the database.
 * ***********************************************************/
package github.Tuesday_at_4.REBU;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

public class AccountRegistration {
  LocalDate DOB = LocalDate.now();

  String username = "";

  String password = "";

  String firstName = "";

  String lastName = "";

  String phone = "";

  String email = "";

  String creditCard = "";

  @FXML
  private AnchorPane anchorAccountRegistration;

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
  private Button btn_createAccount;

  @FXML
  private TextField txtField_createUsername;


  @FXML
  private PasswordField txtField_createPassword;

  @FXML
  private DatePicker dateB_DOB;

  @FXML
  void create_account(MouseEvent event) {

    // storing information from text fields
    int userID = 0;

    DOB = dateB_DOB.getValue();


    //Handles the exception of a Null Pointer Error
    try{
     username = String.valueOf(txtField_createUsername.getText());
      password =  String.valueOf(txtField_createPassword.getText());
      firstName = String.valueOf(txtField_firstName.getText());
      lastName = String.valueOf(txtField_lastName.getText());
      phone = String.valueOf(txtField_phone.getText());
      email = String.valueOf(txtField_email.getText());
      creditCard = String.valueOf(txtField_creditCard.getText());
    }
    catch(NullPointerException e){
      JOptionPane.showMessageDialog(null, "Null pointer exception thrown");
      txtField_createUsername.clear();
      txtField_createPassword.clear();
      txtField_firstName.clear();
      txtField_lastName.clear();
      txtField_phone.clear();
      txtField_email.clear();
      txtField_creditCard.clear();
    }

    // creating a new registered user, holding their information

    Account dummy = new Account(username, password, firstName, lastName, phone, email, DOB, creditCard);
    dummy.printAccountDetails();
    DatabaseAccessor.addAccount(dummy);
    Main.currentUser = dummy;
    Main.createNewScene(event, "AccountSummary.fxml");
  }
}
