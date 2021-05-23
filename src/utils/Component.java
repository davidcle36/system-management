/**
 * Component Class
 * */
package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**Component Class for showing common JavaFX Dialog*/
public class Component {

    /**
     * Alert Error Dialog. A template to show a dialog whenever a exception is
     * throw if needed
     *
     * @param message the message of the error
     */
    public static void ErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }


    /**
     * Alert Warning Dialog. A template to show a dialog whenever a exception is
     * throw if needed
     *
     * @param message the message of the error
     */
    public static void WarningDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Alert Confirmation Dialog. A template to show a dialog whenever a exception is
     * throw if needed
     *
     * @param message the message of the error
     * @return boolean is true for yes, false for no
     */
    public static boolean ConfirmationDialog(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
           return true;
        }
        return false;
    }
}
