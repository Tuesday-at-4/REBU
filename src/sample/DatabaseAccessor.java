package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccessor {

  /** IN PROGRESS
   * This function will take in an account object and build a row in the database.
   * The account will be assigned a unique ID number
   */
  public static void add_account(){
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

  /** IN PROGRESS
   * This function searches for a row that has this username and password, then removes it from the database.
   * The process is irreversible.
   * @param accountID - The unique ID of the account
   */
  public static void deleteAccount(int accountID){
  }

  /**
   * gets the users account from some parameters
   * @return - The account of the desired user
   */
  public static Account getAccount(){
    Account dummy = new Account("Kali","Ollie","dummy@email.com","123-456-7890","01/01/1001","user","pass");
    return dummy;
  }
}
