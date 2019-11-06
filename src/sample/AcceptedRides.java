/***********************************************************
 * File   : AcceptedRides.Java
 * Author(s)  : Benjamin Cano
 * Class   : CEN 3031
 * Purpose : allows the for population in the tableview for accepted rides tab
 ************************************************************/
package sample;

import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AcceptedRides {


  private String Passenger2;
  private LocalTime current_Time;
  private LocalDate current_Date;
  private String end_Loc;
  private String start_Loc;

  AcceptedRides(String Passenger2, LocalTime currentTime, LocalDate currentDate, String end_Loc, String start_Loc) {
    this.Passenger2 = Passenger2;
    this.current_Date = currentDate;
    this.current_Time = currentTime;
    this.end_Loc = end_Loc;
    this.start_Loc = start_Loc;
  }
  public String getStart_Loc() {
    return start_Loc;
  }

  public void setStart_Loc(String start_Loc) {
    this.start_Loc = start_Loc;
  }
  public String getEnd_Loc() {
    return end_Loc;
  }

  public void setEnd_Loc(String end_Loc) {
    this.end_Loc = end_Loc;
  }
  public String getPassenger2() {
    return Passenger2;
  }

  public void setPassenger2(String Passenger2) {
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

