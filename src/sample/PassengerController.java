/***********************************************************
 * File   : Passenger_Controller.Java
 * Author  :  Wilkenson Germain, Benjamin Cano
 * Class   : CEN 3031
 * Purpose : Allows the Passenger to create Rides, see their pending Rides, and their accepted Rides.
 ************************************************************/

package sample;

/* Line 12-25 are necessary import statements needed to connect the code with
   corresponding .fxml file(s)*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class PassengerController {

    /* all of the @FXML code does with controls and containers used in the Passenger.fxml  */

    @FXML
    private Tab tabPassengerMain;

    @FXML
    private Button home_Button;

    @FXML
    private Button Edit_Account;

    @FXML
    private Tab tabRideScheduler;

    @FXML
    private TextField textFieldStartLocation;

    @FXML
    private TextField textFieldEndLocation;

    @FXML
    private TextField textFieldStartTime;

    @FXML
    private TextField textFieldStartDate;

    @FXML
    private Button btnCreateRide;

    @FXML
    private Tab tabPendingRides;

    @FXML
    private TableView<Trip> tvPendingRides;

    @FXML
    private TableView<Trip> tvAcceptedRides;

    @FXML
    private TableColumn<Trip, String> pendRideId;

    @FXML
    private TableColumn<Trip, String> pendFrom;

    @FXML
    private TableColumn<Trip, String> pendTo;

    @FXML
    private TableColumn<Trip, LocalDate> pendDate;

    @FXML
    private TableColumn<Trip, LocalTime> pendTime;

    @FXML
    private Tab tabAccptedRides;

    @FXML
    private TableColumn<?, String> accptRideId;

    @FXML
    private TableColumn<?, String> accptFrom;

    @FXML
    private TableColumn<?, String> accptTo;

    @FXML
    private TableColumn<?, String> accptDate;

    @FXML
    private TableColumn<?, String> accptTime;

    /* 103-106 will be expanded upon to record scheduled rides and have them stored in the DB when it works */

    @FXML
    void createRide(ActionEvent event) {

    }

    /* The next block of code (108-111) returns you to the dashboard via the home button. */

    @FXML
    void returnHome(ActionEvent event) {
        Main.createNewScene(event, "Dashboard.fxml");
    }

    /*  The next block of code (115-118) returns you to the account summary page via the edit account button. */

    @FXML
    private void edit_Account(Event event) {
        Main.createNewScene(event, "Account_Summary.fxml");
    }

    /* cellValueFactory - set to specify how to populate all cells within a single TableColumn
     * propertyValueFactory<1, 2>  1- takes a parameter for type of the class contained within the TableView list
     *2- takes a another parameter type of the class contained within the TableColumn cells
     * They're used together get values to populate on the table view columns and cells */

    public void initialize() {
        ObservableList<Trip> data = populateList();
        pendRideId.setCellValueFactory(new PropertyValueFactory<Trip, String>("pendRideId"));
        pendFrom.setCellValueFactory(new PropertyValueFactory<Trip, String>("pendFrom"));
        pendTo.setCellValueFactory(new PropertyValueFactory<Trip, String>("pendTo"));
        pendDate.setCellValueFactory(new PropertyValueFactory<Trip, LocalDate>("pendDate"));
        pendTime.setCellValueFactory(new PropertyValueFactory<Trip, LocalTime>("pendTime"));

        tvPendingRides.setItems(data);
    }

    /* Line 138 - 141 placeholder values until DB works */

    public static ObservableList<Trip> populateList() {
        return FXCollections.observableArrayList(new Trip("121289", "Miami", "Orlando", LocalDate.of(2019, Month.NOVEMBER, 12), LocalTime.of(2, 23)));

    }
}
