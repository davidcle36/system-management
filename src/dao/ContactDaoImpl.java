/**
 * Contact Dao Implementation Class
 * */
package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import model.Country;
import utils.DBConnection;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * Contact Data Access Object Implementation. Class that extends the GeneralCrudDaoImpl abstract class and implements
 * with appointment interface. Methods use to
 * to connect to database and retrieve contact records.
 * */
public class ContactDaoImpl extends GeneralCrudDaoImpl<Contact> implements ContactDaoInterface{

    ObservableList<Contact> contactList = FXCollections.observableArrayList();

    /**
     * Contacts method for getting all appointment. Retrieve all Contacts from database and all its column
     *
     * @throws Exception sql error
     * */
    @Override
    public ObservableList<Contact> getContacts() throws Exception {
        contactList.clear();
        Contact c = null;
        DBConnection.makeConnection();
        ResultSet results = findAll("contacts");
        while (results.next()) {
            int id = results.getInt("Contact_ID");
            String name = results.getString("Contact_Name");
            String email = results.getString("Email");
            c = new Contact(id, name, email);
            contactList.add(c);
        }
        DBConnection.closeConnection();
        return contactList;
    }
}
