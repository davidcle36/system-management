/**
 * Basic System info
 * */
package utils;

import java.time.ZoneId;
import java.util.Locale;

/**
 * Basic System info
 * */
public class SystemInfo {
    private static String language;
    private static String location;
    private static String user;
    private static String userId;
    private static ZoneId zoneId;
    private static boolean firstStart;

    public SystemInfo() {
        this.language = Locale.getDefault().getLanguage();
        this.location = ZoneId.systemDefault().toString();
        this.zoneId = ZoneId.systemDefault();
        this.firstStart = true;
    }

    public static boolean isFirstStart() {
        return firstStart;
    }

    public static void setFirstStart(boolean firstStart) {
        SystemInfo.firstStart = firstStart;
    }

    public static ZoneId getZoneId() {
        return zoneId;
    }

    public static String getLanguage() {
        return language;
    }

    public static String getLocation() {
        return location;
    }

    public static String getUser(){ return user;}

    public static void setUser(String u){
        user = u;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        SystemInfo.userId = userId;
    }
}
