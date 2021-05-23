/** Main Class */
package main;

import controller.CustomerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.SystemInfo;

import java.util.Locale;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Main method. Starts the JavaFx Scene and load the initial scene, Login View.
     * */
    @Override
    public void start(Stage primaryStage) throws Exception {
        SystemInfo sys = new SystemInfo();

        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
        primaryStage.setTitle("USchedule");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
