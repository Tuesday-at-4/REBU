/***********************************************************
 * File   : Rides.Java
 * Author(s)  : Michael Carracino, Benjamin Cano, Breanna Rhodes
 * Class   : CEN 3031
 * Purpose : Stores the information from the database for a given Ride
 ************************************************************/
package github.Tuesday_at_4.REBU;

import java.time.LocalDate;
import java.time.LocalTime;


public class Rides {
  private int ride_id;
  private int passenger_id;
  private int driver_id;
  private LocalDate start_date;
  private String start_location;
  private String end_location;
  private LocalTime start_time;
  private int ride_status_id;
  private String firstName;
  private String nameHidden;
  private String driverName;
  private String passengerName;
  private String rideStatusComment;


//SETTERS
  public void setRide_id(int ride_id) {
    this.ride_id = ride_id;
  }

  public void setRideStatusComment(String rideStatusComment){
    this.rideStatusComment = rideStatusComment;
  }

  public void setPassenger_id(int passenger_id) {
    this.passenger_id = passenger_id;
  }

  public void setDriver_id(int driver_id) {
    this.driver_id = driver_id;
  }

  public void setStart_date(LocalDate start_date) {
    this.start_date = start_date;
  }

  public void setStart_location(String start_location) {
    this.start_location = start_location;
  }

  public void setEnd_location(String end_location) {
    this.end_location = end_location;
  }

  public void setStart_time(LocalTime start_time) {
    this.start_time = start_time;
  }

  public void setRide_status_id(int ride_status_id) {
    this.ride_status_id = ride_status_id;
  }

  //GETTERS
  public int getRide_id() {
    return ride_id;
  }

  public int getPassenger_id() {
    return passenger_id;
  }

  public int getDriver_id() {
    return driver_id;
  }

  public LocalDate getStart_date() {
    return start_date;
  }

  public String getStart_location() {
    return start_location;
  }

  public String getEnd_location() {
    return end_location;
  }

  public LocalTime getStart_time() {
    return start_time;
  }

  public String getNameHidden(){
    nameHidden = "Not Assigned Yet";
    return nameHidden;
  }

  //CONSTRUCTORS
  /**
   * This is a general use constructor
   */
  public Rides(int passenger_id, int driver_id, LocalTime start_time, LocalDate start_date, String start_location, String end_location, int ride_status_id) {
    this.passenger_id = passenger_id;
    this.driver_id = driver_id;
    this.start_date = start_date;
    this.start_location = start_location;
    this.end_location = end_location;
    this.start_time = start_time;
    this.ride_status_id = ride_status_id;
  }
  /**
   * Database only Constructor
   */
  public Rides(int ride_id, int passenger_id, int driver_id, LocalTime start_time, LocalDate start_date, String start_location, String end_location, int ride_status_id) {
    this.ride_id = ride_id;
    this.passenger_id = passenger_id;
    this.driver_id = driver_id;
    this.start_date = start_date;
    this.start_location = start_location;
    this.end_location = end_location;
    this.start_time = start_time;
    this.ride_status_id = ride_status_id;
  }

  //HELPERS
  public int getPassengerName() {
    passengerName = DatabaseAccessor.getAccount(passenger_id).getFirstName();
    return passenger_id;
  }

  public String getFirstName(){
    firstName = Main.currentUser.getFirstName();
    return firstName;
  }
  public String getDriverName(){
    if(driver_id ==0){
      driverName = "Not yet assigned";
    } else {
      driverName = DatabaseAccessor.getAccount(driver_id).getFirstName();
    }
    return driverName;
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

  public int getRide_status_id(){
    return ride_status_id;
  }

  public String getRideStatusComment(){
    switch(ride_status_id){
      case 0:
        rideStatusComment = "Accepted";
        break;
      case 1:
        rideStatusComment = "Pending";
        break;
      case 2:
        rideStatusComment = "Completed";
        break;
      case 3:
        rideStatusComment = "Expired";
        break;
      case 4:
        rideStatusComment = "Cancelled by Driver";
        break;
      default:
        rideStatusComment = "Cancelled by Passenger";
    }
    return rideStatusComment; }

  public void printRide(){
    System.out.println(
        "\nRideID: "+ride_id+
            "\t|PassengerID: "+passenger_id+
            "\t|DriverID: "+driver_id+
            "\t|Date: "+start_date+
            "\t|From: "+start_location+
            "\t|To: "+end_location+
            "\t|When: "+start_time+
            "\t|StatusID: "+ride_status_id);
  }
}
