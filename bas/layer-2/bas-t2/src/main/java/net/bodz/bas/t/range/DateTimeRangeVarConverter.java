package net.bodz.bas.t.range;

import org.joda.time.DateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class DateTimeRangeVarConverter
        extends AbstractRangeVarConverter<DateTimeRange, DateTime> {

    public DateTimeRangeVarConverter() {
        super(DateTimeRange.class);
    }

    @Override
    public DateTimeRange fromString(String in)
            throws TypeConvertException {
        DateTimeRange range = new DateTimeRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public DateTimeRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long instant = in.longValue();
        DateTime dateTime = new DateTime(instant);
        return new DateTimeRange().point(dateTime);
    }

    @Override
    public Number toNumber(DateTimeRange value) {
        if (value == null)
            return null;
        DateTime dateTime = value.getPointValue();
        if (dateTime == null)
            return null;
        return dateTime.getMillis();
    }

    @Override
    public boolean toBoolean(DateTimeRange value) {
        Number number = toNumber(value);
        if (number == null)
            return false;
        return number.longValue() != 0L;
    }

    public static final DateTimeRangeVarConverter INSTANCE = new DateTimeRangeVarConverter();

}
