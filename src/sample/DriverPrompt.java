/**
 * ********************************************************* File : DriverPrompt.Java Author(s) :
 * Benjamin Cano Class : CEN 3031 Purpose : Initializes the GUI program that creates a scene that
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

  private static String PASS = "";
  private static String USER = "";

  /**
   * The database driver is established using a string, and an h2 driver is entered into the
   * arugment. The url can be found in the database properties. It is copied and pasted into the
   * string argument for url.
   */
  private static String JDBC_DRIVER = "org.h2.Driver";

  private static String DB_URL = "jdbc:h2:./res/RebuDB";

  /**
   * Declaring the object Connection, imports all of the fields and methods used to establish a
   * conneciton. Declaring the object Statement will allow queries to be passed to the database, so
   * that it can be accessed.
   */
  private Connection conn = null;

  private Statement stmt = null;

  @FXML private TextField txtManufacturer;

  @FXML private TextField txtModel;

  @FXML private TextField txtYear;

  @FXML private TextField txtcarType;

  @FXML private TextField txtlicensePlate;

  @FXML private Button OkButton;

  @FXML private Button CancelButton;

  @FXML private TextField txtColor;

  @FXML private TextField txtSeats;

  public void initialize() {}

  @FXML
  private void CancelButton(Event event) {
    Main.createNewScene(event, "Dashboard.fxml");
  }

  @FXML
  private Car OkButton(MouseEvent event)  {

    String manufacturer = txtManufacturer.getText();
    String model = txtModel.getText();
    int year = Integer.parseInt(txtYear.getText());
    String carType = txtcarType.getText();
    String licensePlate = txtlicensePlate.getText();
    String CarColor = txtColor.getText();
    int NumSeats = Integer.parseInt(txtSeats.getText());
    int carID = 2;
    Car dummyCar =
        new Car(carID, manufacturer, model, year, carType, licensePlate, CarColor, NumSeats);
    DatabaseAccessor.addCar(dummyCar);

    try {
      // STEP 1: Register JDBC driver

      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT * FROM CAR_DETAILS WHERE CAR_ID='" + carID + "'";
      ResultSet rs = stmt.executeQuery(sql);
      if (rs.next()) {
        System.out.println("Car Found");
        dummyCar =
            new Car(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getInt(8));
        Main.createNewScene(event, "Driver.fxml");
      } else {
        System.out.println("Car not found");
        sql =
            "INSERT INTO CAR_DETAILS(CAR_ID, CAR_MANUFACTURER, CAR_MODEL, CAR_YEAR, CAR_TYPE, LICENSE_PLATE, CAR_COLOR, CAR_SEATING)"
                +
                "VALUES('"
                + dummyCar.getCarID() + "','"
                + dummyCar.getManufacturer() + "','"
                + dummyCar.getModel() + "','"
                + dummyCar.getYear() + "','"
                + dummyCar.getCarType() + "','"
                + dummyCar.getLicensePlate() + "','"
                + dummyCar.getCarColor() + "','"
                + dummyCar.getNumSeats() + "');";
        stmt.executeUpdate(sql);

      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dummyCar;
  }
  }
