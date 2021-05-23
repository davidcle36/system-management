/**
 * Country Dao Interface
 * */
package dao;

import javafx.collections.ObservableList;
import model.Country;

/**
 * Country Data Access Object Interface. Class that extends the GeneralCrudDaoImpl abstract class and implements
 * with country interface. Methods use to
 * to connect to database and retrieve Country records.
 * */
public interface CountryDaoInterface {

    /**
     * Country interface for getting all country. Retrieve all contacts from database and all its column
     *
     * @throws Exception sql error
     * @return countryList is the data retrieved from database
     * */
    ObservableList<Country> getCountryList() throws Exception;
}
