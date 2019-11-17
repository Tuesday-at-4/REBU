
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AccountRegistration {
  LocalDate DOB = LocalDate.now();

  String username = "";

  String password = "";

  String firstName = "";

  String  lastName = "";

  String phone = "";

  String email = "";

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
    if(dateB_DOB.getValue().equals(0)){
      System.out.println("You didn't give us your birthday! We need to know that stuff!");
    }
    else{
      DOB = dateB_DOB.getValue();
    }
    if(txtField_firstName.getText().length() > 255){
      System.out.println("Your first name can't be that long, try again!");
    }
    else{
      firstName = txtField_firstName.getText();

    }
    if(txtField_lastName.getText().length() > 255){
      System.out.println("Your last name can't be that long, try again!");
    }
    else{
      lastName = txtField_lastName.getText();
    }
    if(txtField_email.getText().length() > 255){
      System.out.println("Your email can't be that long, try again!");
    }
    else{
      email = txtField_email.getText();

    }
    if(txtField_phone.getText().length() > 10){
      System.out.println("Your phone number should only be numbers like '1231231234'. Try again");
    }
    else{
      phone = txtField_phone.getText();
    }
    if(txtField_createUsername.getText().length() > 255){
      System.out.println("Your username name can't be that long, try again!");
    }
    else{
      username = txtField_createUsername.getText();
    }
    if(txtField_createPassword.getText().length() > 255){
      System.out.println("Your password name can't be that long, try again!");
    }
    else{
      password = txtField_createPassword.getText();
    }



    // creating a new registered user, holding their information

    Account dummy = new Account(username, password, firstName, lastName, phone, email, DOB, " ");
    dummy.printAccountDetails();
    DatabaseAccessor.addAccount(dummy);
    Main.currentUser = dummy;
    Main.createNewScene(event, "AccountSummary.fxml");
  }
}
