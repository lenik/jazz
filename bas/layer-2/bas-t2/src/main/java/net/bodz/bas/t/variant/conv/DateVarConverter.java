package net.bodz.bas.t.variant.conv;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.err.TypeConvertException;

public class DateVarConverter
        extends AbstractVarConverter<Date> {

    DateFormat format;

    public DateVarConverter(DateFormat format) {
        super(Date.class);
        if (format == null)
            throw new NullPointerException("format");
        this.format = format;
    }

    @Override
    public Date fromNumber(Number in)
            throws TypeConvertException {
        long date = in.longValue();
        return new Date(date);
    }

    @Override
    public Date fromString(String in)
            throws TypeConvertException {
        try {
            return format.parse(in);
        } catch (IllegalArgumentException e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        } catch (ParseException e) {
            throw new TypeConvertException("Parse error: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString(Date value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        String str = format.format(calendar);
        return str;
    }

    @Override
    public Number toNumber(Date value) {
        return value.getTime();
    }

    @Override
    public boolean toBoolean(Date value) {
        return true;
    }

    public static final DateVarConverter INSTANCE = new DateVarConverter(Dates.YYYY_MM_DD);

}
