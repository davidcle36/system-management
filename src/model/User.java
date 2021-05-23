/**
 * User Model Class
 */
package model;

/**
 * User Model Class
 */
public class User {
    private static int userId;
    private static String userName;
    private static String password;
    private static String createDate;
    private static String createdBy;
    private static String lastUpdate;
    private static String lastUpdatedBy;

    /**
     * get UserId
     */
    public static int getUserId() {
        return userId;
    }

    /**
     * set UserId
     */
    public static void setUserId(int userId) {
        User.userId = userId;
    }

    /**
     * get UserName
     */
    public static String getUserName() {
        return userName;
    }

    /**
     * set UserName
     */
    public static void setUserName(String userName) {
        User.userName = userName;
    }

    /**
     * get Password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * set Password
     */
    public static void setPassword(String password) {
        User.password = password;
    }
}
