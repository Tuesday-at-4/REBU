/*
 * Dashboard
 * Initializes the dashboard for the home page
 */
package sample;

import java.awt.TextField;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Dashboard_Controller {

    @FXML
    TextField textFieldNews;

    public void initialize(){
    }

    public void handlePassengerButton(ActionEvent event){
        Main.createNewScene(event,"Passenger.FXML");
    }

    @FXML
    private void handleAccountButton(ActionEvent event){
        Main.createNewScene(event,"Account_Summary.FXML");
    }

    @FXML
    private void handleDriverButton(ActionEvent event){
        Main.createNewScene(event,"Driver.FXML");
    }



}
