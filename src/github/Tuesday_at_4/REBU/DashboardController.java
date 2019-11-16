/***********************************************************
 * File   : DashboardController.Java
 * Author(s)  : Michael Carracino
 * Class   : CEN 3031
 * Purpose : Handles the events on the hompepage/dashboard
 ************************************************************/
package github.Tuesday_at_4.REBU;

import java.awt.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashboardController {

    @FXML
    TextField textFieldNews;

    public void initialize(){
        System.out.println("");
    }

    public void handlePassengerButton(ActionEvent event){
        Main.createNewScene(event,"Passenger.FXML");
    }

    @FXML
    private void handleAccountButton(ActionEvent event){
        Main.createNewScene(event,"AccountSummary.FXML");
    }

    @FXML
    private void handleDriverButton(ActionEvent event){
        Main.createNewScene(event,"DriverPrompt.FXML");
    }
}