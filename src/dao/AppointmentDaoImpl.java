/**
 * Appointment Data Access Object Implementation.
 * */
package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.User;
import utils.Component;
import utils.DBConnection;
import utils.SystemInfo;
import utils.MyTime;

import java.sql.ResultSet;
import java.text.MessageFormat;

/**
 * Appointment Data Access Object Implementation. Class that extends the GeneralCrudDaoImpl abstract class and implements
 * with appointment interface. Methods use to
 * to connect to database and retrieve appointment records.
 * */
public class AppointmentDaoImpl extends GeneralCrudDaoImpl<Appointment> implements AppointmentDaoInterface {
    private String tableName = "appointments";
    private String tableInsert = " (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, " +
            "Last_Updated_By, Customer_ID, User_ID, Contact_ID) ";
    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    /**
     * Appointment method for updating. Send updated values to database using GeneralCrudDaoImpl method, update
     *
     * @param appointment to be updated
     * @throws Exception sql error
     * */
    @Override
    public void updateAppointment(Appointment appointment) throws Exception {
        int id = appointment.getId();
        String title = appointment.getTitle();
        String type = appointment.getType();
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        int contactId = appointment.getContactId();
        String start = appointment.getStart();
        String end = appointment.getEnd();
        String timestamp = MyTime.getCurrentUtcTime();
        int customerId = appointment.getCustomerId();

        String customerUpdateSqlValuesPlaceHolder = "{0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}";
        String formattedValues = MessageFormat.format(customerUpdateSqlValuesPlaceHolder,
                "title = '" + title + "'",
                "description =  '" + description + "'",
                "location =  '" + location + "'",
                "contact_id = " + contactId,
                "type = '" + type + "'",
                "start = '" + start + "'",
                "end = '" + end + "'",
                "User_ID = " + User.getUserId(),
                "Last_Update = '" + timestamp + "'",
                "Last_Updated_By =  '" + User.getUserName() + "'",
                "Customer_ID = " + customerId);

        DBConnection.makeConnection();
        update(tableName, formattedValues, "Appointment_ID", String.valueOf(id));
        DBConnection.closeConnection();
    }

    /**
     * Appointment method for deleting. Delete appointment base on id
     *
     * @param id of appointment to be deleted
     * @throws Exception sql error
     * */
    @Override
    public void deleteAppointment(int id) throws Exception {
        DBConnection.makeConnection();
        delete(tableName, "Appointment_ID", Integer.toString(id));
        DBConnection.closeConnection();
    }

    @Override
    public void getAppointment() {

    }

    /**
     * Appointment method for adding new. Add appointment from instance
     *
     * @param appointment of appointment to be deleted
     * @throws Exception sql error
     * */
    @Override
    public void addAppointment(Appointment appointment) throws Exception {
        try {
            String appointmentSqlValuesPlaceHolder = "''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'', ''{6}'', ''{7}'', " +
                    "''{8}'', ''{9}'', {10}, {11}, {12}";
            DBConnection.makeConnection();
            int id = count(tableName) + 1;
            String timestamp = MyTime.getCurrentUtcTime();

            String formattedValues = MessageFormat.format(appointmentSqlValuesPlaceHolder,
                    appointment.getTitle().trim(),
                    appointment.getDescription().trim(),
                    appointment.getLocation().trim(),
                    appointment.getType().trim(),
                    appointment.getStart().trim(),
                    appointment.getEnd().trim(),
                    timestamp,
                    SystemInfo.getUser(),
                    timestamp,
                    SystemInfo.getUser(),
                    appointment.getCustomerId(),
                    appointment.getUserId(),
                    appointment.getContactId());
            create(tableName + tableInsert, formattedValues);
            DBConnection.closeConnection();
        }catch (Exception e){
            Component.ErrorDialog(e.getMessage());
        }
    }

    /**
     * Appointment method for getting all appointment. Retrieve all appointments from database and all its column
     *
     * @throws Exception sql error
     * */
    @Override
    public ObservableList<Appointment> getAllAppointments() throws Exception {
        Appointment appointment;
        DBConnection.makeConnection();
        ResultSet results = findAllOnInner(tableName, "contacts", "Contact_ID");
        while (results.next()) {
            int id = results.getInt("Appointment_ID");
            String title = results.getString("Title");
            String description = results.getString("Description");
            String location = results.getString("Location");
            String type = results.getString("Type");
            String start = MyTime.getLocaleTime(results.getString("Start"));
            String end = MyTime.getLocaleTime(results.getString("End"));
            String createDate = MyTime.getLocaleTime(results.getString("Create_Date"));
            String createdBy = results.getString("Created_By");
            String lastUpdate = MyTime.getLocaleTime(results.getString("Last_Update"));
            String lastUpdateBy = results.getString("Last_Updated_By");
            int customerId = results.getInt("Customer_ID");
            int userId = results.getInt("USER_ID");
            int contactId = results.getInt("CONTACT_ID");
            String contact = results.getString("Contact_Name");

            appointment = new Appointment(id, title, description, location, type, start, end, createDate, createdBy,
                    lastUpdate,
                    lastUpdateBy, customerId, userId, contactId);

            appointment.setContactName(contact);

            appointmentList.add(appointment);
        }
        DBConnection.closeConnection();
        return appointmentList;
    }
}
