package controller;

import dao.CountryDaoImpl;
import dao.CustomerDaoImpl;
import dao.FirstLevelDivisionDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import utils.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Customer Controller Class for adding, deleting, and updating.
 * */

public class CustomerController implements Initializable {
    Stage stage;
    Parent scene;
    FirstLevelDivision fld;
    Country country;

    Customer selectedCustomer;
    ObservableList<Country> countryList = FXCollections.observableArrayList();
    ObservableList<FirstLevelDivision> fldList = FXCollections.observableArrayList();
    ObservableList<FirstLevelDivision> filteredFldList = FXCollections.observableArrayList();
    boolean isUpdate = false;

    @FXML
    private Label labelCustomerId;

    @FXML
    private Label labelCustomerForm;

    @FXML
    private Label labelCustomerAddress;

    @FXML
    private Label labelCustomerPhone;

    @FXML
    private Label labelCustomerName;

    @FXML
    private Label labelCustomerPostalCode;

    @FXML
    private TextField TextCustomerId;

    @FXML
    private TextField TextCustomerAddress;

    @FXML
    private TextField TextCustomerName;

    @FXML
    private TextField TextCustomerPhone;

    @FXML
    private TextField TextCustomerPostalCode;

    @FXML
    private Label labelCustomerDivision;

    @FXML
    private ComboBox<FirstLevelDivision> ComboBoxCustomerDivision;

    @FXML
    private Label labelCustomerCountry;

    @FXML
    private ComboBox<Country> ComboBoxCustomerCountry;

    @FXML
    private Button BtnCustomerSave;

    @FXML
    private Button BtnCustomerCancel;

    /**
     * An Action Handler, Button, that save newly added part. When saved, the
     * helper function, ChangeSceneOnButton will switch back to main form.
     *
     * @param event action event
     * @throws Exception Trigger Dialog box when part is not found,
     * @throws NumberFormatException if data is invalid according the args of
     * that object, throw error.
     */
    @FXML
    public void onActionCustomerSaveHandler(ActionEvent event) throws Exception, NumberFormatException, NullPointerException{
        try {

                CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
                fld = ComboBoxCustomerDivision.getSelectionModel().getSelectedItem();

            if (!isUpdate) {
                customerDaoImpl.addCustomer(TextCustomerName.getText(), TextCustomerAddress.getText(),
                        TextCustomerPostalCode.getText(), TextCustomerPhone.getText(), SystemInfo.getUser(),
                        fld.getDivisionId());
            }else{
                selectedCustomer.setName(TextCustomerName.getText());
                selectedCustomer.setAddress(TextCustomerAddress.getText());
                selectedCustomer.setPostalCode( TextCustomerPostalCode.getText());
                selectedCustomer.setPhone( TextCustomerPhone.getText());
                selectedCustomer.setLastUpdatedBy(SystemInfo.getUser());
                selectedCustomer.setDivisionId(fld.getDivisionId());
                customerDaoImpl.updateCustomer(selectedCustomer);
            }

            SceneHelper.ChangeSceneOnButton(stage, event, scene, FxmlPath.MAIN_FORM.toString());
        }catch(NumberFormatException e){
            Component.ErrorDialog(e.getMessage());
        }
        catch(NullPointerException e){
            Component.ErrorDialog(Translation.ERROR.MISSING_FIELD.toString());
        }
        catch (Exception e) {
            Component.ErrorDialog(e.getMessage());
        }

    }

    /**
     * An Action Handler that change scene to Main Form.
     * Using a constant enum, {@link FxmlPath#MAIN_FORM} the point of change is
     * limited when updated, thus, limiting bugs when changes to fxml path file
     * occurs.
     *
     * @param event action event
     */
    @FXML
    public void onActionCustomerCancelHandler(ActionEvent event) {
        try {
            SceneHelper.ChangeSceneOnButton(stage, event, scene, FxmlPath.MAIN_FORM.toString());
        } catch (Exception e) {
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler,Combo Box, so when a user select a country, it will also filter the division base on what country is selected
     *
     * @param event action event
     */
    @FXML
    public void onActionCBCustomerCountry(ActionEvent event) {
        country = ComboBoxCustomerCountry.getSelectionModel().getSelectedItem();

        filteredFldList.clear();
        for (FirstLevelDivision f : fldList) {
            if (country.getCountryId() == f.getCountryId()) {
                filteredFldList.add(f);
            }
        }

        ComboBoxCustomerDivision.setDisable(false);
        ComboBoxCustomerDivision.setItems(filteredFldList);

    }

    @FXML
    public void onActionCBCustomerDivision(ActionEvent event) {

    }

    /**
     * A method to show select customer that was selected in the main form.
     *
     * @param customer is the instance to be updated
     *
     */
    public void showCustomer(Customer customer) {
        isUpdate = true;
        selectedCustomer = customer;

        TextCustomerId.setText(String.valueOf(customer.getId()));
        TextCustomerName.setText(String.valueOf(customer.getName()));
        TextCustomerAddress.setText(String.valueOf(customer.getAddress()));
        TextCustomerPostalCode.setText(String.valueOf(customer.getPostalCode()));
        TextCustomerPhone.setText(String.valueOf(customer.getPhone()));

        for (FirstLevelDivision f : fldList) {
            if (f.getDivisionId() == customer.getDivisionId()) {
                ComboBoxCustomerCountry.getSelectionModel().select(f.getCountryId() - 1);

                for (FirstLevelDivision f2 : fldList) {
                    if (f.getCountryId() == f2.getCountryId()) {
                        filteredFldList.add(f2);
                    }
                }

                ComboBoxCustomerDivision.setItems(filteredFldList);
                ComboBoxCustomerDivision.getSelectionModel().select(f);
                ComboBoxCustomerDivision.setDisable(false);

                return;
            }
        }

        labelCustomerForm.setText(Translation.MAIN.UPDATE_CUSTOMER.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryDaoImpl countryDao = new CountryDaoImpl();
        FirstLevelDivisionDaoImpl FirstLvlDao = new FirstLevelDivisionDaoImpl();

        if(isUpdate){
            labelCustomerForm.setText(Translation.MAIN.ADD_CUSTOMER.toString());
        }else{
            labelCustomerForm.setText(Translation.MAIN.UPDATE_CUSTOMER.toString());
        }


        ComboBoxCustomerCountry.setPromptText(Translation.CUSTOMER.COUNTRY_SELECT.toString());
        ComboBoxCustomerDivision.setPromptText(Translation.CUSTOMER.DIVISION_SELECT.toString());
        labelCustomerAddress.setText(Translation.CUSTOMER.ADDRESS.toString());
        labelCustomerCountry.setText(Translation.CUSTOMER.COUNTRY.toString());
        labelCustomerName.setText(Translation.CUSTOMER.NAME.toString());
        labelCustomerPhone.setText(Translation.CUSTOMER.PHONE.toString());
        labelCustomerPostalCode.setText(Translation.CUSTOMER.POSTAL_CODE.toString());
        TextCustomerId.setPromptText(Translation.COMMON.AUTO_GENERATED.toString());
        BtnCustomerSave.setText(Translation.COMMON.SAVE.toString());
        BtnCustomerCancel.setText(Translation.COMMON.CANCEL.toString());

        try {
            fldList = FirstLvlDao.getFirstLevelDivisionsList();
            countryList = countryDao.getCountryList();
            ComboBoxCustomerCountry.setItems(countryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
