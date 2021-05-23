/**
 * Database Connection Class
 * */
package utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Database Connection Class. Make and close connection based on values.
 * */
public class DBConnection {
    private static final String databaseName="WJ08aRQ";
    private static final String DB_URL="jdbc:mysql://wgudb.ucertify.com:3306/"+databaseName;
    private static final String username="U08aRQ";
    private static final String password="53689236266";
    private static final String driver="com.mysql.cj.jdbc.Driver";
    static Connection conn;

    /**
     * Connect to database
     *
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if error from database
     * @throws Exception error
     * */
    public static void makeConnection()throws ClassNotFoundException, SQLException, Exception
    {
        Class.forName(driver);
        conn=(Connection) DriverManager.getConnection(DB_URL,username,password);
        System.out.println("Connection Successful");
    }

    /**
     * closed connecting of database
     *
     * @throws ClassNotFoundException if class not found
     * @throws SQLException if error from database
     * @throws Exception error
     * */
    public static void closeConnection()throws ClassNotFoundException, SQLException, Exception{
        conn.close();
        System.out.println("Connection Closed");
    }
}
