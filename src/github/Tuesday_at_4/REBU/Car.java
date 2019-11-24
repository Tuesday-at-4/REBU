/**
 * *********************************************************
 * File : Car.Java
 * Author(s): Ben Cano
 * Class : CEN 3031
 * Purpose : Creates the car object.
 * **********************************************************
 */

package github.Tuesday_at_4.REBU;

public class Car {
  private int carID;
  private int userID;
  private String manufacturer;
  private String model;
  private int year;
  private String carType;
  private String licensePlate;
  private String CarColor;
  private int NumSeats;

  /**
   * Constructor without the carID, general use
   * @param userID
   * @param manufacturer
   * @param model
   * @param year
   * @param carType
   * @param licensePlate
   * @param CarColor
   * @param NumSeats
   */
  public Car(int userID, String manufacturer, String model, int year, String carType,
      String licensePlate, String CarColor, int NumSeats) {
    this.userID = userID;
    this.manufacturer = manufacturer;
    this.model = model;
    this.year = year;
    this.carType = carType;
    this.licensePlate = licensePlate;
    this.CarColor = CarColor;
    this.NumSeats = NumSeats;
  }

  /**
   * Constructor without the carID, Only used in the DB accessor class, DON'T USE
   * @param userID
   * @param manufacturer
   * @param model
   * @param year
   * @param carType
   * @param licensePlate
   * @param CarColor
   * @param NumSeats
   */
  Car(int carID, int userID, String manufacturer, String model, int year, String carType, String licensePlate,
      String CarColor, int NumSeats) {
    this.carID = carID;
    this.userID = userID;
    this.manufacturer = manufacturer;
    this.model = model;
    this.year = year;
    this.carType = carType;
    this.licensePlate = licensePlate;
    this.CarColor = CarColor;
    this.NumSeats = NumSeats;
  }

  //Accessor Methods:

  public int getUserID() { return  userID; }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getModel() {
    return model;
  }

  public int getYear() { return year; }

  public String getCarType() {
    return carType;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public String getCarColor() {
    return CarColor;
  }

  public int getNumSeats() { return NumSeats; }

  public void setCarID(int carID) {
    this.carID = carID;
  }

  //Setter Methods:
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public void setCarType(String carType) { this.carType = carType; }

  public void setCarColor(String CarColor) {
    this.CarColor = CarColor;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public void setNumSeats(int NumSeats) {
    this.NumSeats = NumSeats;
  }

  public int getCarID() {
    return carID;
  }

  public void printCar(){
    System.out.println("User_ID: " +userID
        +"\nManufacturer: "+manufacturer
        +"\nModel: "+model
        +"\nYear: "+year
        +"\nCarType: "+carType
        +"\nLicensePlate: "+licensePlate
        +"\nCarColor: "+CarColor
        +"\nNumSeats: "+NumSeats
    );
  }
}
