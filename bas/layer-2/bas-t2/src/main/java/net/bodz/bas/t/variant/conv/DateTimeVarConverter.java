package net.bodz.bas.t.variant.conv;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import net.bodz.bas.err.TypeConvertException;

public class DateTimeVarConverter
        extends AbstractVarConverter<DateTime> {

    public DateTimeVarConverter() {
        super(DateTime.class);
    }

    @Override
    public DateTime fromNumber(Number in)
            throws TypeConvertException {
        long instant = in.longValue();
        return new DateTime(instant);
    }

    @Override
    public DateTime fromString(String in)
            throws TypeConvertException {
        try {
            Calendar calendar = CalendarVarConverter.INSTANCE.fromString(in);
            TimeZone tz = calendar.getTimeZone();
            DateTimeZone jodaTz = DateTimeZone.forTimeZone(tz);
            return new DateTime(calendar.getTimeInMillis(), jodaTz);
        } catch (IllegalArgumentException e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(DateTime value) {
        GregorianCalendar calendar = value.toGregorianCalendar();
        return CalendarVarConverter.INSTANCE.toString(calendar);
    }

    @Override
    public Number toNumber(DateTime value) {
        return value.getMillis();
    }

    @Override
    public boolean toBoolean(DateTime value) {
        return true;
    }

    public static final DateTimeVarConverter INSTANCE = new DateTimeVarConverter();

}
