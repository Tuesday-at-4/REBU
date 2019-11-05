package sample;
// Don't name your parameter variables the same as the class variables. It gets REALLY confusing.
//import java.sql.Date; There should be no need for SQL
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
//import javafx.beans.property.SimpleStringProperty;  This can be string.

// Needs to be changed to match the database structure
public class Rides {

  private String passengerName;
  private String driver;
  private LocalTime timeOfRide;
  private LocalDate dateOfRide;
  private String startLocation;
  private String endLocation;
  private int rideID;
  
  /**
   * Contructor for when the passenger creates a ride
   */
  public Rides(String passName, LocalTime vtime, LocalDate vDate, String startLoc, String endLoc) {
    this.passengerName = passName;
    this.driver = "None";
    this.date_OfRide = vDate;
    this.time_OfRide = vtime;
    this.startLocation = startLoc;
    this.endLocation = endLoc;
    Random rnd = new Random();
    this.rideID = rnd.nextInt(999999);
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
