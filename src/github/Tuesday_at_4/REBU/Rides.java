/***********************************************************
 * File   : Rides.Java
 * Author(s)  : Michael Carracino, Benjamin Cano
 * Class   : CEN 3031
 * Purpose : Stores the information from the database for a given Ride
 ************************************************************/
package github.Tuesday_at_4.REBU;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Rides {
  private int ride_id;
  private int passenger_id;
  private int driver_id;
  private LocalDate start_date;
  private String start_location;
  private String end_location;
  private LocalTime start_time;
  private int ride_status_id;

  public Rides(int ride_id, int passenger_id, int driver_id, LocalDate start_date, String start_location, String end_location, LocalTime start_time, int ride_status_id) {
    this.ride_id = ride_id;
    this.passenger_id = passenger_id;
    this.driver_id = driver_id;
    this.start_date = start_date;
    this.start_location = start_location;
    this.end_location = end_location;
    this.start_time = start_time;
    this.ride_status_id = ride_status_id;
  }

  public Rides(LocalDate start_date, String start_location, String end_location, LocalTime start_time, int ride_status_id) {
    this.start_date = start_date;
    this.start_location = start_location;
    this.end_location = end_location;
    this.start_time = start_time;
    this.ride_status_id = ride_status_id;
    System.out.println("Start Date: " + start_date + "\nStart Location: " + start_location
        + "\nEnd Location: " + end_location + "\nStart Time: " + start_time + "\nRide Status: "+ ride_status_id);
  }



  public int getPassengerName() {
    return passenger_id;
  }

  public LocalDate getDate_OfRide() {
    return start_date;
  }

  public LocalTime getTime_OfRide() {
    return start_time;
  }

  public String getEndLocation() {
    return end_location;
  }

  public String getStartLocation() {
    return start_location;
  }

  public int getRideID() {
    return ride_id;
  }

  public int getPassenger() { return passenger_id;}

  public int getDriver() {
    return driver_id;
  }

  public int getRide_status_id(){ return ride_status_id; }

  public void printRide(){
    System.out.println(
        "RideID: "+ride_id+
            "|PassengerID: "+passenger_id+
            "|DriverID: "+driver_id+
            "|Date: "+start_date+
            "|From: "+start_location+
            "|To: "+end_location+
            "|When: "+start_time+
            "|StatusID: "+ride_status_id);
  }
}
