package net.bodz.bas.t.range;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class LocalDateRangeVarConverter
        extends AbstractRangeVarConverter<LocalDateRange, LocalDate> {

    public LocalDateRangeVarConverter() {
        super(LocalDateRange.class);
    }

    @Override
    public LocalDateRange fromString(String in)
            throws TypeConvertException {
        LocalDateRange range = new LocalDateRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public LocalDateRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long instant = in.longValue();
        DateTime dateTime = new DateTime(instant);
        return new DateTimeRange().point(dateTime).toLocalDateRange();
    }

    @Override
    public Number toNumber(LocalDateRange value) {
        if (value == null)
            return null;
        DateTime dateTime = value.toDateTimeRange().getPointValue();
        if (dateTime == null)
            return null;
        return dateTime.getMillis();
    }

    @Override
    public boolean toBoolean(LocalDateRange value) {
        Number number = toNumber(value);
        if (number == null)
            return false;
        return number.longValue() != 0L;
    }

    public static final LocalDateRangeVarConverter INSTANCE = new LocalDateRangeVarConverter();

}
