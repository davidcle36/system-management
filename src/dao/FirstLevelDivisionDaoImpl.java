package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.FirstLevelDivision;
import utils.DBConnection;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * Division Data Access Object Implementation. Class that extends the GeneralCrudDaoImpl abstract class and implements
 * with Division interface. Methods use to
 * to connect to database and retrieve Division records.
 * */
public class FirstLevelDivisionDaoImpl extends GeneralCrudDaoImpl<FirstLevelDivision> implements FirstLevelDivisionDaoInterface {
    ObservableList<FirstLevelDivision> firstLevelDivisionList = FXCollections.observableArrayList();

    /**
     * Division interface for getting all division. Retrieve all divisions from database and all its column
     *
     * @throws Exception sql error
     * @return divisions list of appointments
     * */
    @Override
    public ObservableList<FirstLevelDivision> getFirstLevelDivisionsList() throws Exception {
        firstLevelDivisionList.clear();
        FirstLevelDivision f = null;
        DBConnection.makeConnection();
        ResultSet results = findAll("first_level_divisions");
        while (results.next()) {
            int id = results.getInt("Division_ID");
            String division = results.getString("Division");
            Timestamp createDate = results.getTimestamp("Create_Date");
            String createdBy = results.getString("Created_By");
            Timestamp lastUpdate = results.getTimestamp("Last_Update");
            String lastUpdatedBy = results.getString("Last_Updated_By");
            int countryId = results.getInt("Country_ID");
            f = new FirstLevelDivision(id, division, createDate, createdBy, lastUpdate, lastUpdatedBy, countryId);
            firstLevelDivisionList.add(f);
        }
        DBConnection.closeConnection();
        return firstLevelDivisionList;
    }
}
