package net.bodz.bas.types.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Dates {

    public static DateFormat DATE;
    public static DateFormat TIME;
    public static DateFormat DATETIME;

    static {
        DATE = DateFormat.getDateInstance();
        TIME = DateFormat.getTimeInstance();
        DATETIME = DateFormat.getDateTimeInstance();
    }

    public static String formatDate(Object date) {
        return DATE.format(date);
    }

    public static String formatTime(Object date) {
        return TIME.format(date);
    }

    public static String formatDateTime(Object date) {
        return DATETIME.format(date);
    }

    public static Date parseDate(String text) throws ParseException {
        return DATE.parse(text);
    }

    public static Date parseDate(String text, Date defl) {
        try {
            return DATE.parse(text);
        } catch (ParseException e) {
            return defl;
        }
    }

    public static Date parseTime(String text) throws ParseException {
        return TIME.parse(text);
    }

    public static Date parseTime(String text, Date defl) {
        try {
            return TIME.parse(text);
        } catch (ParseException e) {
            return defl;
        }
    }

    public static Date parseDateTime(String text) throws ParseException {
        return DATETIME.parse(text);
    }

    public static Date parseDateTime(String text, Date defl) {
        try {
            return DATETIME.parse(text);
        } catch (ParseException e) {
            return defl;
        }
    }

}
