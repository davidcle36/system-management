/**
 * User dao implementation Class
 * */
package dao;

import model.User;
import utils.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User Data Access Object Implementation. Class that extends the GeneralCrudDaoImpl abstract class and implements
 * with user interface. Methods use to
 * to connect to database and retrieve user records.
 * */
public class UserDaoImpl extends GeneralCrudDaoImpl<User> implements UserDaoInterface{

    /**
     * Method to get user. login user based on password and username and check in database if valid
     *
     * @param userName that the user entered
     * @param password that the user entered
     * */
    @Override
    public boolean validateUser(String userName, String password) throws SQLException, Exception {
        boolean isValid = false;
        DBConnection.makeConnection();
        ResultSet result = find("users", "User_Name", userName);
        while(result.next()){
            String uname = result.getString("User_Name");
            String pwrd = result.getString("Password");

            if(uname.equalsIgnoreCase(userName) && pwrd.equals(password)){
                isValid = true;
            }
        }
        DBConnection.closeConnection();
       return isValid;
    }

    @Override
    public void getUser(String userName, String password) throws SQLException, Exception {
        boolean isValid = false;
        User user = null;
        DBConnection.makeConnection();
        ResultSet result = find("users", "User_Name", userName);
        while(result.next()){
            String uname = result.getString("User_Name");
            String pwrd = result.getString("Password");
            int id = Integer.parseInt(result.getString("USER_ID"));
            if(uname.equalsIgnoreCase(userName) && pwrd.equals(password)){
                User.setUserId(id);
                User.setUserName(uname);
            }
        }
        DBConnection.closeConnection();
    }
}
