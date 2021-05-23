/**
 * Contact Dao Interface
 * */
package dao;

import javafx.collections.ObservableList;
import model.Contact;

/**
 * Interface for ContactDaoImpl to connect and retrieve data from database.
 * */
public interface ContactDaoInterface {
    /**
     * Contact interface for getting all appointment. Retrieve all contacts from database and all its column
     *
     * @throws Exception sql error
     * @return contacts list of contacts
     * */
    ObservableList<Contact> getContacts() throws Exception;
}
