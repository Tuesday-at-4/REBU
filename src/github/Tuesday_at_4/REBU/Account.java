/***********************************************************
 * File   : Account.Java
 * Author(s)  : Michael Carracino
 * Class   : CEN 3031
 * Purpose : Stores the user data pulled from the database
 ************************************************************/
package github.Tuesday_at_4.REBU;

import java.time.LocalDate;

public class Account {
  private String firstName;
  private String lastName;
  private String phone;
  private String username;
  private String password;
  private String email;
  private String creditCard;
  private int userID;
  private LocalDate dateOfBirth;

  /**
   * A constructor without UserID, general use constructor
   * @param username
   * @param password
   * @param firstName
   * @param lastName
   * @param email
   * @param phone
   * @param dateOfBirth
   */
  public Account(String username, String password, String firstName, String lastName, String phone, String email, LocalDate dateOfBirth, String creditCard) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.creditCard = creditCard;
  }

  /**
   * A constructor WITH userID DO BOT USE
   * @param userID
   * @param username
   * @param password
   * @param firstName
   * @param lastName
   * @param email
   * @param phone
   * @param dateOfBirth
   */
  public Account(int userID, String username, String password, String firstName, String lastName, String email, String phone, LocalDate dateOfBirth, String creditCard){
    this.userID = userID;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.creditCard = creditCard;
  }


  /*** GETTER METHODS ***/
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

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public int getUserID() {
    return userID;
  }

  public String getCreditCard() {
    return creditCard;
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

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setUser_id(int user_id) {
    this.userID = user_id;
  }

  /*** HELPER METHOD ***/
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
        + "\nCreditCar:   "+creditCard);
  }
}
