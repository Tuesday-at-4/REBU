/*
 * Account
 * Basic fields and methods related to a user's account.
 */
package sample;

public class Account {

  private String firstName, lastName, phone, username, password, email, dateOfBirth;

  // static variable of a current user's account, for the sake of updating account details for now
  public static Account currentUser;

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

  /*** HELPER METHOD ***/
  public String getName() {
    return getFirstName() + " " + getLastName();
  }

  public Account(String firstName, String lastName, String email, String phone, String dateOfBirth,
      String username, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.dateOfBirth = dateOfBirth;
    this.username = username;
    this.password = password;
  }
}
