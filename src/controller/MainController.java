/**
 * Main Controller for Main Form View
 * */
package controller;

import dao.AppointmentDaoImpl;
import dao.CustomerDaoImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lambda.SceneLambda;
import model.Appointment;
import model.Customer;
import utils.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Main Controller for Main Form View
 * */
public class MainController implements Initializable {
    Stage stage;
    Parent scene;

    ObservableList<Customer> customersList = FXCollections.observableArrayList();
    ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    ObservableList<Appointment> appointmentMonth = FXCollections.observableArrayList();
    ObservableList<Appointment> appointmentWeek = FXCollections.observableArrayList();
    ObservableList<Appointment> upcomingAppointmentList = FXCollections.observableArrayList();
    boolean isStart = true;

    ArrayList<String> months = new ArrayList<String>(){{
       add(Month.JANUARY.name());
       add(Month.FEBRUARY.name());
       add(Month.MARCH.name());
       add(Month.APRIL.name());
       add(Month.MAY.name());
       add(Month.JUNE.name());
       add(Month.JULY.name());
       add(Month.AUGUST.name());
       add(Month.SEPTEMBER.name());
       add(Month.OCTOBER.name());
       add(Month.NOVEMBER.name());
       add(Month.DECEMBER.name());
    }};

    ArrayList<String> daysOfWeek = new ArrayList<String>(){{
        add(DayOfWeek.SUNDAY.name());
        add(DayOfWeek.MONDAY.name());
        add(DayOfWeek.TUESDAY.name());
        add(DayOfWeek.WEDNESDAY.name());
        add(DayOfWeek.THURSDAY.name());
        add(DayOfWeek.FRIDAY.name());
        add(DayOfWeek.SATURDAY.name());
    }};
    private int monthCount = 0;
    private int dayCount = 0;
    private int weekCount = 0;

    Customer selectedCustomer;
    Appointment selectedAppointment;

    @FXML
    private Button addCustomerBtn;

    @FXML
    private Button addAppointmentBtn;

    @FXML
    private Button updateCustomerBtn;

    @FXML
    private Button deleteCustomerBtn;

    @FXML
    private Tab appointmentTab;

    @FXML
    private Tab customerTab;

    @FXML
    private TabPane mainTabs;


    @FXML
    private Label labelMonth;

    @FXML
    private Label labelDayOfWeek;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, Integer> ColCustomerId;

    @FXML
    private TableColumn<Customer, String> ColCustomerName;

    @FXML
    private TableColumn<Customer, String> ColCustomerAddress;

    @FXML
    private TableColumn<Customer, String> ColCustomerPostalCode;

    @FXML
    private TableColumn<Customer, String> ColCustomerPhone;

    @FXML
    private TableColumn<Customer, Timestamp> ColCustomerCreateDate;

    @FXML
    private TableColumn<Customer, String> ColCustomerCreatedBy;

    @FXML
    private TableColumn<Customer, Timestamp> ColCustomerLastUpdate;

    @FXML
    private TableColumn<Customer, String> ColCustomerLastUpdatedBy;

    @FXML
    private TableColumn<Customer, Integer> ColCustomerDivisionId;

    @FXML
    private Button updateAppointmentBtn;

    @FXML
    private Button deleteAppointmentBtn;

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, Integer> ColAppointmentId;

    @FXML
    private TableColumn<Appointment, String> ColAppointmentTitle;

    @FXML
    private TableColumn<Appointment, String> ColAppointmentDescription;

    @FXML
    private TableColumn<Appointment, String> ColAppointmentLocation;

    @FXML
    private TableColumn<Appointment, Integer> ColAppointmentContactId;

    @FXML
    private TableColumn<Appointment, String> ColAppointmentContactName;

    @FXML
    private TableColumn<Appointment, String> ColAppointmentType;

    @FXML
    private TableColumn<Appointment, ZonedDateTime> ColAppointmentStart;

