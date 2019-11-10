package sample;

public class Car {
  private int carID;
  private String manufacturer;
  private String model;
  private int year;
  private String carType;
  private String licensePlate;
  private String CarColor;
  private int NumSeats;


  Car(int carID, String manufacturer, String model, int year, String carType, String licensePlate,
      String CarColor, int NumSeats) {
    this.carID = carID;
    this.manufacturer = manufacturer;
    this.model = model;
    this.year = year;
    this.carType = carType;
    this.licensePlate = licensePlate;
    this.CarColor = CarColor;
    this.NumSeats = NumSeats;

  }

  //Accessor Methods:

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

}
