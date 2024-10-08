package net.bodz.bas.c.java.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;

import net.bodz.bas.c.java.text.Dates;

public class Calendars {

    public static Calendar parseIso8601(String s)
            throws ParseException {
        Date date = Dates.ISO8601.parse(s);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        // XXX TimeZone
        return calendar;
    }

    public static String formatIso8601(Date date) {
        return formatIso8601(date.getTime());
    }

    public static String formatIso8601(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return formatIso8601(calendar);
    }

    public static String formatIso8601(Calendar calendar) {
        return Dates.ISO8601.format(calendar);
    }

    public static Calendar parseTimestampTZ(String s) {
        return parseTimeTZ(s, 1000);
    }

    public static Calendar parseTimeTZ(String s, int scale)
            throws NumberFormatException {
        s = s.trim();

        int space = s.indexOf(' ');
        if (space == -1)
            throw new IllegalArgumentException("No time zone.");

        String w1 = s.substring(0, space);
        String w2 = s.substring(space + 1).trim();

        long millis = Long.parseLong(w1) * scale;

        TimeZone timeZone = null;
        String intStr = w2.startsWith("+") ? w2.substring(1) : w2;
        int offset = Integer.parseInt(intStr);
        String[] ids = TimeZone.getAvailableIDs(offset);
        if (ids.length != 0)
            timeZone = TimeZone.getTimeZone(ids[0]);
        else
            timeZone = new SimpleTimeZone(offset, w2);

        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis(millis);

        return calendar;
    }

    public static Calendar parseXmlCalendar(String s) {
        return DatatypeConverter.parseDateTime(s);
    }

    public static String formatXmlCalendar(Calendar calendar) {
        return DatatypeConverter.printDateTime(calendar);
    }

    public static Date parseXmlDate(String s) {
        Calendar calendar = parseXmlCalendar(s);
        return calendar.getTime();
    }

    public static String formatXmlDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return formatXmlCalendar(calendar);
    }

}
