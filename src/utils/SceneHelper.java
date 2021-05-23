/** Helper Class Scene Change */
package utils;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * SceneHelper Class.
 * */
public class SceneHelper {

    /**
     * One of two overload method to change scene when button is press.
     *
     *
     * @param stage the stage
     * @param event the event
     * @param scene the scene
     * @param path the fxml path
     * @throws IOException file not found
     */
    public static void ChangeSceneOnButton(Stage stage, ActionEvent event,
                                           Parent scene, String path) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(SceneHelper.class.getResource(path));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
