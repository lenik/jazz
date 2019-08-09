package net.bodz.bas.t.variant.conv;

import java.util.Calendar;

import net.bodz.bas.c.java.util.Calendars;
import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.err.TypeConvertException;

public class CalendarVarConverter
        extends AbstractVarConverter<Calendar> {

    public CalendarVarConverter() {
        super(Calendar.class);
    }

    @Override
    public Calendar fromNumber(Number in)
            throws TypeConvertException {
        long time = in.longValue();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar;
    }

    @Override
    public Calendar fromString(String in)
            throws TypeConvertException {
        try {
            return Calendars.parseXmlCalendar(in);
        } catch (IllegalArgumentException e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(Calendar value) {
        return Dates.ISO8601.format(value);
    }

    @Override
    public Number toNumber(Calendar value) {
        return value.getTimeInMillis();
    }

    @Override
    public boolean toBoolean(Calendar value) {
        return true;
    }

    public static final CalendarVarConverter INSTANCE = new CalendarVarConverter();

}
