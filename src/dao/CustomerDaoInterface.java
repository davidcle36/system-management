/**
 * Customer Dao Interface
 * */
package dao;

import javafx.collections.ObservableList;
import model.Customer;

/**
 * Customer Dao Interface for CRUD actions
 * */
public interface CustomerDaoInterface {
    /**
     * Customer interface for adding new. Add customer from instance
     *
     * @param customerName name
     * @param customerAddress address
     * @param customerPostalCode postal code
     * @param phoneNumber phone
     * @param createdBy created by user
     * @param divisionId division id
     * @throws Exception sql error
     * */
    void addCustomer(String customerName, String customerAddress,
                     String customerPostalCode, String phoneNumber,
                     String createdBy, int divisionId) throws Exception;
    /**
     * Customer method for updating. Send updated values to database using GeneralCrudDaoImpl method, update
     *
     * @param customer to be updated
     * @throws Exception sql error
     * */
    void updateCustomer(Customer customer) throws Exception;

    /**
     * Customer interface for deleting. Delete customer base on id
     *
     * @param id of appointment to be deleted
     * @return boolean to confirm deleted
     * @throws Exception sql error
     * */
    boolean deleteCustomer(int id) throws Exception;

    /**
     * Customer interface for getting a customer. Delete customer base on id
     *
     * @param customerId of appointment to be deleted
     * @return customer single result
     * @throws Exception sql error
     * */
    Customer getCustomer(String customerId) throws Exception;
    /**
     * Customer method for getting all appointment. Retrieve all Customer from database and all its column
     *
     * @return customers list
     * @throws Exception sql error
     * */
    ObservableList<Customer> getAllCustomers() throws Exception;
}
