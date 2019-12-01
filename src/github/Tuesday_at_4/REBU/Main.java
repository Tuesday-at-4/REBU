/***********************************************************
 * File   : Main.Java
 * Author(s)  : Michael Carracino
 * Class   : CEN 3031
 * Purpose : Initializes the GUI program and holds a function for swapping scenes
 ************************************************************/
package github.Tuesday_at_4.REBU;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // static variable of a current user's account, for the sake of updating account details for now
    public static Account currentUser;

    static ArrayList<Rides> pendingRides= new ArrayList<Rides>();
    static ArrayList<Rides> confirmedRides = new ArrayList<Rides>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        currentUser = DatabaseAccessor.getAccount(1);
        Parent rootMain = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        //Parent rootMain = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene sceneMain = new Scene(rootMain);
        primaryStage.setScene(sceneMain);
        sceneMain.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        primaryStage.show();
    }
    public static void createNewScene(Event event, String newFileFXML){
        Parent newRoot = null;
        try {
            newRoot = FXMLLoader.load(Main.class.getResource(newFileFXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene newScene = new Scene(newRoot);
        newScene.getStylesheets().add(Main.class.getResource("Style.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

}
