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

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getCarType() {
    return carType;
  }

  public void setCarType(String carType) {
    this.carType = carType;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setCarColor(String CarColor) {
    this.CarColor = CarColor;
  }

  public String getCarColor() {
    return CarColor;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public void setNumSeats(int NumSeats) {
    this.NumSeats = NumSeats;
  }

  public int getNumSeats() {
    return NumSeats;
  }

  public int getCarID() {
    return carID;
  }

  public void setCarID(int carID) {
    this.carID = carID;
  }
}
