/**
 * *********************************************************
 * File : DatabaseAccessor.Java
 * Author(s): Michael Carracino, Breanna Rhodes, Sabrina Kienholz
 * Class : CEN 3031
 * Purpose : Manages all interactions with the database.
 * **********************************************************
 */
package github.Tuesday_at_4.REBU;

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
  //////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////    Account Methods   ////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Searches for an account with this username and password
   *
   * @param username - the unique username of the user
   * @param password - the unique password of the user
   * @return - the USER_ID of the requested user, or 0 if there is no account found
   */
  public static int searchForAccount(String username, String password) {
    int userID = 0;
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql =
          "SELECT USER_ID FROM USER_ACCOUNT WHERE USER_NAME = '"
              + username
              + "' AND USER_PASSWORD='"
              + password
              + "'";
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
        userID = rs.getInt(1);
        System.out.println("Found a user\nUSER_ID: " + userID);
      } else {
        userID = 0;
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
   * Returns the Account built from the associated row in the database, returns an Account of 0's
   * and blanks if data is not found. NOTE: there is no functionality for repeated USER_IDs in the
   * database
   *
   * @param userID - the user ID associated with the
   * @return - returns an Account object, either full of data, or 0's and blanks if the USER_ID is
   *     not found
   */
  public static Account getAccount(int userID) {
    Account dummyAccount = new Account(0, "", "", "", "", "", "", "", 0, 0);

    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT * FROM USER_ACCOUNT WHERE USER_ID='" + userID + "'";
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
        System.out.println("Account Found");
        dummyAccount =
            new Account(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getString(8),
                rs.getInt(9),
                rs.getInt(10));
        // dummyAccount.printAccountDetails();
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
   *
   * @param dummyAccount - the account to be added
   */
  public static void addAccount(Account dummyAccount) {
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql =
          "INSERT INTO USER_ACCOUNT(user_id, user_name, user_password, first_name, last_name, phone_number, user_email, date_of_birth, car_id_one, car_id_two)"
              + "VALUES('"
              + dummyAccount.getUser_id()
              + "','"
              + dummyAccount.getUsername()
              + "','"
              + dummyAccount.getPassword()
              + "','"
              + dummyAccount.getFirstName()
              + "','"
              + dummyAccount.getLastName()
              + "','"
              + dummyAccount.getPhone()
              + "','"
              + dummyAccount.getEmail()
              + "','"
              + dummyAccount.getDateOfBirth()
              + "','"
              + dummyAccount.getCarOneID()
              + "','"
              + dummyAccount.getCarTwoID()
              + "');";
      stmt.executeUpdate(sql);
      System.out.println("Account " + dummyAccount.getUser_id() + " has been added!");
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
   *
   * @param userID - the ID for the account to be deleted
   */
  public static void deleteAccount(int userID) {
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "DELETE FROM USER_ACCOUNT WHERE USER_ID='" + userID + "'";
      stmt.executeUpdate(sql);
      System.out.println("Account " + userID + " has been deleted!");
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //////////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////    Rides Methods   /////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * A Test function to print out all Ride data from an ArrayList<Rides>.
   *
   * @param dummyArray - the Array of Rides to be printed
   */
  public static void printAllRides(ArrayList<Rides> dummyArray) {
    System.out.println("\n************All stored Rides************");
    for (Rides x : dummyArray) {
      System.out.println(
          "\nRideID: "
              + x.getRideID()
              + "\nPassengerID: "
              + x.getPassenger()
              + "\nDriverID: "
              + x.getDriver()
              + "\nStartDate: "
              + x.getDate_OfRide()
              + "\nStartLocation: "
              + x.getStartLocation()
              + "\nEndLocation: "
              + x.getEndLocation()
              + "\nStartTime: "
              + x.getTime_OfRide()
              + "\nRideStatusID: "
              + x.getRide_status_id());
    }
    System.out.println("************End of stored Rides************");
  }

  /**
   * Returns an ArrayList of all Rides in the DB. Not very useful, except in conjunction with
   * printAllRides in order to manually see the status of the rides in the DB.
   *
   * @return - An ArrayList of every Ride stored in the Database
   */
  public static ArrayList<Rides> getAllRides() {
    ArrayList<Rides> dummyRideArray = new ArrayList<Rides>();
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT * FROM RIDES_LIST";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        dummyRideArray.add(
            new Rides(
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
   * Returns an ArrayList of all Rides in the DB that have a matching DriverID and that have the
   * Accepted status This array is a list of rides that this driver has accepted. Built specifically
   * for the Accepted Rides tabPane in the driver screen
   *
   * @param driverUserID - the userID of the Driver
   * @return - ArrayList of all rides where userID = driverID
   */
  public static ArrayList<Rides> getDriverAcceptedRides(int driverUserID) {
    ArrayList<Rides> dummyRideArray = new ArrayList<Rides>();
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql =
          "SELECT * FROM RIDES_LIST WHERE DRIVER_ID = '"
              + driverUserID
              + "' AND RIDE_STATUS_ID = 0";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        dummyRideArray.add(
            new Rides(
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
   * Returns an array of all Rides where the Driver ID's DON'T match and the rides have the Pending
   * status This is an array of Rides that the Driver has not accepted. Made specifically for the
   * Pending Rides tabPane in the driver screen
   *
   * @param driverUserID - The driverID that is matched to Rides
   * @return - ArrayList of all rides that are both pending and not associated with the driver
   */
  public static ArrayList<Rides> getDriverPendingRides(int driverUserID) {
    ArrayList<Rides> dummyRideArray = new ArrayList<Rides>();
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql =
          "SELECT * FROM RIDES_LIST WHERE DRIVER_ID != '"
              + driverUserID
              + "' AND RIDE_STATUS_ID = 1"; // Returns only pending rides not associated with the
                                            // driver
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        dummyRideArray.add(
            new Rides(
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
   * Returns an ArrayList of all Rides in the DB that have a matching PassengerID and the pending
   * status MAde specifically for the Pending Rides in the Passenger Screen
   *
   * @param passengerID - the userID of the Passenger
   * @return - ArrayList of all rides where userID = passengerID
   */
  public static ArrayList<Rides> getPassengerPendingRides(int passengerID) {
    ArrayList<Rides> dummyRideArray = new ArrayList<Rides>();
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql =
          "SELECT * FROM RIDES_LIST WHERE PASSENGER_ID = '"
              + passengerID
              + "' AND RIDE_STATUS_ID = 1";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        dummyRideArray.add(
            new Rides(
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
   * Returns an array of all Rides where the PassengerID's match, and the Rides have the accepted
   * status Made specifically for the Accepted Rides tabPane in the Passenger screen.
   *
   * @param passengerID - The userID of the passenger
   * @return - an arrayList of Rides that have both a matching passengerID and the accepted status
   */
  public static ArrayList<Rides> getPassengerAcceptedRides(int passengerID) {
    ArrayList<Rides> dummyRideArray = new ArrayList<Rides>();
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql =
          "SELECT * FROM RIDES_LIST WHERE PASSENGER_ID = '"
              + passengerID
              + "' AND RIDE_STATUS_ID = 0";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        dummyRideArray.add(
            new Rides(
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
   * Stores the data in a Ride object in the database
   *
   * @param dummyRide - The filled Ride to be stored
   */
  public static void addRide(Rides dummyRide) {
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql =
          "INSERT INTO RIDES_LIST(RIDE_ID, PASSENGER_ID, DRIVER_ID, START_DATE, START_LOCATION, END_LOCATION, START_TIME, RIDE_STATUS_ID)"
              + "VALUES('"
              + dummyRide.getRideID()
              + "','"
              + dummyRide.getPassengerName()
              + "','"
              + dummyRide.getDriver()
              + "','"
              + dummyRide.getDate_OfRide().toString()
              + "','"
              + dummyRide.getStartLocation()
              + "','"
              + dummyRide.getEndLocation()
              + "','"
              + dummyRide.getTime_OfRide().toString()
              + "','"
              + dummyRide.getRide_status_id()
              + "');";
      stmt.executeUpdate(sql);
      System.out.println("Ride " + dummyRide.getRideID() + " has been added!");
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
   * Changes the RideStatusID of a particular Ride List of Ride Status ID's: 0, Accepted 1, Pending
   * 2, Completed 3, Expired 4, Cancelled by Driver 5, Cancelled by Passenger
   *
   * @param dummyRideID - The rideID of the ride in question
   * @param rideStatusID - The status flag of the ride in question
   */
  public static void editRideStatus(int dummyRideID, int rideStatusID) {
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql =
          "UPDATE RIDES_LIST SET RIDE_STATUS_ID = "
              + rideStatusID
              + " WHERE RIDE_ID = "
              + dummyRideID;
      stmt.executeUpdate(sql); // There is no resultSet for an update statement

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
   * Deletes any rides with a matching rideID
   *
   * @param rideID - the ride to be deleted
   */
  public static void deleteRide(int rideID) {
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "DELETE FROM RIDES_LIST WHERE RIDE_ID='" + rideID + "'";
      stmt.executeUpdate(sql);
      System.out.println("Ride " + rideID + " has been deleted!");
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  };

  //////////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////    Notification Methods   //////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////
  /*
  public String[] getNotifications(int userID){
    String[] rideID = {""};
    return rideID;
  }
  public void addNotification(int userID, int rideID){
  }
  public void deleteNotification(int userID, int rideID){
  }
  */
  //////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////    Car Methods   //////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * returns the Car object of the carID
   *
   * @param - the target car, base on the carID
   * @return - a car object, filled with the relevant data
   */
  public Car getCarFromID(int carID) {
    Car dummyCar = new Car(0, "", "", 000, "", "", "", 0);
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT * FROM CAR_DETAILS WHERE CAR_ID='" + carID + "'";
      ResultSet rs = stmt.executeQuery(sql);

      if (rs.next()) {
        System.out.println("Car Found");
        dummyCar =
            new Car(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                rs.getInt(8));
      } else {
        System.out.println("Car not found");
      }
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dummyCar;
  }

  /**
   * Adds a car object to the database
   *
   * @param dummyCar
   */
  public static void addCar(Car dummyCar) {
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql =
          "INSERT INTO CAR_DETAILS(CAR_ID, CAR_MANUFACTURER, CAR_MODEL, CAR_YEAR, CAR_TYPE, LICENSE_PLATE, CAR_COLOR, CAR_SEATING) VALUES('"
              + dummyCar.getCarID()
              + "','"
              + dummyCar.getManufacturer()
              + "','"
              + dummyCar.getModel()
              + "','"
              + dummyCar.getYear()
              + "','"
              + dummyCar.getCarType()
              + "','"
              + dummyCar.getLicensePlate()
              + "','"
              + dummyCar.getCarColor()
              + "','"
              + dummyCar.getNumSeats()
              + "')";
      stmt.executeUpdate(sql);
      System.out.println("Car " + dummyCar.getCarID() + " has been added!");
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
   * Takes a carID and deletes all cars that match it in the DB
   *
   * @param car_ID the car_id to be added
   */
  public void deleteCar(int car_ID) {
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "DELETE FROM CAR_DETAILS WHERE CAR_ID='" + car_ID + "'";
      stmt.executeUpdate(sql);
      System.out.println("Car " + car_ID + " has been deleted!");
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
   * Updates the data for a particular item in the database.
   *
   * @param dummyAccount
   */

  public static void editAccount(Account dummyAccount) {
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      stmt = conn.createStatement();
      String sql =
          "UPDATE USER_ACCOUNT "
              + "SET USER_NAME = '" + dummyAccount.getUsername()+"',"
              + "USER_PASSWORD = '" + dummyAccount.getPassword()+"',"
              + "FIRST_NAME = '" + dummyAccount.getFirstName()+"',"
              + "LAST_NAME = '" + dummyAccount.getLastName()+"',"
              + "PHONE_NUMBER = '" + dummyAccount.getPhone()+"',"
              + "USER_EMAIL = '" + dummyAccount.getEmail()+"',"
              + "DATE_OF_BIRTH = '" + dummyAccount.getDateOfBirth()+"',"
              + "CAR_ID_ONE = '" + dummyAccount.getCarOneID()+"',"
              + "CAR_ID_TWO = '" + dummyAccount.getCarTwoID()+"'"
              + " WHERE USER_ID = '" + dummyAccount.getUser_id() + "'";
      stmt.executeUpdate(sql);
      System.out.println("Account " + dummyAccount.getUser_id() + " has been updated!");
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
