/*
 * Rides
 * Made by the passenger and accessed by the driver
 */
package sample;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Rides {
  private String passengerName;
  private LocalTime time_OfRide;
  private LocalDate date_OfRide;
  private String startLocation;
  private String endLocation;
  private int rideID;
  private String driver;

  public Rides(String passName, LocalTime time, LocalDate Date, String startLoc, String endLoc) {
    this.passengerName = passName;
    this.date_OfRide = Date;
    this.time_OfRide = time;
    this.startLocation = startLoc;
    this.endLocation = endLoc;
    Random rnd = new Random();
    this.rideID = rnd.nextInt(999999);
    this.driver = "None";
  }

  public String getPassengerName() {
    return passengerName;
  }

  public LocalDate getDate_OfRide() {
    return date_OfRide;
  }

  public LocalTime getTime_OfRide() {
    return time_OfRide;
  }

  public String getEndLocation() {
    return endLocation;
  }

  public String getStartLocation() {
    return startLocation;
  }

  public int getRideID() {
    return rideID;
  }

  public String getDriver() {
    return driver;
  }
}
