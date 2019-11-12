/*
 * Active Rides
 * Used in the process of making the driver scene
 */
package github.Tuesday_at_4.REBU;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DriverActiveRides {

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
    TableView.add(new Rides(0,1,1, LocalDate.of(2019, Month.NOVEMBER, 19),"Miami", "Orlando",LocalTime.of(12, 45),1));
    TableView.add(new Rides(0,1,1, LocalDate.of(2019, Month.NOVEMBER, 30),"Fort Myers", "Tallahassee",LocalTime.of(9, 45),1));
    TableView.add(new Rides(0,1,1, LocalDate.of(2019, Month.NOVEMBER, 21),"Tampa", "Naples",LocalTime.of(3, 20),1));
    return TableView;
  }

}
