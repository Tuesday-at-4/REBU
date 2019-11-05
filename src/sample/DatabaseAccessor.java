/***********************************************************
 * File   : DatabaseAccessor.Java
 * Author(s)  : Michael Carracino
 * Class   : CEN 3031
 * Purpose : Manages all interactions with the database.
 ************************************************************/

package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccessor {
/*
  public int searchForAccount(String username, String password){
    int userID = 0;
    return userID;
  }
  public Account getAccount(int userID){
    Account dummyAccount = new Account("","","","","","","");
    return dummyAccount;
  }
  public void createAccount(Account dummyAccount){
  }
  public Account editAccount(Account givenAccount){
    Account dummyAccount = new Account("","","","","","","");
    return dummyAccount;
  }
  public void deleteAccount(int userID){
  }
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

  /**
   * This function will take in an account object and build a row in the database. The account will
   * be assigned a unique ID number
   */
  public static void test_database() {
    final String JDBC_DRIVER = "res.org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/HR";
    final String PASS = "";
    //  Database credentials
    Connection conn = null;
    Statement stmt = null;
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      String USER = "";
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();
      String sql = "SELECT * FROM USER_ACCOUNT";
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        System.out.println(rs.getString(1));
      }

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
