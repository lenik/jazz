package net.bodz.bas.t.range;

import java.time.LocalDate;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class LocalDateRangeVarConverter
        extends AbstractTemporalRangeVarConverter<LocalDateRange, LocalDate> {

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
        long epochDay = in.longValue();
        LocalDate point = LocalDate.ofEpochDay(epochDay);
        return new LocalDateRange().point(point);
    }

    @Override
    public Number toNumber(LocalDateRange value) {
        if (value == null)
            return null;
        LocalDate point = value.getPointValue();
        if (point == null)
            return null;
        return point.toEpochDay();
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
