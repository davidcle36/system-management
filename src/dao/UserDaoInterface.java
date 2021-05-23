/**
 * User Dao Interface
 * */
package dao;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User Dao Interface
 * */
public interface UserDaoInterface {
    public boolean validateUser(String userName, String password) throws SQLException, Exception;
    /**
     * Interface to get user from interface. login user based on password and username and check in database if valid
     *
     * @param userName that the user entered
     * @param password that the user entered
     * @throws SQLException sql error
     * @throws Exception errors
     * */
    public void getUser(String userName, String password) throws SQLException, Exception;
}
