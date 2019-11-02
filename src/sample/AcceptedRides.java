/*
 * Accepted Rides
 * Pending ride requests that the driver has accepted.
 */
package sample;

import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.SimpleStringProperty;

public class AcceptedRides {

  private SimpleStringProperty Passenger2;
  private LocalTime current_Time;
  private LocalDate current_Date;

  public AcceptedRides(String Passenger2, LocalTime currentTime, LocalDate currentDate) {
    this.Passenger2 = new SimpleStringProperty(Passenger2);
    this.current_Date = currentDate;
    this.current_Time = currentTime;
  }

  public String getPassenger2() {
    return Passenger2.get();
  }

  public void setPassenger2(SimpleStringProperty Passenger2) {
    this.Passenger2 = Passenger2;
  }

  public LocalDate getCurrent_Date() {
    return current_Date;
  }

  public void setCurrent_Date(LocalDate currentDate) {
    current_Date = currentDate;
  }

  public LocalTime getCurrent_Time() {
    return current_Time;
  }

  public void setCurrent_Time(LocalTime currentTime) {
    this.current_Time = currentTime;
  }
}