    @FXML
    private TableColumn<Appointment, ZonedDateTime> ColAppointmentEnd;

    @FXML
    private TableColumn<Appointment, Integer> ColAppointmentCustomerId;

    @FXML
    private TableColumn<Customer, String> ColCustomerDivision;

    @FXML
    private RadioButton RadioButtonMonth;

    @FXML
    private ToggleGroup aview;

    @FXML
    private RadioButton RadioButtonWeek;

    @FXML
    private Button nextDate;

    @FXML
    private Button prevDate;

    @FXML
    private Button btnRecord;

    /**
     * discussion of lambda. A lambda function is created to passed down the values. The scene will change.
     *
     * @see lambda.SceneLambda
     * */
    public SceneLambda sceneLambda = (stage, event, scene, path) -> {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(SceneHelper.class.getResource(path));
        stage.setScene(new Scene(scene));
        stage.show();
    };

    /**
     * An Action Handler, Button, that change scene and show record. A lambda function is provided, sceneLambda.change to
     * when button is press to record view.
     *
     * @param event action event
     * @see lambda.SceneLambda
     */
    @FXML
    public void onActionRecord(ActionEvent event){
        try {
            /**
             * discussion of lambda. Change the current scene to a record view by passing the args to the lambda function
             *
             * */
            sceneLambda.change(stage, event, scene, FxmlPath.RECORD_FORM.toString());
        } catch (NullPointerException e) {
            Component.ErrorDialog(Translation.ERROR.EMPTY_SELECT.toString());
        } catch (IOException e) {
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler, Button, that change scene and show customer form. A lambda function is provided, sceneLambda.change to
     * when button is press to customer form.
     *
     * @param event action event
     * @throws IOException IOException
     * @throws NullPointerException null selection
     */
    @FXML
    public void onActionAddCustomerHandler(ActionEvent event) throws IOException, NullPointerException {
        try {
            /**
             * discussion of lambda. Change the current scene to a customer form view by passing the args to the lambda function
             * */
            sceneLambda.change(stage, event, scene, FxmlPath.CUSTOMER_FORM.toString());
        } catch (NullPointerException e) {
            Component.ErrorDialog(Translation.ERROR.EMPTY_SELECT.toString());
        } catch (IOException e) {
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler, Button, that delete selected customer.
     *
     * @param event action event
     *
     * @throws InvocationTargetException scene error
     * @throws Exception error catch
     */
    @FXML
    public void onActionDeleteCustomerHandler(ActionEvent event) throws InvocationTargetException, Exception {
        try {
            CustomerDaoImpl customerImpl = new CustomerDaoImpl();

            if (customerTable.getSelectionModel().getSelectedItem() == null) {
                throw new Exception(Translation.ERROR.EMPTY_SELECT.toString());
            }

            if (Component.ConfirmationDialog(Translation.CONFIRMATION.DELETE_C.toString())) {
                if(!customerImpl.deleteCustomer(selectedCustomer.getId())){
                    throw new Exception(Translation.ERROR.APPOINTMENT_CONSTRAINT.toString());
                };
                customersList.remove(selectedCustomer);
                selectedCustomer = null;
            }

        } catch (InvocationTargetException e) {
            Component.ErrorDialog(e.getMessage());
        } catch (Exception e) {
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler, Button, that change scene and show customer form for update.
     *
     * @param event action event
     * @throws NullPointerException selection null error
     * @throws Exception error catch
     */
    @FXML
    public void onActionUpdateCustomerHandler(ActionEvent event) throws Exception, NullPointerException {
        try {
            if (customerTable.getSelectionModel().getSelectedItem() == null) {
                throw new Exception(Translation.ERROR.EMPTY_SELECT.toString());
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FxmlPath.CUSTOMER_FORM.toString()));
            loader.load();

            CustomerController CController = loader.getController();
            CController.showCustomer(customerTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Component.ErrorDialog(Translation.ERROR.EMPTY_SELECT.toString());
        } catch (Exception e) {
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler, Button, that change scene and show appointment form for create.
     *
     * @param event action event
     *
     * @throws IOException scene error
     */
    @FXML
    public void onActionAddAppointmentHandler(ActionEvent event) throws IOException {
        try {
            if (customerTable.getSelectionModel().getSelectedItem() == null) {
                throw new Exception(Translation.ERROR.EMPTY_SELECT.toString());
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FxmlPath.APPOINTMENT_FORM.toString()));
            loader.load();

            AppointmentController AController = loader.getController();
            AController.showCustomer(customerTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (Exception e) {
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler, Button, that change scene and show appointment form update.
     *
     * @param event action event
     */
    @FXML
    public void onActionUpdateAppointmentHandler(ActionEvent event) {
        try {
            if (appointmentTable.getSelectionModel().getSelectedItem() == null) {
                throw new Exception(Translation.ERROR.EMPTY_SELECT.toString());
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FxmlPath.APPOINTMENT_FORM.toString()));
            loader.load();

            AppointmentController AController = loader.getController();
            AController.showAppointment(appointmentTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Component.ErrorDialog(Translation.ERROR.EMPTY_SELECT.toString());
        } catch (Exception e) {
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler, Button, that delete appointment selected.
     *
     * @param event action event
     * @throws Exception error
     */
    @FXML
    public void onActionDeleteAppointmentHandler(ActionEvent event) throws Exception{
        try {
            AppointmentDaoImpl appointmentImpl = new AppointmentDaoImpl();

            if (appointmentTable.getSelectionModel().getSelectedItem() == null) {
                throw new Exception(Translation.ERROR.EMPTY_SELECT.toString());
            }

            if (Component.ConfirmationDialog(Translation.CONFIRMATION.DELETE_A.toString())) {
                appointmentImpl.deleteAppointment(selectedAppointment.getId());
                appointmentList.remove(selectedAppointment);

                selectedCustomer = null;
            }

        } catch (InvocationTargetException e) {
            Component.ErrorDialog(e.getMessage());
        } catch (Exception e) {
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler, radio button, that show appointment by months.
     *
     * @param event action event
     * @throws Exception error when date out of range
     */
    @FXML
    public void onActionRBMonth(ActionEvent event) throws Exception {
        try {
            nextDate.setDisable(false);
            prevDate.setDisable(false);
            labelMonth.setText(months.get(monthCount));
            labelDayOfWeek.setText("");

                appointmentMonth.clear();
                for (Appointment a : appointmentList) {
                    if(MyTime.getMonth(a.getStart()).toString().equalsIgnoreCase(labelMonth.getText())){
                        appointmentMonth.add(a);
                    }
                }
                appointmentTable.setItems(appointmentMonth);
        }catch (Exception e){
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler, Button, that show appointments by week.
     *
     * @param event action event
     */
    @FXML
    public void onActionRBWeek(ActionEvent event) {
        try {
            nextDate.setDisable(false);
            prevDate.setDisable(false);
            nextDate.setText("Next week");
            prevDate.setText("Prev week");
            labelMonth.setText("Week: ");
            labelDayOfWeek.setText(String.valueOf(weekCount + 1));

            appointmentWeek.clear();
            for (Appointment a : appointmentList) {
                if(
                       MyTime.getWeek(a.getStart()) == weekCount
                ){
                    appointmentWeek.add(a);
                }
            }
            appointmentTable.setItems(appointmentWeek);
        }catch (Exception e){
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler, Button, move to next month or week.
     *
     * @param event action event
     */
    @FXML
    public void onActionNextDate(ActionEvent event) throws IndexOutOfBoundsException {
        try {
            if (aview.getSelectedToggle() == RadioButtonMonth && monthCount < 11) {
                monthCount++;
                labelMonth.setText(months.get(monthCount));

                appointmentMonth.clear();
                for (Appointment a : appointmentList) {
                    if(MyTime.getMonth(a.getStart()).toString().equalsIgnoreCase(labelMonth.getText())){
                        appointmentMonth.add(a);
                    }
                }
                appointmentTable.setItems(appointmentMonth);
            }

            if (aview.getSelectedToggle() == RadioButtonWeek) {
                weekCount++;
                labelDayOfWeek.setText(String.valueOf(weekCount + 1));

                appointmentWeek.clear();
                for (Appointment a : appointmentList) {
                    if(
                            MyTime.getWeek(a.getStart()) == weekCount
                    ){
                        appointmentWeek.add(a);
                    }
                }
                appointmentTable.setItems(appointmentWeek);
            }

        }catch (Exception e){
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * An Action Handler, Button, move to next month or week.
     *
     * @param event action event
     */
    @FXML
    public void onActionPrevDate(ActionEvent event) throws IndexOutOfBoundsException {
        try {
            if (aview.getSelectedToggle() == RadioButtonMonth && monthCount > 0) {
                monthCount--;
                labelMonth.setText(months.get(monthCount));

                appointmentMonth.clear();
                for (Appointment a : appointmentList) {
                    if(MyTime.getMonth(a.getStart()).toString().equalsIgnoreCase(labelMonth.getText())){
                        appointmentMonth.add(a);
                    }
                }
                appointmentTable.setItems(appointmentMonth);
            }

            if (aview.getSelectedToggle() == RadioButtonWeek && weekCount > 0) {
                weekCount--;
                labelDayOfWeek.setText(String.valueOf(weekCount + 1));

                appointmentWeek.clear();
                for (Appointment a : appointmentList) {
                    if(
                            MyTime.getWeek(a.getStart()) == weekCount
                    ){
                        appointmentWeek.add(a);
                    }
                }
                appointmentTable.setItems(appointmentWeek);
            }
        }catch(IndexOutOfBoundsException e){
            Component.ErrorDialog(Translation.ERROR.END_DATE.toString());
        }
        catch (Exception e){
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * Main form initializer. Set text translation in english or french. Also listen to action on tables,
     * customers and appointment, that if any changes,
     * set the selected item to be prepare to move to another scene and tab view changes.
     *
     * @param url URL class
     * @param resourceBundle ResourceBundle Class
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CustomerDaoImpl customerImpl = new CustomerDaoImpl();
        AppointmentDaoImpl appointmentImpl = new AppointmentDaoImpl();

        addCustomerBtn.setText(Translation.MAIN.ADD_CUSTOMER.toString());
        addAppointmentBtn.setText(Translation.MAIN.ADD_APPOINTMENT.toString());
        updateCustomerBtn.setText(Translation.MAIN.UPDATE_CUSTOMER.toString());
        updateAppointmentBtn.setText(Translation.MAIN.UPDATE_APPOINTMENT.toString());
        deleteCustomerBtn.setText(Translation.MAIN.DELETE_CUSTOMER.toString());
        deleteAppointmentBtn.setText(Translation.MAIN.DELETE_APPOINTMENT.toString());
        ColCustomerName.setText(Translation.CUSTOMER.NAME.toString());
        ColCustomerAddress.setText(Translation.CUSTOMER.ADDRESS.toString());
        ColCustomerPostalCode.setText(Translation.CUSTOMER.POSTAL_CODE.toString());
        ColCustomerPhone.setText(Translation.CUSTOMER.PHONE.toString());
        ColCustomerCreateDate.setText(Translation.CUSTOMER.CREATE_DATE.toString());
        ColCustomerCreatedBy.setText(Translation.CUSTOMER.CREATE_BY.toString());
        ColCustomerLastUpdate.setText(Translation.CUSTOMER.LAST_UPDATE.toString());
        ColCustomerLastUpdatedBy.setText(Translation.CUSTOMER.LAST_UPDATE_BY.toString());
        ColCustomerDivisionId.setText(Translation.CUSTOMER.DIVISION_ID.toString());
        customerTab.setText(Translation.MAIN.CUSTOMERS.toString());

        appointmentTab.setText(Translation.MAIN.APPOINTMENTS.toString());
        ColAppointmentTitle.setText(Translation.APPOINTMENT.TITLE.toString());
        ColAppointmentDescription.setText(Translation.APPOINTMENT.DESCRIPTION.toString());
        ColAppointmentContactId.setText(Translation.APPOINTMENT.CONTACT_ID.toString());
        ColAppointmentLocation.setText(Translation.APPOINTMENT.LOCATION.toString());
        ColAppointmentType.setText(Translation.APPOINTMENT.TYPE.toString());
        ColAppointmentEnd.setText(Translation.APPOINTMENT.END.toString());
        ColAppointmentStart.setText(Translation.APPOINTMENT.START.toString());
        ColAppointmentCustomerId.setText(Translation.APPOINTMENT.CUSTOMER_ID.toString());
        ColAppointmentContactName.setText(Translation.APPOINTMENT.CONTACT_NAME.toString());

        try {
            customersList = customerImpl.getAllCustomers();
            appointmentList = appointmentImpl.getAllAppointments();
            labelDayOfWeek.setText("");
            labelMonth.setText("");



            customerTable.setItems(customersList);
            appointmentTable.setItems(appointmentList);

            ColCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
            ColCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
            ColCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            ColCustomerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            ColCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            ColCustomerCreateDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
            ColCustomerCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
            ColCustomerLastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
            ColCustomerLastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
            ColCustomerDivisionId.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
            ColCustomerDivision.setCellValueFactory(new PropertyValueFactory<>("division"));

            ColAppointmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
            ColAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            ColAppointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            ColAppointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
            ColAppointmentContactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
            ColAppointmentContactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
            ColAppointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
            ColAppointmentStart.setCellValueFactory(new PropertyValueFactory<>("start"));
            ColAppointmentEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
            ColAppointmentCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));


            /**
             * When customer table is clicked, this will enable the button so that any actions on a customer to be possible
             * */
            customerTable.setOnMouseClicked((MouseEvent event) -> {
                if (event.getButton().equals(MouseButton.PRIMARY) && mainTabs.getSelectionModel().getSelectedIndex() == 0) {
                    updateCustomerBtn.setDisable(false);
                    deleteCustomerBtn.setDisable(false);
                    addAppointmentBtn.setDisable(false);
                    selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
                }
            });

            appointmentTable.setOnMouseClicked((MouseEvent event) -> {
                if (event.getButton().equals(MouseButton.PRIMARY) && mainTabs.getSelectionModel().getSelectedIndex() == 1) {
                    updateAppointmentBtn.setDisable(false);
                    deleteAppointmentBtn.setDisable(false);
                    selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
                }
            });

            mainTabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
                @Override
                public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                    updateCustomerBtn.setDisable(true);
                    deleteCustomerBtn.setDisable(true);
                    addAppointmentBtn.setDisable(true);
                }
            });

            for(Appointment a: appointmentList){
                long t = MyTime.getTimeDifference(a.getStart());
                if(t> 0 && t <= 15){
                    upcomingAppointmentList.add(a);
                }
            }

            if(SystemInfo.isFirstStart()){
                if(upcomingAppointmentList.size() == 0 ){
                    Component.WarningDialog(Translation.APPOINTMENT.NO_APPOINTMENT.toString());
                }else{
                    String list = "";
                    for(Appointment a : upcomingAppointmentList){
                        list = list + Translation.APPOINTMENT.UPCOMING_APPOINTMENT.toString() + a.getId() + " @ " + a.getStart() + "\n";
                    }
                    Component.WarningDialog(list);
                }
                SystemInfo.setFirstStart(false);
            }
        } catch (Exception e) {
            Component.ErrorDialog(e.getMessage());
        }
    }
}
