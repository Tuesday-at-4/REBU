/**
 * ********************************************************* File : DriverPrompt.Java 
 *Author(s) : Benjamin Cano, Breanna Rhodes
 * Class : CEN 3031
 * Purpose : Initializes the GUI program that creates a scene that
 * prompts the user to enter their driver information.
 * **********************************************************
 */
package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class DriverPrompt {



  @FXML private TextField txt_manufacturer;

  @FXML private TextField txt_model;

  @FXML private TextField txt_year;

  @FXML private TextField txt_carType;

  @FXML private TextField txt_licensePlate;

  @FXML private TextField txt_color;

  @FXML private TextField txt_seats;

  @FXML private Button OkButton;

  @FXML private Button CancelButton;

  // When cancel button is selected, it directs the user back to dashboard.
  @FXML
  private void CancelButton(Event event) {
    Main.createNewScene(event, "Dashboard.fxml");
  }

  // When Okay button is selected, it adds a car to database.
  @FXML
  private void OkButton(MouseEvent event) {
    String manufacturer = txt_manufacturer.getText();
    String model = txt_model.getText();
    int year = Integer.parseInt(txt_year.getText());
    String carType = txt_carType.getText();
    String licensePlate = txt_licensePlate.getText();
    String CarColor = txt_color.getText();
    int NumSeats = Integer.parseInt(txt_seats.getText());
    int carID = 2;
    Car dummyCar =
        new Car(carID, manufacturer, model, year, carType, licensePlate, CarColor, NumSeats);
    DatabaseAccessor.addCar(dummyCar);
    Main.createNewScene(event, "AccountSummary.fxml");
  }
    }





