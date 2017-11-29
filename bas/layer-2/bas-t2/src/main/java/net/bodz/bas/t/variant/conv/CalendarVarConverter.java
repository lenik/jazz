package net.bodz.bas.t.variant.conv;

import java.util.Calendar;

import javax.xml.bind.DatatypeConverter;

import net.bodz.bas.err.TypeConvertException;

public class CalendarVarConverter
        extends AbstractVarConverter<Calendar> {

    public CalendarVarConverter() {
        super(Calendar.class);
    }

    @Override
    public Calendar fromString(String in)
            throws TypeConvertException {
        try {
            return DatatypeConverter.parseDateTime(in);
        } catch (IllegalArgumentException e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(Calendar value) {
        return DatatypeConverter.printDateTime(value);
    }

    @Override
    public Number toNumber(Calendar value) {
        return value.getTimeInMillis();
    }

    @Override
    public boolean toBoolean(Calendar value) {
        return true;
    }

    public static final CalendarVarConverter instance = new CalendarVarConverter();

}
