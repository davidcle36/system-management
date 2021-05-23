/**
 * Country Dao Implementation Class
 * */
package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import utils.DBConnection;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * Country Data Access Object Implementation. Class that extends the GeneralCrudDaoImpl abstract class and implements
 * with country interface. Methods use to
 * to connect to database and retrieve Country records.
 * */
public class CountryDaoImpl extends GeneralCrudDaoImpl<Country> implements CountryDaoInterface{
    ObservableList<Country> countryList = FXCollections.observableArrayList();

    /**
     * Country method for getting all country. Retrieve all contacts from database and all its column
     *
     * @throws Exception sql error
     * @return countryList is the data retrieved from database
     * */
    @Override
    public ObservableList<Country> getCountryList() throws Exception {
        countryList.clear();
        Country c = null;
        DBConnection.makeConnection();
        ResultSet results = findAll("countries");
        while (results.next()) {
            int id = results.getInt("Country_ID");
            String country = results.getString("Country");
            Timestamp createDate = results.getTimestamp("Create_Date");
            String createdBy = results.getString("Created_By");
            Timestamp lastUpdate = results.getTimestamp("Last_Update");
            String lastUpdatedBy = results.getString("Last_Updated_By");
            c = new Country(id, country, createDate, createdBy, lastUpdate, lastUpdatedBy);
            countryList.add(c);
        }
        DBConnection.closeConnection();
        return countryList;
    }
}
