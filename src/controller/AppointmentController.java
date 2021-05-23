package controller;

import dao.AppointmentDaoImpl;
import dao.ContactDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lambda.TimeLocal;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import utils.*;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * Appointment Controller Class for adding, deleting, and updating.
 * */
public class AppointmentController implements Initializable {
    Parent scene;
    Stage stage;
    Appointment selectedAppointment;
    ObservableList<Contact> contactList = FXCollections.observableArrayList();
    private boolean isUpdate = false;

    @FXML
    private Label appointmentHeader;

    @FXML
    private Label labelAppointmentId;

    @FXML
    private Label labelAppointmentTitle;

    @FXML
    private Label labelAppointmentDescription;

    @FXML
    private Label labelAppointmentLocation;

    @FXML
    private Label labelAppointmentStart;

    @FXML
    private Label labelAppointmentEnd;

    @FXML
    private Label labelAppointmentContact;

    @FXML
    private TextField TextAppointmentId;

    @FXML
    private TextField TextAppointmentTitle;

    @FXML
    private TextField TextAppointmentType;

    @FXML
    private TextField TextAppointmentCustomerId;

    @FXML
    private TextArea TextAppointmentDescription;

    @FXML
    private TextField TextAppointmentLocation;

    @FXML
    private DatePicker dateStart;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private TextField timeStart;

    @FXML
    private TextField timeEnd;

    @FXML
    private Label labelAppointmentType;

    @FXML
    private Label labelAppointmentCustomerId;

    @FXML
    private Button appointmentSaveBtn;

    @FXML
    private Button appointmentCancelBtn;

    @FXML
    private ComboBox<Contact> CBContact;

