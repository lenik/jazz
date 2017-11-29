package net.bodz.bas.t.variant.conv;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import net.bodz.bas.err.TypeConvertException;

public class DateVarConverter
        extends AbstractVarConverter<Date> {

    public DateVarConverter() {
    }

    @Override
    public Date fromString(String in)
            throws TypeConvertException {
        try {
            Calendar calendar = DatatypeConverter.parseDateTime(in);
            return calendar.getTime();
        } catch (IllegalArgumentException e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(Date value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        String iso8601 = DatatypeConverter.printDateTime(calendar);
        return iso8601;
    }

    @Override
    public Number toNumber(Date value) {
        return value.getTime();
    }

    @Override
    public boolean toBoolean(Date value) {
        return true;
    }

    public static final DateVarConverter instance = new DateVarConverter();

}
