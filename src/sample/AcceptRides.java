/*
 * Accept Rides
 * Allows a driver to accept pending ride requests.
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
import javafx.scene.control.cell.PropertyValueFactory;

public class AcceptRides {

  @FXML
  private TableColumn<AcceptedRides, String> Passenger2;

  @FXML
  private TableColumn<AcceptedRides, LocalDate> current_Date;

  @FXML
  private TableColumn<AcceptedRides, LocalTime> current_Time;

  @FXML
  private Button accept_Ride;

  @FXML
  private Button decline_Ride;

  @FXML
  private TableView<AcceptedRides> accepted_Rides;

  @FXML
  public void initialize(URL url, ResourceBundle rb) {
    Passenger2.setCellValueFactory(new PropertyValueFactory<AcceptedRides, String>("Passenger2"));
    current_Date
        .setCellValueFactory(new PropertyValueFactory<AcceptedRides, LocalDate>("currentDate"));
    current_Time
        .setCellValueFactory(new PropertyValueFactory<AcceptedRides, LocalTime>("currentTime"));

    accepted_Rides.setItems(getAcceptedRides());
  }

  public ObservableList<AcceptedRides> getAcceptedRides() {
    ObservableList<AcceptedRides> AcceptedRides = FXCollections.observableArrayList();
    AcceptedRides.add(new AcceptedRides("breanna", LocalTime.of(4, 45), LocalDate.of(2019, Month.OCTOBER, 20)));
    return AcceptedRides;
  }
}
