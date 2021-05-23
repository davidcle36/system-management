/**
 * MyTime Util Class
 */
package utils;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.ValueRange;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Time class. Utils related to time conversion and calculating.
 * */
public class MyTime {
    public static String pattern = "yyyy-MM-dd HH:mm:ss";
    public static String sPattern = "yyyy-MM-dd hh:mm a";

    /**
     * Get UTC Time from now
     *
     * @return String utc time
     * */
    public static String getCurrentUtcTime() {
        ZonedDateTime gmt = ZonedDateTime.of(LocalDateTime.now(),
                ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("GMT"));

        return Timestamp.valueOf(gmt.toLocalDateTime()).toString().replaceAll("\\.[^.]*", "");
    }

    public static String getEstTime(String t) {
        return "";
    }

    /**
     * Get locale Time.
     *
     * @param dt datetime
     * @return String time
     * @throws ParseException parse error
     * */
    public static String getLocaleTime(String dt) throws ParseException {

        DateFormat tf = new SimpleDateFormat(pattern);
        tf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = tf.parse(dt);

        ZonedDateTime tz = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(SystemInfo.getZoneId().toString()));

        return tz.format(DateTimeFormatter.ofPattern(sPattern));
    }

    /**
     * Get month locale Time.
     *
     * @param dt date time
     * @return Month time
     * @throws ParseException parse error
     * */
    public static Month getMonth(String dt) throws ParseException {
        DateFormat tf = new SimpleDateFormat(sPattern);
        tf.setTimeZone(TimeZone.getTimeZone(SystemInfo.getZoneId()));
        Date date = tf.parse(dt);

        ZonedDateTime tz = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(SystemInfo.getZoneId().toString()));

        Month month = tz.getMonth();

        return month;
    }


    public static DayOfWeek getDayOfWeek(String dt) throws ParseException {
        DateFormat tf = new SimpleDateFormat(sPattern);
        tf.setTimeZone(TimeZone.getTimeZone(SystemInfo.getZoneId()));
        Date date = tf.parse(dt);

        ZonedDateTime tz = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(SystemInfo.getZoneId().toString()));

        DayOfWeek dayofWeek = tz.getDayOfWeek();

        return dayofWeek;
    }

    public static int getWeek(String dt) throws ParseException {
        DateFormat tf = new SimpleDateFormat(sPattern);
        tf.setTimeZone(TimeZone.getTimeZone(SystemInfo.getZoneId()));
        Date date = tf.parse(dt);

        ZonedDateTime tz = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(SystemInfo.getZoneId().toString()));

        int weekOfYear = tz.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        return weekOfYear;
    }

    public static String localeToUTC(String dt) throws ParseException {
        DateFormat tf = new SimpleDateFormat(sPattern);
        tf.setTimeZone(TimeZone.getTimeZone(SystemInfo.getZoneId().toString()));
        Date date = tf.parse(dt);

        ZonedDateTime tz = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));

        return tz.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static boolean validateLocalWithInEst(String dt) throws ParseException {
        DateFormat tf = new SimpleDateFormat(sPattern);
        tf.setTimeZone(TimeZone.getTimeZone(SystemInfo.getZoneId()));
        Date date = tf.parse(dt);

        ZonedDateTime tz = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("America/New_York"));

        ValueRange hourRange = ValueRange.of(8, 22);
        if (hourRange.isValidValue(tz.getHour())) {
            if (!(tz.getHour() == 22 && tz.getMinute() > 0)) {
                return true;
            }
        }

        return false;
    }

    public static boolean validateTimeOverlay(String sdt, String edt) throws ParseException {
        DateFormat tf = new SimpleDateFormat(sPattern);
        tf.setTimeZone(TimeZone.getTimeZone(SystemInfo.getZoneId()));

        Date d1 = tf.parse(sdt);
        Date d2 = tf.parse(edt);

        return d1.after(d2);
    }


    /**
     * Get difference from now and date time.
     *
     * @param edt date time
     * @return String time
     * @throws ParseException parse error
     * */
    public static long getTimeDifference(String edt) throws ParseException {
        DateFormat tf = new SimpleDateFormat(sPattern);
        tf.setTimeZone(TimeZone.getTimeZone(SystemInfo.getZoneId()));
        Date d1 = tf.parse(edt);
        Date d2 = new Date();

        long diffInMili = d1.getTime() - d2.getTime();
        long diff = TimeUnit.MINUTES.convert(diffInMili, TimeUnit.MILLISECONDS);

        return (diff + -1);
    }

    public static String isAppointmentOverlapped(String sdt, String edt, int aid) throws Exception {
        String m = "";
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM appointments WHERE '" + sdt + "' BETWEEN start AND end OR '" + edt + "' BETWEEN " +
                "start AND end";
        Query.makeQuery(sqlStatement);
        ResultSet results = Query.getResult();

        while (results.next()) {
            String s = MyTime.getLocaleTime(results.getString("Start"));
            String e = MyTime.getLocaleTime(results.getString("End"));
            int id = results.getInt("Appointment_ID");

            if(aid == id){
            }else{
               return "\n\nAppointment ID: " + id + "\nStarting at \t" + s + "\nEnding at \t" + e;
            }

        }
        DBConnection.closeConnection();
        return "";
    }
}
