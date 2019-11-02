/*
 * Active Rides
 * Used in the process of making the driver scene
 */
package sample;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ActiveRides {

  @FXML
  private TableView<Rides> scheduled_Rides;
  @FXML
  private TableColumn<Rides, String> Passenger;

  @FXML
  private TableColumn<Rides, LocalTime> Time_OfRide;

  @FXML
  private TableColumn<Rides, LocalDate> Date_OfRide;

  @FXML
  private Button schedule_Button;

  @FXML
  private Button schedule_Delete;

  /**
   * When creating a TableColumn instance, the two most important properties to set are the column
   * text (what to show in the column header area), and the column cell value factory (which is used
   * to populate individual cells in the column).
   */
  @FXML
  public void initialize(URL url, ResourceBundle rb) {
    Passenger.setCellValueFactory(new PropertyValueFactory<Rides, String>("passengerName"));
    Date_OfRide.setCellValueFactory(new PropertyValueFactory<Rides, LocalDate>("Date"));
    Time_OfRide.setCellValueFactory(new PropertyValueFactory<Rides, LocalTime>("Time"));

    scheduled_Rides.setItems(getRides());
  }

  public ObservableList<Rides> getRides() {
    ObservableList<Rides> TableView = FXCollections.observableArrayList();
    TableView.add(new Rides("J Cole", LocalTime.of(12, 45), LocalDate.of(2019, Month.OCTOBER, 19),"Miami", "Orlando"));
    TableView.add(new Rides("Kendrick Lamar", LocalTime.of(5, 35), LocalDate.of(2019, Month.OCTOBER, 24), "Ohio", "Kentucky"));
    TableView.add(new Rides("LeBron James", LocalTime.of(6, 45), LocalDate.of(2019, Month.OCTOBER, 31),"California", "Georgia"));
    return TableView;
  }

}
