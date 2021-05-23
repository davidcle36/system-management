package lambda;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Lambda function, SceneLambda Change, is use to change to change.
 */
@FunctionalInterface
public interface SceneLambda {
    /**
     * discussion of lambda. A lambda function is created to passed down the values. The scene will change.
     * onActionAddCustomerHandler, and onActionRecord in MainForm utilize the change function.
     *
     * @param stage       Stage
     * @param actionEvent ActionEvent
     * @param scene       Parent
     * @param path  view file path
     *
     * @throws IOException scene change error
     *
     */
    void change(Stage stage, ActionEvent actionEvent, Parent scene, String path) throws IOException;
}
