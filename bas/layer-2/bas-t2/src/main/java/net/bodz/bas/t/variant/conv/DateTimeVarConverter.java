package net.bodz.bas.t.variant.conv;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;

import net.bodz.bas.err.TypeConvertException;

public class DateTimeVarConverter
        extends AbstractVarConverter<DateTime> {

    DateTimeFormatter formatter;

    public DateTimeVarConverter(DateTimeFormatter format) {
        super(DateTime.class);
        this.formatter = format;
    }

    @Override
    public DateTime fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long instant = in.longValue();
        return new DateTime(instant);
    }

    @Override
    public DateTime fromString(String in)
            throws TypeConvertException {
        if (in == null)
            return null;
        if (formatter == null)
            try {
                Calendar calendar = CalendarVarConverter.INSTANCE.fromString(in);
                TimeZone tz = calendar.getTimeZone();
                DateTimeZone jodaTz = DateTimeZone.forTimeZone(tz);
                return new DateTime(calendar.getTimeInMillis(), jodaTz);
            } catch (IllegalArgumentException e) {
                throw new TypeConvertException("Failed to parse date " + in, e);
            }
        else {
            try {
                return formatter.parseDateTime(in);
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
                throw new TypeConvertException("Failed to parse date " + in, e);
            }
        }
    }

    @Override
    public String toString(DateTime value) {
        if (value == null)
            return null;
        if (formatter == null) {
            GregorianCalendar calendar = value.toGregorianCalendar();
            return CalendarVarConverter.INSTANCE.toString(calendar);
        } else {
            return formatter.print(value);
        }
    }

    @Override
    public Number toNumber(DateTime value) {
        if (value == null)
            return null;
        return value.getMillis();
    }

    @Override
    public boolean toBoolean(DateTime value) {
        return true;
    }

    public static final DateTimeVarConverter INSTANCE = new DateTimeVarConverter(null);

}
