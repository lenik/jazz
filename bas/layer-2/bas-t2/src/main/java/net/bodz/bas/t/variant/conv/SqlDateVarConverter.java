package net.bodz.bas.t.variant.conv;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.bas.err.TypeConvertException;

public class SqlDateVarConverter
        extends AbstractVarConverter<java.sql.Date> {

    DateTimeFormatter dateFormat = DateTimes.ISO_LOCAL_DATE;

    public SqlDateVarConverter() {
        super(java.sql.Date.class);
    }

    @Override
    public java.sql.Date fromNumber(Number in)
            throws TypeConvertException {
        long time = in.longValue();
        return new java.sql.Date(time);
    }

    @Override
    public java.sql.Date fromString(String in)
            throws TypeConvertException {
        try {
            LocalDate date = LocalDate.parse(in, dateFormat);
            return new java.sql.Date(date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        } catch (DateTimeParseException e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(java.sql.Date value) {
        return dateFormat.format(DateTimes.convert(value));
    }

    @Override
    public Number toNumber(java.sql.Date value) {
        return value.getTime();
    }

    @Override
    public boolean toBoolean(java.sql.Date value) {
        return true;
    }

    @Override
    public Instant toInstant(Date value) {
        long epochMilli = value.getTime();
        return Instant.ofEpochMilli(epochMilli);
    }

    @Override
    public LocalDateTime toLocalDateTime(Date value) {
        return toLocalDate(value).atStartOfDay();
    }

    @Override
    public LocalDate toLocalDate(Date value) {
        return DateTimes.convert(value);
    }

    @Override
    public LocalTime toLocalTime(Date value) {
        return LocalTime.MIN;
    }

    @Override
    public OffsetDateTime toOffsetDateTime(Date value) {
        return toZonedDateTime(value).toOffsetDateTime();
    }

    @Override
    public OffsetTime toOffsetTime(Date value) {
        return toOffsetDateTime(value).toOffsetTime();
    }

    @Override
    public ZonedDateTime toZonedDateTime(Date value) {
        return toLocalDateTime(value).atZone(ZoneId.systemDefault());
    }

    public static final SqlDateVarConverter INSTANCE = new SqlDateVarConverter();

}
