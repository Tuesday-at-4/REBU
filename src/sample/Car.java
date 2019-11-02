/*
 * Car
 * Classifying cars by manufacturer, model, year, type, and license plate address.
 */
package sample;

import javafx.beans.property.SimpleStringProperty;

public class Car {
  private SimpleStringProperty manufacturer;
  private SimpleStringProperty model;
  private SimpleStringProperty year;
  private SimpleStringProperty carType;
  private SimpleStringProperty licensePlate;

  Car(String manufacturer, String model, String year, String carType, String licensePlate){
    this.manufacturer = new SimpleStringProperty(manufacturer);
    this.model = new SimpleStringProperty(model);
    this.year = new SimpleStringProperty(year);
    this.carType = new SimpleStringProperty(carType);
    this.licensePlate = new SimpleStringProperty(licensePlate);
  }
  public String getManufacturer(){
    return manufacturer.get();
  }
  public void setManufacturer(String manufacturer){
    this.manufacturer.set(manufacturer);
  }
  public String getModel(){
    return model.get();
  }
  public void setModel(String model){
    this.model.set(model);
  }
  public String getYear(){
    return year.get();
  }
  public void setYear(String year){
    this.year.set(year);
  }
  public String getCarType(){
    return carType.get();
  }
  public void setCarType(String carType){
    this.carType.set(carType);
  }
  public String getLicensePlate(){
    return licensePlate.get();
  }
  public void setLicensePlate(String licensePlate){
    this.licensePlate.set(licensePlate);
  }
}
