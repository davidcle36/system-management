package lambda;

import java.text.ParseException;

/**
 * Lambda function for adjusting time, UTC, to locale time.
 * */
@FunctionalInterface
public interface TimeLocal {
    /**
     * discussion of lambda. Passed a string time, UTC, to this function, and return a locale time in string.
     * It is utilize in the Appointment, onActionAppointmentSave
     *
     * @param dt datetime string
     * @return String is the local time
     * @throws ParseException unmatch time pattern
     */
    String localeTime(String dt) throws ParseException;
}
