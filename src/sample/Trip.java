/***********************************************************
 * File   : Passenger_Controller.Java
 * Author  :  Wilkenson Germain
 * Class   : CEN 3031
 * Purpose : Have getters and setters for retrieving and updating value of our variables
 ************************************************************/


package sample;

import java.time.LocalDate;
import java.time.LocalTime;

public class Trip {
    private String pendRideId;
    private String pendFrom;
    private String pendTo;
    private LocalDate pendDate;
    private LocalTime pendTime;

    Viaje(String pendRideId, String pendFrom, String pendTo, LocalDate pendDate, LocalTime pendTime) {
        this.pendDate = pendDate;
        this.pendFrom = pendFrom;
        this.pendRideId = pendRideId;
        this.pendTime = pendTime;
        this.pendTo = pendTo;
    }

    public LocalDate getPendDate() {
        return pendDate;
    }

    public String getPendFrom() {
        return pendFrom;
    }

    public String getPendRideId() {
        return pendRideId;
    }

    public LocalTime getPendTime() {
        return pendTime;
    }

    public String getPendTo() {
        return pendTo;
    }
}
