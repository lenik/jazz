package net.bodz.bas.t.variant.conv;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.bas.err.TypeConvertException;

public class DateVarConverter
        extends AbstractVarConverter<Date> {

    DateTimeFormatter format;

    public DateVarConverter(DateTimeFormatter format) {
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
            ZonedDateTime zdt = ZonedDateTime.parse(in, format);
            return new Date(zdt.toInstant().toEpochMilli());
        } catch (IllegalArgumentException e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        } catch (DateTimeParseException e) {
            throw new TypeConvertException("Parse error: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString(Date value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        ZonedDateTime zdt = DateTimes.convert(calendar);
        String str = format.format(zdt);
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

    public static final DateVarConverter INSTANCE = new DateVarConverter(DateTimes.ISO8601);

}
