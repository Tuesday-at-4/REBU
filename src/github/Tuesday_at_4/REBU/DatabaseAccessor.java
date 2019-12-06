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
import java.util.HashMap;
import java.util.Observable;

public class DatabaseAccessor {
  private static String JDBC_DRIVER = "org.h2.Driver";
  private static String DB_URL = "jdbc:h2:./res/RebuDB";
  private static String PASS = "";
  private static String USER = "";
  private static Connection conn = null;
  private static Statement stmt = null;
  //////////////////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////    Account Methods   ////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////
  public static void printAllAccounts(){
    ArrayList<Account> dummyAccount = new ArrayList<>();
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM USER_ACCOUNT";
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        dummyAccount.add(new Account(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7),
                LocalDate.parse(rs.getString(8)),
                rs.getString(9)));
      }
      System.out.println("\n************ Accounts ************");
      for (Account x : dummyAccount){
        x.printAccountDetails();
      }
      System.out.println("\n************End of stored Accounts************\n");
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
   * Searches for an account with this username and password
   * @param username - the unique username of the user
   * @param password - the unique password of the user
   * @return - the USER_ID of the requested user, or 0 if there is no account found
   */
  public static int searchForAccount(String username, String password) {
    int userID=-1;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
    Account dummyAccount = new Account(0,"", "", "", "", "", "", LocalDate.parse("1111-11-25"), "");
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
                LocalDate.parse(rs.getString(8)),
                rs.getString(9));
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
          "INSERT INTO USER_ACCOUNT(user_name, user_password, first_name, last_name, phone_number, user_email, date_of_birth, credit_card)"
              + "VALUES('"
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
              + dummyAccount.getCreditCard()
              + "');";
      stmt.executeUpdate(sql);
      System.out.println("Account " + dummyAccount.getUserID() + " has been added!");
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
              + " SET USER_NAME = '" + dummyAccount.getUsername() + "', "
              + "USER_PASSWORD = '" + dummyAccount.getPassword() + "', "
              + "FIRST_NAME = '" + dummyAccount.getFirstName() + "', "
              + "LAST_NAME = '" + dummyAccount.getLastName() + "', "
              + "PHONE_NUMBER = '" + dummyAccount.getPhone() + "', "
              + "USER_EMAIL = '" + dummyAccount.getEmail() + "', "
              + "DATE_OF_BIRTH = '" + dummyAccount.getDateOfBirth().toString() + "', "
              + "CREDIT_CARD = '" + dummyAccount.getCreditCard() + "'"
              + " WHERE USER_ID = " + dummyAccount.getUserID();
      stmt.executeUpdate(sql);
      System.out.println("Account " + dummyAccount.getUserID() + " has been updated!");
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
   * A Test function to print out all Ride data from the database
   */
  public static void printAllRides() {
    ArrayList<Rides> dummyRides = getAllRides();
    System.out.println("\n************ Rides ************");
    for (Rides x: dummyRides){
      x.printRide();
    }

    System.out.println("************End of stored Rides************\n");
  }

  /**
   * Returns a ride object based on the RideID
   * @param rideID - the target ride's ID
   * @return - the filled Rides object
   */
  public static Rides getRide(int rideID){
    Rides dummyRide = new Rides(0,0,0,LocalTime.of(1,1),LocalDate.of(1111,1,1),"","",0);
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT * FROM RIDES_LIST WHERE RIDE_ID = "+rideID;
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        dummyRide = new Rides(
                rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                LocalTime.parse(rs.getString(4)),
                LocalDate.parse(rs.getString(5)),
                rs.getString(6),
                rs.getString(7),
                rs.getInt(8));
      }
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dummyRide;
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
                LocalTime.parse(rs.getString(4)),
                LocalDate.parse(rs.getString(5)),
                rs.getString(6),
                rs.getString(7),
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

  public static void addDriverToRide(int rideID, int driverID){
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      stmt = conn.createStatement();
      String sql =
          "UPDATE RIDES_LIST "
              + " SET DRIVER_ID = " + driverID
              + " WHERE RIDE_ID = " + rideID;
      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
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
          "INSERT INTO RIDES_LIST(PASSENGER_ID, DRIVER_ID, START_DATE, START_LOCATION, END_LOCATION, START_TIME, RIDE_STATUS_ID)"
              + "VALUES('"
              + dummyRide.getPassengerName()
              + "','"
              + dummyRide.getDriver_id()
              + "','"
              + dummyRide.getStart_date().toString()
              + "','"
              + dummyRide.getStart_location()
              + "','"
              + dummyRide.getEnd_location()
              + "','"
              + dummyRide.getStart_time().toString()
              + "','"
              + dummyRide.getRide_status_id()
              + "');";
      stmt.executeUpdate(sql);
      System.out.println("Ride " + dummyRide.getRide_id() + " has been added!");
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
   * @param userID - the user who is changing the status
   * @param dummyRideID - The rideID of the ride in question
   * @param newRideStatusID - The status flag of the ride in question
   */
  public static void changeRideStatus(int userID, int dummyRideID, int newRideStatusID) {
    //Before we change the ride, we add the notification about it.
    Rides dummyRide = getRide(dummyRideID);
    //System.out.println(dummyRide.printRide();
    addNotification(userID, dummyRide, newRideStatusID);

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
              + newRideStatusID
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

  public static void printAllNotifications(){
    ArrayList<Notification> dummyArray = new ArrayList<>();
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();

      String sql =
          "SELECT NOTIFICATION_TYPE, NOTIFICATION_TEXT FROM USER_NOTIFICATIONS";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
        dummyArray.add(new Notification(rs.getInt(1), rs.getString(2)));
      }

      System.out.println("\n************ Notifications ************");
      for (Notification x: dummyArray){
        x.printNotifications();
      }
      System.out.println("************End of stored Notifications************\n");


      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  /**
   * Gets an ArrayList of Notification objects that are pertinent to  a user.
   * @param userID - the user whose notifications you want.
   * @return - The array of strings
   */

  public static ArrayList<Notification> getNotifications(int userID){
    ArrayList<Notification> dummyArray = new ArrayList<>();
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();

      String sql =
          "SELECT notification_type, notification_text FROM USER_NOTIFICATIONS WHERE USER_ID = " +userID;
      ResultSet rs = stmt.executeQuery(sql);
      System.out.println("Getting Notifications...");
      while(rs.next()){
        dummyArray.add(new Notification(rs.getInt(1), rs.getString(2)));
      }
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return dummyArray;
  }

  /**
   * This class is used by the changeRideStatus to create a notification for both passenger and driver, where applicable.
   *    The notifications convey this change to the users
   *    There are always two notifications made, one for driver, and another for passenger
   * @param userID - the user who caused the notification
   * @param dummyRide - the ride whose status is changing
   * @param rideStatusID - the status that the ride is changing to
   */
  private static void addNotification(int userID, Rides dummyRide, int rideStatusID){

    String noteStringPassenger;
    String noteStringDriver;
    dummyRide.printRide();

    String userCreditCard = Main.currentUser.getCreditCard();

    // blocking out all numbers except the last four
    String protectedCreditCard = " **** **** **** " + userCreditCard.substring(12, 16);

    //Here i generate the Notification text
    //If the rideStatusID is changing to 0, then the ride has been accepted by a driver
    if (rideStatusID == 0){
      noteStringPassenger = "'\nYour ride from "+ dummyRide.getStart_location()+ " to " + dummyRide.getEnd_location() + " has been accepted!'";
      noteStringDriver = "'\nYou accepted a ride request from "+ dummyRide.getStart_location()+ " to "+ dummyRide.getEnd_location() + ".'";
    } else if (rideStatusID == 2) {
      //If the rideStatusID is changing to 3, then the ride has been marked complete by the driver
      noteStringPassenger = "'\nYour ride request from "+ dummyRide.getStart_location()+ " to "+ dummyRide.getEnd_location() + " has been marked complete!\nYour card ending in " + protectedCreditCard +" has been charged.'";
      noteStringDriver = "'\nYou completed the ride request from " + dummyRide.getStart_location() + " to " + dummyRide.getEnd_location() + ".\nPayment has been processed to your account.'";
    } else {
      noteStringPassenger = "An Error has occurred";
      noteStringDriver = "An Error has occurred";
    }

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
          "INSERT INTO USER_NOTIFICATIONS(USER_ID, NOTIFICATION_TYPE, NOTIFICATION_TEXT)"
              + "VALUES"
              + "("+dummyRide.getPassenger_id()+ ", 2, "+noteStringPassenger+"),"
              + "("+userID+", 1, "+noteStringDriver+");";
      stmt.executeUpdate(sql);
      System.out.println("Ride " + dummyRide.getRide_id() + " has been added!");
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
  //////////////////////////////////////    Car Methods   //////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////
  public static void printAllCars(){
    ArrayList<Car> dummyArray = new ArrayList<>();
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();

      String sql =
          "SELECT * FROM CAR_DETAILS";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()){
        dummyArray.add(new Car(
            rs.getInt(2),
            rs.getString(3),
            rs.getString(4),
            rs.getInt(5),
            rs.getString(6),
            rs.getString(7),
            rs.getString(8),
            rs.getInt(9)));
      }

      System.out.println("\n************ Cars ************");
      for (Car x: dummyArray){
        x.printCar();
      }
      System.out.println("************End of stored Cars************\n");


      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
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
   * Returns an arraylist of all carID's associated with a user
   * @param userID - the user associated with the cars
   * @return - an arrayList of all carID's, may be empty
   */
  public ArrayList<Integer> getAllCarsFromUser(int userID){
    ArrayList<Integer> carList = new ArrayList<>();
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT CAR_ID FROM CAR_DETAILS WHERE USER_ID='" + userID + "'";
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
        carList.add(rs.getInt(1));
      }

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return carList;
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
          "INSERT INTO CAR_DETAILS(USER_ID, CAR_MANUFACTURER, CAR_MODEL, CAR_YEAR, CAR_TYPE, LICENSE_PLATE, CAR_COLOR, CAR_SEATING) VALUES('"
              + dummyCar.getUserID()
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
      System.out.println("Car has been added!");
      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
