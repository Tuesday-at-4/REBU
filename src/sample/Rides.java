package sample;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.SimpleStringProperty;

public class Rides {

  private SimpleStringProperty passengerName;
  private LocalTime time_OfRide;
  private LocalDate date_OfRide;

   Rides(String passengerName, LocalTime time, LocalDate Date) {
    this.passengerName = new SimpleStringProperty(passengerName);
    this.date_OfRide = Date;
    this.time_OfRide = time;
  }

  public String getPassengerName() {
    return passengerName.get();
  }

  public void setPassengerName(String passengerName) {
    this.passengerName.set(passengerName);
  }

  public LocalDate getDate_OfRide() {

     return date_OfRide;
  }

  public void setDate_OfRide(LocalDate date) {

     this.date_OfRide = date;
  }

  public LocalTime getTime_OfRide() {

     return time_OfRide;
  }

  public void setTime(LocalTime time) {

     this.time_OfRide = time;
  }
}
