/**
 * Customer Dao Implementation Class
 * */
package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import utils.DBConnection;
import utils.MyTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * Customer Data Access Object Implementation. Class that extends the GeneralCrudDaoImpl abstract class and implements
 * with Customer interface. Methods use to
 * to connect to database and retrieve Customer records.
 * */
public class CustomerDaoImpl extends GeneralCrudDaoImpl<Customer> implements CustomerDaoInterface {
    private final String tableName = "customers";
    private final ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private String tableInsert = " (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, " +
            "Last_Updated_By, Division_ID) ";

    /**
     * Customer interface for adding new. Add customer from instance
     *
     * @param customerName name
     * @param customerAddress address
     * @param customerPostalCode postal code
     * @param customerPhoneNumber phone
     * @param createdBy created by user
     * @param divisionId division id
     * @throws Exception sql error
     * */
    @Override
    public void addCustomer(String customerName, String customerAddress, String customerPostalCode,
                            String customerPhoneNumber, String createdBy, int divisionId) throws Exception {
        String customerSqlValuesPlaceHolder = "''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'', ''{6}'', ''{7}'', " +
                "{8}";
        DBConnection.makeConnection();
        int id = count(tableName) + 1;
        String timestamp = MyTime.getCurrentUtcTime();

        String formattedValues = MessageFormat.format(customerSqlValuesPlaceHolder,
                customerName.replaceAll("\\s+$", ""), customerAddress.replaceAll("\\s+$", ""),
                customerPostalCode.replaceAll("\\s+$", ""), customerPhoneNumber.replaceAll("\\s+$", ""), timestamp,
                createdBy, timestamp, createdBy, divisionId);

        create(tableName + tableInsert, formattedValues);
        DBConnection.closeConnection();
    }

    /**
     * Customer method for updating. Send updated values to database using GeneralCrudDaoImpl method, update
     *
     * @param customer to be updated
     * @throws Exception sql error
     * */
    @Override
    public void updateCustomer(Customer customer) throws Exception {
        int id = customer.getId();
        String name = customer.getName().replaceAll("\\s+$", "");
        ;
        String postalCode = customer.getPostalCode().replaceAll("\\s+$", "");
        ;
        String address = customer.getAddress().replaceAll("\\s+$", "");
        ;
        String phoneNumber = customer.getPhone().replaceAll("\\s+$", "");
        ;
        int divisionId = customer.getDivisionId();
        String timestamp = MyTime.getCurrentUtcTime();
        String lastUpdatedBy = customer.getLastUpdatedBy();

        String customerUpdateSqlValuesPlaceHolder = "{0}, {1}, {2}, {3}, {4}, {5}, {6}";
        String formattedValues = MessageFormat.format(customerUpdateSqlValuesPlaceHolder, "Customer_Name = '" + name + "'",
                "Postal_Code = '" + postalCode + "'", "Address =  '" + address + "'", "Phone =  '" + phoneNumber + "'",
                "Division_ID = " + divisionId, "Last_Update = '" + timestamp + "'",
                "Last_Updated_By =  '" + lastUpdatedBy + "'");

        DBConnection.makeConnection();
        update(tableName, formattedValues, "Customer_ID", Integer.toString(id));
        DBConnection.closeConnection();

    }

    /**
     * Customer method for deleting. Delete customer base on id
     *
     * @param id of appointment to be deleted
     * @return boolean confirm if deleted
     * @throws Exception sql error
     * */
    @Override
    public boolean deleteCustomer(int id) throws SQLException, Exception {
            boolean isDeleted;
            DBConnection.makeConnection();
            isDeleted = delete(tableName, "Customer_ID", Integer.toString(id));
            DBConnection.closeConnection();
            return isDeleted;
    }

    /**
     * Customer method for getting a customer. Delete customer base on id
     *
     * @param customerId of appointment to be deleted
     * @return customer get single customer result
     * @throws Exception sql error
     * */
    @Override
    public Customer getCustomer(String customerId) throws Exception {
        Customer customer = null;
        DBConnection.makeConnection();
        ResultSet result = find(tableName, "Customer_ID", customerId);
        while (result.next()) {
            int id = result.getInt("Customer_ID");
            String name = result.getString("Customer_Name");
            String address = result.getString("Address");
            String postalCode = result.getString("Postal_Code");
            String phone = result.getString("Phone");
            String createDate = result.getTimestamp("Create_Date").toString();
            String createdBy = result.getString("Created_By");
            String lastUpdate = result.getTimestamp("Last_Update").toString();
            String lastUpdateBy = result.getString("Last_Updated_By");
            int divisionId = result.getInt("Division_ID");
            customer = new Customer(id, name, address, postalCode, phone, createDate, createdBy, lastUpdate,
                    lastUpdateBy, divisionId);
        }
        DBConnection.closeConnection();
        return customer;
    }

    /**
     * Customer method for getting all appointment. Retrieve all Customer from database and all its column
     *
     * @return customers list
     * @throws Exception sql error
     * */
    @Override
    public ObservableList<Customer> getAllCustomers() throws Exception {
        Customer customer;
        allCustomers.clear();
        DBConnection.makeConnection();
        ResultSet results = findAllOnInner(tableName, "first_level_divisions", "Division_ID");
        while (results.next()) {
            int id = results.getInt("Customer_ID");
            String name = results.getString("Customer_Name");
            String address = results.getString("Address");
            String postalCode = results.getString("Postal_Code");
            String phone = results.getString("Phone");
            String createDate = MyTime.getLocaleTime(results.getString("Create_Date"));
            String createdBy = results.getString("Created_By");
            String lastUpdate = MyTime.getLocaleTime(results.getString("Last_Update"));
            String lastUpdateBy = results.getString("Last_Updated_By");
            int divisionId = results.getInt("Division_ID");
            String division = results.getString("Division");
            customer = new Customer(id, name, address, postalCode, phone, createDate, createdBy, lastUpdate,
                    lastUpdateBy, divisionId);
            customer.setDivision(division);
            allCustomers.add(customer);
        }
        DBConnection.closeConnection();
        return allCustomers;
    }
}
