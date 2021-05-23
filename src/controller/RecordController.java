/**
 * Record Class
 * */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import utils.FxmlPath;
import utils.Record;
import utils.SceneHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Record Class
 * */
public class RecordController implements Initializable {
    int[] monthCountArr = new int[12];
    Stage stage;
    Parent scene;

    @FXML
    private Label jan;

    @FXML
    private Label feb;

    @FXML
    private Label mar;

    @FXML
    private Label apr;

    @FXML
    private Label may;

    @FXML
    private Label jun;

    @FXML
    private Label jul;

    @FXML
    private Label aug;

    @FXML
    private Label sep;

    @FXML
    private Label oct;

    @FXML
    private Label nov;

    @FXML
    private Label dec;

    @FXML
    private Text TextTypeReport;

    @FXML
    private Button backBtn;

    /**
     * Action method, Button event, to return to main view scene.
     * */
    @FXML
    public void onActionBack(ActionEvent event) throws IOException {
        SceneHelper.ChangeSceneOnButton(stage, event, scene, FxmlPath.MAIN_FORM.toString());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Record record = new Record();

        try {
           Pair<int[], String> p = record.getCustomerAppointmentCountRecord();
           int[] monthCountArr = p.getKey();
           String typeRecord = p.getValue();

            jan.setText(String.valueOf(monthCountArr[0]));
            feb.setText(String.valueOf(monthCountArr[1]));
            mar.setText(String.valueOf(monthCountArr[2]));
            apr.setText(String.valueOf(monthCountArr[3]));
            may.setText(String.valueOf(monthCountArr[4]));
            jun.setText(String.valueOf(monthCountArr[5]));
            jul.setText(String.valueOf(monthCountArr[6]));
            aug.setText(String.valueOf(monthCountArr[7]));
            sep.setText(String.valueOf(monthCountArr[8]));
            oct.setText(String.valueOf(monthCountArr[9]));
            nov.setText(String.valueOf(monthCountArr[10]));
            dec.setText(String.valueOf(monthCountArr[11]));

            TextTypeReport.setText(typeRecord);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
