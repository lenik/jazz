package net.bodz.bas.c.java.time;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;

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

    public static TemporalAccessor _convert(Date date) {
        switch (TypeKind.getTypeId(date.getClass())) {
        case TypeId.SQL_DATE:
            return convert((java.sql.Date) date);
        case TypeId.SQL_TIME:
            return convert((java.sql.Time) date);
        case TypeId.TIMESTAMP:
            return convert((Timestamp) date);
        case TypeId.DATE:
        default:
            return convertDate(date);
        }
    }

    public static ZonedDateTime convertDate(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        return zdt;
    }

    public static LocalDate convert(java.sql.Date sqlDate) {
        Instant instant = Instant.ofEpochMilli(sqlDate.getTime());
        // LocalDate localDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    public static LocalTime convert(java.sql.Time sqlTime) {
        Instant instant = Instant.ofEpochMilli(sqlTime.getTime());
        // LocalTime localTime = LocalTime.ofInstant(instant, ZoneId.systemDefault());
        LocalTime localTime = instant.atZone(ZoneId.systemDefault()).toLocalTime();
        return localTime;
    }

    public static ZonedDateTime convert(java.sql.Timestamp timestamp) {
        ZonedDateTime zdt = convertDate(timestamp);
        ZonedDateTime nanoFixed = zdt.with(ChronoField.NANO_OF_SECOND, timestamp.getNanos());
        return nanoFixed;
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
