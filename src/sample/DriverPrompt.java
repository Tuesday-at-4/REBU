package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DriverPrompt {

    @FXML
    private TextField getManufacturer;

    @FXML
    private TextField getModel;

    @FXML
    private TextField getYear;

    @FXML
    private TextField txt_carType;

    @FXML
    private TextField txt_licensePlate;

    @FXML
    private Button OkButton;

    @FXML
    private Button CancelButton;

    @FXML
    private TextField getColor;

    @FXML
    private TextField getSeats;


  @FXML
  private void OkButton(Event event){
    Main.createNewScene(event, "Driver.fxml");
  }
  @FXML
  private void CancelButton(Event event){
    Main.createNewScene(event, "Dashboard.fxml");
  }
}
