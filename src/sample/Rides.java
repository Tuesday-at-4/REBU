/***********************************************************
 * File   : Rides.Java
 * Author(s)  : Michael Carracino
 * Class   : CEN 3031
 * Purpose : Stores the user data pulled from the database
 ************************************************************/
package sample;

public class Account {

  private  static String firstName, lastName, phone, username, password, email, dateOfBirth;
  private static int userID, carOneID, carTwoID;
  // static variable of a current user's account, for the sake of updating account details for now


  public Account(int userID, String username, String password,String firstName, String lastName, String email, String phone, String dateOfBirth,int carOneID,int carTwoID) {
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.carOneID = carOneID;
    this.carTwoID = carTwoID;
  }


  /*** ACCESSOR METHODS ***/
  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  /*** SETTER METHODS ***/
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setCarTwoID(int carTwoID) {
    this.carTwoID = carTwoID;
  }

  public void setCarOneID(int carOneID) {
    this.carOneID = carOneID;
  }

  public void setUser_id(int user_id) {
    this.userID = user_id;
  }

  /*** HELPER METHOD ***/
  public String getName() {
    return getFirstName() + " " + getLastName();
  }

  public int getUser_id() {
    return userID;
  }

  public int getCarOneID() {
    return carOneID;
  }

  public int getCarTwoID() {
    return carTwoID;
  }

  /**
   * A print Account for verifying your account with print statements
   */
  public void printAccountDetails(){
    System.out.println(""
        + "\nUserID:  "+userID
        + "\nUsername:  "+username
        + "\nPassword:  "+password
        + "\nFirstName:  "+firstName
        + "\nLastName:  "+lastName
        + "\nPhone:  "+phone
        + "\nEmail:  "+email
        + "\nDateOfBirth:  "+dateOfBirth
        + "\nCarOneID:  "+carOneID
        + "\nCarTwoID:  "+carTwoID);
  }
}