    /**
     * discussion of lambda. A function that takes in string, dt, and parsed it, and return
     * a time that will be display locally.
     *
     * @see lambda.TimeLocal
     * */
    public TimeLocal timeLocal = (dt) -> {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        String sPattern = "yyyy-MM-dd hh:mm a";
        DateFormat tf = new SimpleDateFormat(sPattern);
        tf.setTimeZone(TimeZone.getTimeZone(SystemInfo.getZoneId().toString()));
        Date date = tf.parse(dt);

        ZonedDateTime tz = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));

        return tz.format(DateTimeFormatter.ofPattern(pattern));
    };

    /**
     * An Action Handler that change scene to Main Form.
     * Using a constant enum, {@link FxmlPath#MAIN_FORM} the point of change is
     * limited when updated, thus, limiting bugs when changes to fxml path file
     * occurs.
     *
     * @param event action event
     * @throws IOException if file is not found, throws an IOException. Since it
     * is part of the code base, User will not notice this error unless they
     * intentionally rename the file or delete it. If error does occur, an error
     * dialog will pop up
     */
    @FXML
    public void onActionAppointmentCancel(ActionEvent event) throws IOException {
        SceneHelper.ChangeSceneOnButton(stage, event, scene, FxmlPath.MAIN_FORM.toString());
    }

    /**
     * An Action Handler, Button, that save newly added part. When saved, the
     * helper function, ChangeSceneOnButton will switch back to main form.
     *
     * @param event action event
     * @throws Exception Trigger Dialog box when part is not found,
     * @throws NumberFormatException if data is invalid according the args of
     * that object, throw error.
     *
     * @see lambda.TimeLocal
     */
    @FXML
    public void onActionAppointmentSave(ActionEvent event) throws Exception, NullPointerException, NumberFormatException {


        try{
            Appointment appointment = null;
            String startDate = dateStart.getValue().toString();
            String endDate = dateEnd.getValue().toString();
            String startDatetime = startDate + " " + timeStart.getText().trim();
            String endDateTime = endDate + " " + timeEnd.getText().trim();
            Contact contact = CBContact.getSelectionModel().getSelectedItem();
            int id = !isUpdate ? -1 : Integer.parseInt(TextAppointmentId.getText());



            if(!MyTime.validateLocalWithInEst(startDatetime) || !MyTime.validateLocalWithInEst(endDateTime)){
                throw new Exception(Translation.ERROR.WITHIN_RANGE_TIME.toString());
            }
            if(MyTime.validateTimeOverlay(startDatetime, endDateTime)){
                throw new Exception(Translation.ERROR.TIME_OVERLAY.toString());
            }

            /**
             * discussion of lambda. passed a utc string time and returns a locale time to be display
             * in table view.
             * */
            String startUtc = timeLocal.localeTime(startDatetime);
            String endUtc = timeLocal.localeTime(endDateTime);

            String overlappedTime = MyTime.isAppointmentOverlapped(startUtc, endUtc, id);

            if(!overlappedTime.equals("")){
                throw new Exception(Translation.ERROR.OVERLAPPED.toString() + " " + overlappedTime);
            }

            AppointmentDaoImpl appointmentDao = new AppointmentDaoImpl();
            appointment = new Appointment(
                    id,
                    TextAppointmentTitle.getText(),
                    TextAppointmentDescription.getText(),
                    TextAppointmentLocation.getText(),
                    TextAppointmentType.getText(),
                    startUtc,
                    endUtc,
                    MyTime.getCurrentUtcTime(),
                    SystemInfo.getUser(),
                    MyTime.getCurrentUtcTime(),
                    SystemInfo.getUser(),
                    Integer.parseInt(TextAppointmentCustomerId.getText()),
                    User.getUserId(),
                    contact.getId()
                    );

            if(!isUpdate){
                appointmentDao.addAppointment(appointment);
            }else{
                appointmentDao.updateAppointment(appointment);
            }


            SceneHelper.ChangeSceneOnButton(stage, event, scene, FxmlPath.MAIN_FORM.toString());
        }catch(NumberFormatException e) {
            Component.ErrorDialog(e.getMessage());
        }
        catch(NullPointerException e){
            Component.ErrorDialog(Translation.ERROR.MISSING_FIELD.toString());
        } catch(Exception e){
            Component.ErrorDialog(e.getMessage());
            System.out.println(e);
        }

    }

    /**
     * A method to show select appointment that was selected in the main form.
     *
     * @param appointment is the instance to be updated
     *
     */
    public void showAppointment(Appointment appointment) {
        isUpdate = true;
        selectedAppointment = appointment;
        String[] startDateTime = appointment.getStart().split("\\s+");
        String[] endDateTime = appointment.getEnd().split("\\s+");

        TextAppointmentId.setText(String.valueOf(appointment.getId()));
        TextAppointmentCustomerId.setText(String.valueOf(appointment.getCustomerId()));
        TextAppointmentTitle.setText(String.valueOf(appointment.getTitle()));
        TextAppointmentDescription.setText(String.valueOf(appointment.getDescription()));
        TextAppointmentType.setText(String.valueOf(appointment.getType()));
        TextAppointmentCustomerId.setText(String.valueOf(appointment.getCustomerId()));
        TextAppointmentLocation.setText(String.valueOf(appointment.getLocation()));
        dateStart.setValue(LocalDate.parse(startDateTime[0]));
        dateEnd.setValue(LocalDate.parse(endDateTime[0]));
        timeStart.setText(startDateTime[1] + " " + startDateTime[2]);
        timeEnd.setText(endDateTime[1] + " " + endDateTime[2]);

        for(Contact c : contactList){
            if(appointment.getContactId() == c.getId()){
                CBContact.getSelectionModel().select(c);
                return;
            }
        }

        appointmentHeader.setText(Translation.MAIN.UPDATE_APPOINTMENT.toString());
    }

    /**
     * A method to show select appointment that was selected in the main form.
     *
     * @param customer is the instance needed to set the customer id to appointment
     *
     */
    public void showCustomer(Customer customer){
        TextAppointmentCustomerId.setText(String.valueOf(customer.getId()));
    }

    /**
     * Appointment Initialize method. Set text to english or french. Pre set contact list from database
     *
     * @param url URL
     * @param resourceBundle ResourceBundle
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContactDaoImpl contactImpl = new ContactDaoImpl();

        if(isUpdate){
            appointmentHeader.setText(Translation.MAIN.APPOINTMENTS.toString());
        }else{
            appointmentHeader.setText(Translation.MAIN.APPOINTMENTS.toString());
        }

        labelAppointmentTitle.setText(Translation.APPOINTMENT.TITLE.toString());
        labelAppointmentEnd.setText(Translation.APPOINTMENT.END.toString());
        labelAppointmentDescription.setText(Translation.APPOINTMENT.DESCRIPTION.toString());
        labelAppointmentLocation.setText(Translation.APPOINTMENT.LOCATION.toString());
        labelAppointmentStart.setText(Translation.APPOINTMENT.START.toString());
        labelAppointmentType.setText(Translation.APPOINTMENT.TYPE.toString());
        labelAppointmentCustomerId.setText(Translation.APPOINTMENT.CUSTOMER_ID.toString());
        labelAppointmentId.setText(Translation.APPOINTMENT.APPOINTMENT_ID.toString());
        appointmentSaveBtn.setText(Translation.COMMON.SAVE.toString());
        appointmentCancelBtn.setText(Translation.COMMON.CANCEL.toString());
        CBContact.setPromptText(Translation.APPOINTMENT.SELECT_CONTACT.toString());

        try {
            contactList = contactImpl.getContacts();
            CBContact.setItems(contactList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
