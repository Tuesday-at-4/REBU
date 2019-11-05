package sample;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditAccountController {

  @FXML
  private Label lblName;

  @FXML
  private TextField txtEmail;

  @FXML
  private TextField txtPhoneNumber;

  @FXML
  private DatePicker dateB_DOB;

  // saving user account detail changes
  @FXML
  void saveChanges(MouseEvent event) {
    String email, phone;
    LocalDate DOB;

    DOB = dateB_DOB.getValue();
    email = txtEmail.getText();
    phone = txtPhoneNumber.getText();

    //Main.currentUser = new Account ()

  }

}
