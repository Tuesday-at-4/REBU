package sample;

import javafx.beans.property.SimpleStringProperty;

public class Car {

  private String manufacturer;
  private String model;
  private String year;
  private String carType;
  private String licensePlate;
  private String CarColor;
  private String NumSeats;


  Car(String manufacturer, String model, String year, String carType, String licensePlate,
      String CarColor, String NumSeats) {
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

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
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

  public void setNumSeats(String NumSeats) {
    this.NumSeats = NumSeats;
  }

  public String getNumSeats() {
    return NumSeats;
  }
}
