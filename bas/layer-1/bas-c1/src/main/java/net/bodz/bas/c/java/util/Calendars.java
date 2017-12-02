package net.bodz.bas.c.java.util;

import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class Calendars {

    public static Calendar parseIso8601(String s) {
        return javax.xml.bind.DatatypeConverter.parseDateTime(s);
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
        return javax.xml.bind.DatatypeConverter.printDateTime(calendar);
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

}
