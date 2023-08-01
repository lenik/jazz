package net.bodz.bas.t.variant.conv;

import java.util.Calendar;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.bas.err.TypeConvertException;

public class LocalDateVarConverter
        extends AbstractVarConverter<LocalDate> {

    DateTimeFormatter formatter;

    public LocalDateVarConverter(DateTimeFormatter format) {
        super(LocalDate.class);
        this.formatter = format;
    }

    @Override
    public LocalDate fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long instant = in.longValue();
        return new LocalDate(instant);
    }

    @Override
    public LocalDate fromString(String in)
            throws TypeConvertException {
        if (in == null)
            return null;
        if (formatter == null)
            try {
                Calendar calendar = CalendarVarConverter.INSTANCE.fromString(in);
                TimeZone tz = calendar.getTimeZone();
                DateTimeZone jodaTz = DateTimeZone.forTimeZone(tz);
                return new LocalDate(calendar.getTimeInMillis(), jodaTz);
            } catch (IllegalArgumentException e) {
                throw new TypeConvertException("Failed to parse date " + in, e);
            }
        else {
            try {
                return formatter.parseLocalDate(in);
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
                throw new TypeConvertException("Failed to parse date " + in, e);
            }
        }
    }

    @Override
    public String toString(LocalDate value) {
        if (value == null)
            return null;
        if (formatter == null) {
            String s = DateTimes.YYYY_MM_DD.print(value);
            return s;
        } else {
            return formatter.print(value);
        }
    }

    @Override
    public Number toNumber(LocalDate value) {
        if (value == null)
            return null;
        return value.toDateTimeAtStartOfDay().getMillis();
    }

    @Override
    public boolean toBoolean(LocalDate value) {
        return true;
    }

    public static final LocalDateVarConverter INSTANCE = new LocalDateVarConverter(null);

}
