package net.bodz.bas.meta.build;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RcsDates {

    static final DateFormat dateFormat;
    static final DateFormat timeFormat;

    static {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        timeFormat = new SimpleDateFormat("hh:mm:ss");
    }

    /**
     * @param dateString
     *            Non-<code>null</code> date string.
     * @throws ParseException
     */
    public static Date parseDate(String dateString)
            throws ParseException {
        return dateFormat.parse(dateString);
    }

    /**
     * @param timeString
     *            Non-<code>null</code> time string.
     * @throws ParseException
     */
    public static Date parseTime(String timeString)
            throws ParseException {
        return timeFormat.parse(timeString);
    }

    public static String formatDate(long dateTime) {
        return dateFormat.format(dateTime);
    }

    public static String formatTime(long dateTime) {
        return timeFormat.format(dateTime);
    }

}
