/***********************************************************
 * File   : DatabaseAccessor.Java
 * Author(s)  : Michael Carracino
 * Class   : CEN 3031
 * Purpose : Manages all interactions with the database.
 ************************************************************/

package sample;

import com.sun.istack.internal.NotNull;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DatabaseAccessor {
  private static String JDBC_DRIVER = "org.h2.Driver";
  private static String DB_URL = "jdbc:h2:./res/RebuDB";
  private static String PASS = "";
  private static String USER = "";

  /**
   * Searches for an account with this username and password
   * @param username - the unique username of the user
   * @param password - the unique password of the user
   * @return - the USER_ID of the requested user, or 0 if there is no account found
   */
  public static int searchForAccount(String username, String password){
    int userID = 0;
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT USER_ID FROM USER_ACCOUNT WHERE USER_NAME = '"+username+"' AND USER_PASSWORD='"+password+"'";
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
        userID=rs.getInt(1);
        System.out.println("Found a user\nUSER_ID: "+userID);
      } else {
        userID=0;
        System.out.println("User not found\nUSER_ID: 0");
      }
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return userID;
  }

  /**
   * Returns the Account built from the associated row in the database, returns an Account of 0's and blanks if data is not found.
   * NOTE: there is no functionality for repeated USER_IDs in the database
   * @param userID - the user ID associated with the
   * @return - returns an Account object, either full of data, or 0's and blanks if the USER_ID is not found
   */
  public static Account getAccount(int userID){
    Account dummyAccount = new Account(0,"","","","","","","",0,0);

    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT * FROM USER_ACCOUNT WHERE USER_ID='"+userID+"'";
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
        System.out.println("Account Found");
        dummyAccount = new Account(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10));
        //dummyAccount.printAccountDetails();
      } else {
        System.out.println("Account not found");
      }
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dummyAccount;
  }

  /**
   * Takes an object of the Account class and adds its data to the database
   * @param dummyAccount - the account to be added
   */
  public static void addAccount(Account dummyAccount){
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "INSERT INTO USER_ACCOUNT(user_id, user_name, user_password, first_name, last_name, phone_number, user_email, date_of_birth, car_id_one, car_id_two)"+
      "VALUES('"
          + dummyAccount.getUser_id() + "','"
          + dummyAccount.getUsername() + "','"
          + dummyAccount.getPassword() + "','"
          + dummyAccount.getFirstName() + "','"
          + dummyAccount.getLastName() + "','"
          + dummyAccount.getPhone() + "','"
          + dummyAccount.getEmail() + "','"
          + dummyAccount.getDateOfBirth() + "','"
          + dummyAccount.getCarOneID() + "','"
          + dummyAccount.getCarTwoID() + "');";
      stmt.executeUpdate(sql);
      System.out.println("Account "+dummyAccount.getUser_id()+" has been added!");
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Takes in a user_id and deletes the associated account
   * @param userID - the ID for the account to be deleted
   */
  public static void deleteAccount(int userID){
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "DELETE FROM USER_ACCOUNT WHERE USER_ID='"+userID+"'";
      stmt.executeUpdate(sql);
      System.out.println("Account "+userID+" has been deleted!");
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * A Test function to print out all Ride data from an ArrayList<Rides>.
   * @param dummyArray - the Array of Rides to be printed
   */
  public static void printAllRides(ArrayList<Rides> dummyArray){
    System.out.println("\n************All stored Rides************");
    for (Rides x : dummyArray){
      System.out.println(
          "\nRideID: "+x.getRideID()+
          "\nPassengerID: "+x.getPassenger()+
          "\nDriverID: "+x.getDriver()+
          "\nStartDate: "+x.getDate_OfRide()+
          "\nStartLocation: "+x.getStartLocation()+
          "\nEndLocation: "+x.getEndLocation()+
          "\nStartTime: "+x.getTime_OfRide()+
          "\nRideStatusID: "+x.getRide_status_id());
    }
    System.out.println("************End of stored Rides************");
  }

  /**
   * Returns an ArrayList of all Rides in the DB
   * @return - An ArrayList of every Ride stored in the Database
   */
  public static ArrayList<Rides> getAllRides(){
    ArrayList<Rides> dummyRideArray = new ArrayList<Rides>();
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT * FROM RIDES_LIST";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
        dummyRideArray.add(new Rides(
            rs.getInt(1),
            rs.getInt(2),
            rs.getInt(3),
            LocalDate.parse(rs.getString(4)),
            rs.getString(5),
            rs.getString(6),
            LocalTime.parse(rs.getString(7)),
            rs.getInt(8)));
      }
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dummyRideArray;
  }

  /**
   * Returns an ArrayList of all Rides in the DB that have a matching DriverID
   * @param driverUserID - the userID of the Driver
   * @return - ArrayList of all rides where userID = driverID
   */
  public static ArrayList<Rides> getDriverRides(int driverUserID){
    ArrayList<Rides> dummyRideArray = new ArrayList<Rides>();
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT * FROM RIDES_LIST WHERE DRIVER_ID = '"+driverUserID+"'";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
        dummyRideArray.add(new Rides(
            rs.getInt(1),
            rs.getInt(2),
            rs.getInt(3),
            LocalDate.parse(rs.getString(4)),
            rs.getString(5),
            rs.getString(6),
            LocalTime.parse(rs.getString(7)),
            rs.getInt(8)));
      }
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dummyRideArray;
  }

  /**
   * Returns an ArrayList of all Rides in the DB that have a matching PassengerID
   * @param passengerID - the userID of the Passenger
   * @return - ArrayList of all rides where userID = passengerID
   */
  public static ArrayList<Rides> getPassengerRides(int passengerID){
    ArrayList<Rides> dummyRideArray = new ArrayList<Rides>();
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT * FROM RIDES_LIST WHERE PASSENGER_ID = '"+passengerID+"'";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
        dummyRideArray.add(new Rides(
            rs.getInt(1),
            rs.getInt(2),
            rs.getInt(3),
            LocalDate.parse(rs.getString(4)),
            rs.getString(5),
            rs.getString(6),
            LocalTime.parse(rs.getString(7)),
            rs.getInt(8)));
      }
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dummyRideArray;
  }

  public static void addRide(Rides dummyRide){
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "INSERT INTO RIDES_LIST(RIDE_ID, PASSENGER_ID, DRIVER_ID, START_DATE, START_LOCATION, END_LOCATION, START_TIME, RIDE_STATUS_ID)"+
          "VALUES('"
          + dummyRide.getRideID() + "','"
          + dummyRide.getPassengerName() + "','"
          + dummyRide.getDriver() + "','"
          + dummyRide.getDate_OfRide().toString() + "','"
          + dummyRide.getStartLocation() + "','"
          + dummyRide.getEndLocation() + "','"
          + dummyRide.getTime_OfRide().toString() + "','"
          + dummyRide.getRide_status_id()+"');";
      stmt.executeUpdate(sql);
      System.out.println("Ride "+dummyRide.getRideID()+" has been added!");
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Changes the RideStatusID of a particular Ride
   *  Ride Status ID's
   *    0 - 'INACTIVE'
   * 	  1 - 'ACTIVE'
   * 	  2 - 'Cancelled by Driver'
   * 	  3 - 'Cancelled by Passenger'
   * @param dummyRideID - The ID of the ride in question
   * @param rideStatusID - The status flag of the ride in question
   */
  public static void editRideStatus(int dummyRideID, int rideStatusID){
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "UPDATE RIDES_LIST SET RIDE_STATUS_ID = "+rideStatusID+" WHERE RIDE_ID = " + dummyRideID;
      stmt.executeUpdate(sql); //There is no resultSet for an update statement

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void deleteRide(int rideID){
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "DELETE FROM RIDES_LIST WHERE RIDE_ID='"+rideID+"'";
      stmt.executeUpdate(sql);
      System.out.println("Ride "+rideID+" has been deleted!");
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  };
  
  /*
  public String[] getNotifications(int userID){
    String[] rideID = {""};
    return rideID;
  }
  public void addNotification(int userID, int rideID){
  }
  public void deleteNotification(int userID, int rideID){
  }
  public Car[] getCars(int user_ID){
    Car dummyCar[] = {new Car("","","","","")};
    return dummyCar;
  }
  public void createCar(Car dummyCar){
  }
  public Car editCar(Car givenCar){
    Car dummyCar = new Car("","","","","","","");
    return dummyCar;
  }
  public void deleteCar(int car_ID){};
   */
}

