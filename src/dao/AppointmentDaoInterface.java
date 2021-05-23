/**
 * Appointment Dao Interface
 * */
package dao;

import javafx.collections.ObservableList;
import model.Appointment;

/**
 * Interface for AppointmentDao to connect and retrieve data from database.
 * */
public interface AppointmentDaoInterface {
    /**
     * Appointment interface for updating. Send updated values to database using GeneralCrudDaoImpl method,
     * update
     *
     * @param appointment to be updated
     * @throws Exception sql error
     * */
    void updateAppointment(Appointment appointment) throws Exception;

    /**
     * Appointment interface for deleting. Delete appointment base on id
     *
     * @param id of appointment to be deleted
     * @throws Exception sql error
     * */
    void deleteAppointment(int id) throws Exception;
    void getAppointment();

    /**
     * Appointment interface for adding new. Add appointment from instance
     *
     * @param appointment of appointment to be deleted
     * @throws Exception sql error
     * */
    void addAppointment(Appointment appointment) throws Exception;


    /**
     * Appointment method for getting all appointment. Retrieve all appointments from database and all its column
     *
     * @throws Exception sql error
     * @return appointmentList list of appointments
     * */
    ObservableList<Appointment> getAllAppointments() throws Exception;
}
