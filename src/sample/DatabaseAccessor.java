/***********************************************************
 * File   : DatabaseAccessor.Java
 * Author(s)  : Michael Carracino
 * Class   : CEN 3031
 * Purpose : Manages all interactions with the database.
 ************************************************************/

package sample;

import com.sun.istack.internal.NotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        dummyAccount.printAccountDetails();
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
      "VALUES("
          + dummyAccount.getUser_id() + ",'"
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
  /*public String[] getNotifications(int userID){
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
    Car dummyCar = new Car("","","","","");
    return dummyCar;
  }
  public void deleteCar(int car_ID){};
  public Rides[] getRides(int user_ID){ 
    Rides[] dummyRides = {new Rides()};
    return dummyRides;
  };
  public void createRide(Rides dummyRide){};
  public Rides editRide(Rides dummyRide){};
  public void deleteRide(int rideID){};
  */
}
