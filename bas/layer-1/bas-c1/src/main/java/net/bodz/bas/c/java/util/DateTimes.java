package net.bodz.bas.c.java.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimes
        implements
            IDateTimeFormatConsts {

    public static ZonedDateTime parseTimestampTZ(String s) {
        return parseTimeTZ(s, 1000);
    }

    public static ZonedDateTime parseTimeTZ(String s, int scale)
            throws NumberFormatException {
        s = s.trim();

        int space = s.indexOf(' ');
        if (space == -1)
            throw new IllegalArgumentException("No time zone.");

        String w1 = s.substring(0, space);
        String w2 = s.substring(space + 1).trim();

        long millis = Long.parseLong(w1) * scale;
        Instant instant = Instant.ofEpochMilli(millis);

        ZoneId zoneId = ZoneId.of(w2);
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
        return zdt;
    }

}
