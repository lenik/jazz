package net.bodz.bas.t.variant.conv;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import net.bodz.bas.err.TypeConvertException;

public class DateTimeVarConverter
        extends AbstractVarConverter<DateTime> {

    DateTimeFormat format;
    DateTimeFormatter formatter;

    public DateTimeVarConverter() {
        formatter = DateTimeFormat.fullDateTime();
    }

    @Override
    public DateTime fromString(String in)
            throws TypeConvertException {
        try {
            return formatter.parseDateTime(in);
        } catch (IllegalArgumentException e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(DateTime value) {
        return formatter.print(value);
    }

    @Override
    public Number toNumber(DateTime value) {
        return value.getMillis();
    }

    @Override
    public boolean toBoolean(DateTime value) {
        return true;
    }

    public static final DateTimeVarConverter instance = new DateTimeVarConverter();

}
