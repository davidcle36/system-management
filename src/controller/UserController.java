/**
 * User class Controller
 */
package controller;

import dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import utils.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * User Controller Class
 * */
public class UserController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField usernameText;

    @FXML
    private TextField passwordText;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label languageLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label usernameLabel;

    /**
     * An Action Handler to login in with username and password from text field.
     * user.getUser(u, p) is a method to that called a service to the database and take username
     * and password.
     *
     * @param event action event
     * @throws Exception Trigger Dialog box when user is not found,
     */
    @FXML
    public void onActionLoginHandler(ActionEvent event) throws Exception {
        FileWriter myWriter = null;
        try {
            UserDaoImpl userImpl = new UserDaoImpl();
            userImpl.getUser(usernameText.getText(), passwordText.getText());

            if (User.getUserName() == null) {
                throw new Exception(Translation.LOGIN.UNAUTHORIZED.toString());
            }
            SystemInfo.setUser(usernameText.getText());

            myWriter = new FileWriter("login_activity.txt", true);
            myWriter.append("Successful - User \"" + usernameText.getText() + "\" login in at " + MyTime.getCurrentUtcTime() + "\n");
            myWriter.close();

            SceneHelper.ChangeSceneOnButton(stage,
                    event,
                    scene,
                    FxmlPath.MAIN_FORM.toString());

        } catch (Exception e) {
            myWriter = new FileWriter("login_activity.txt", true);
            myWriter.append("Unsuccessful - User \"" + usernameText.getText() + "\" attempted to login in at " + MyTime.getCurrentUtcTime() + "\n");
            myWriter.close();
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * Exit the application when the "Exit" button is clicked.
     *
     * @param event JavaFx event that takes user input when action taking
     */
    @FXML
    public void onActionCancelHandler(ActionEvent event) {
        System.exit(0);
    }

    /**
     * User initialize controller. Set text base on location and load scene.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setText(Translation.LOGIN.LOGIN.toString());
        cancelButton.setText(Translation.LOGIN.CANCEL.toString());
        languageLabel.setText(SystemInfo.getLocation() + " - " + SystemInfo.getLanguage().toUpperCase());
        usernameLabel.setText(Translation.LOGIN.USERNAME.toString());
        passwordLabel.setText(Translation.LOGIN.PASSWORD.toString());
        passwordText.setPromptText(Translation.LOGIN.PASSWORD_PROMPT.toString());
        usernameText.setPromptText(Translation.LOGIN.USERNAME_PROMPT.toString());
    }
}
