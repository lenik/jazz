package net.bodz.bas.c.java.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

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

    public static Instant convert(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return instant;
    }

    public static LocalDate convert(java.sql.Date sqlDate) {
        Instant instant = Instant.ofEpochMilli(sqlDate.getTime());
        LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        return localDate;
    }

    public static LocalTime convert(java.sql.Time sqlTime) {
        Instant instant = Instant.ofEpochMilli(sqlTime.getTime());
        LocalTime localTime = LocalTime.ofInstant(instant, ZoneId.systemDefault());
        return localTime;
    }

    public static Instant convert(java.sql.Timestamp timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        return instant;
    }

    public static ZonedDateTime convert(Calendar calendar) {
        Instant instant = calendar.toInstant();
        ZoneId zoneId = calendar.getTimeZone().toZoneId();
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
        return zdt;
    }

    public static ZonedDateTime startOfYear() {
        int year = LocalDate.now().getYear();
        return startOfYear(year);
    }

    public static ZonedDateTime startOfYear(int year) {
        LocalDate start = LocalDate.of(year, 1, 1);
        ZonedDateTime startOfYear = start.atStartOfDay(ZoneId.systemDefault());
        return startOfYear;
    }

}
