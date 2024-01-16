package net.bodz.bas.db.sql.dialect;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.db.sql.SQLLangs;

public abstract class AbstractSqlDialect
        implements
            ISqlDialect {

    protected String nullLiteral = "null";

    @Override
    public String getStatementTerminator() {
        return ";";
    }

    @Override
    public String getNullLiteral() {
        return nullLiteral;
    }

    @Override
    public boolean isReservedKeyword(String token) {
        if (token == null)
            return false;
        token = token.toLowerCase();
        return SQLLangs.SQL.isReservedKeyword(token);
    }

    @Override
    public String qName(String name) {
        return StringQuote.qq(name);
    }

    @Override
    public String qNameSmart(String name) {
        if (isReservedKeyword(name))
            return qName(name);
        else
            return name;
    }

    @Override
    public String qNames(String composite) {
        return qNames(composite, false);
    }

    @Override
    public String qNamesSmart(String composite) {
        return qNames(composite, true);
    }

    String qNames(String composite, boolean smart) {
        return null;
    }

    static final String doubleSingleQuote = //
            StringQuote.singleQuote + StringQuote.singleQuote;

    @Override
    public String qString(String s) {
        if (s == null)
            return nullLiteral;
        s = s.replace(StringQuote.singleQuote, doubleSingleQuote);
        return StringQuote.q(s);
    }

    @Override
    public String toDate(String quotedDateStr, String sqlDateFormat) {
        return "to_date(" + quotedDateStr + ", " + sqlDateFormat + ")";
    }

    protected String qRawDate(String rawDateLiteral) {
        if (rawDateLiteral == null)
            return nullLiteral;
        else
            return StringQuote.q(rawDateLiteral);
    }

    /**
     * @see DateTimeFormatter
     */
    @Override
    public String sqlDateFormat(String javaDateTimeFormat) {
        return javaDateTimeFormat;
    }

    DateTimeSpec dateTimeSpec(String javaDateTimeFormat) {
        String sqlDateFormat = sqlDateFormat(javaDateTimeFormat);
        return new DateTimeSpec(javaDateTimeFormat, sqlDateFormat);
    }

    DateTimeSpec dateSpec = dateTimeSpec("yyyy-MM-dd");
    DateTimeSpec timeSpec = dateTimeSpec("HH:mm:ss");

    DateTimeSpec dateTimeSpec = dateTimeSpec("yyyy-MM-dd HH:mm:ss.SSS");
    DateTimeSpec dateTimeWithTimeZoneSpec = dateTimeSpec("yyyy-MM-dd HH:mm:ss.SSS zzz");

    @Override
    public final String qDate(Date date) {
        DateTimeSpec spec = dateTimeSpec;
        Instant instant = date.toInstant();
        String str = spec.format(instant);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qDate(java.sql.Date date) {
        DateTimeSpec spec = dateSpec;
        Instant instant = date.toInstant();
        String str = spec.format(instant);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qTime(Time time) {
        LocalTime localTime = time.toLocalTime();
        return qLocalTime(localTime);
    }

    @Override
    public String qLocalDate(LocalDate localDate) {
        DateTimeSpec spec = dateSpec;
        String str = spec.format(localDate);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qLocalTime(LocalTime localTime) {
        DateTimeSpec spec = timeSpec;
        String str = spec.format(localTime);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qLocalDateTime(LocalDateTime localDateTime) {
        DateTimeSpec spec = dateTimeSpec;
        String str = spec.format(localDateTime);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qZonedDateTime(ZonedDateTime zonedDateTime) {
        DateTimeSpec spec = dateTimeWithTimeZoneSpec;
        String str = spec.format(zonedDateTime);
        return toDate(str, spec.sqlSpec);
    }

    @Override
    public String qInstant(Instant instant) {
        DateTimeSpec spec = dateTimeSpec;
        String str = spec.format(instant);
        return toDate(str, spec.sqlSpec);
    }

    private static int milliToNanoConst = 1000000;

    @Override
    public String qLocalDateTime(org.joda.time.LocalDateTime localDateTime) {
        LocalDateTime j = null;
        if (localDateTime != null) {
            j = LocalDateTime.of(//
                    localDateTime.getYear(), //
                    localDateTime.getMonthOfYear(), //
                    localDateTime.getDayOfMonth(), //
                    localDateTime.getHourOfDay(), //
                    localDateTime.getMinuteOfHour(), //
                    localDateTime.getSecondOfMinute(), //
                    localDateTime.getMillisOfSecond() * milliToNanoConst);
        }
        return qLocalDateTime(j);
    }

    @Override
    public String qLocalDate(org.joda.time.LocalDate localDate) {
        LocalDate j = null;
        if (localDate != null) {
            j = LocalDate.of(//
                    localDate.getYear(), //
                    localDate.getMonthOfYear(), //
                    localDate.getDayOfMonth());
        }
        return qLocalDate(j);
    }

    @Override
    public String qLocalTime(org.joda.time.LocalTime localTime) {
        LocalTime j = null;
        if (localTime != null) {
            j = LocalTime.of(//
                    localTime.getHourOfDay(), //
                    localTime.getMinuteOfHour(), //
                    localTime.getSecondOfMinute(), //
                    localTime.getMillisOfSecond() * milliToNanoConst);
        }
        return qLocalTime(j);
    }

    @Override
    public String qDateTime(DateTime dateTime) {
        ZonedDateTime j = null;
        if (dateTime != null) {
            Instant instant = Instant.ofEpochSecond(dateTime.toDate().getTime());
            ZoneId zone = null;
            DateTimeZone dateTimeZone = dateTime.getZone();
            if (dateTimeZone != null)
                zone = ZoneId.of(dateTimeZone.getID());
            j = ZonedDateTime.ofInstant(instant, zone);
        }
        return qZonedDateTime(j);
    }

}
