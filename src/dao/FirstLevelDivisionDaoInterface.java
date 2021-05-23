/**
 * Division Dao interface
 * */
package dao;

import javafx.collections.ObservableList;
import model.FirstLevelDivision;

/**
 * Division interface
 * */
public interface FirstLevelDivisionDaoInterface {
    /**
     * Division interface for getting all division. Retrieve all divisions from database and all its column
     *
     * @throws Exception sql error
     * @return divisions list of appointments
     * */
    ObservableList<FirstLevelDivision> getFirstLevelDivisionsList() throws Exception;
}
