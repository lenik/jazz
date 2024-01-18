package net.bodz.bas.t.range;

import java.time.LocalTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class LocalTimeRangeVarConverter
        extends AbstractTemporalRangeVarConverter<LocalTimeRange, LocalTime> {

    public LocalTimeRangeVarConverter() {
        super(LocalTimeRange.class);
    }

    @Override
    public LocalTimeRange fromString(String in)
            throws TypeConvertException {
        LocalTimeRange range = new LocalTimeRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public LocalTimeRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long nanoOfDay = in.longValue();
        LocalTime point = LocalTime.ofNanoOfDay(nanoOfDay);
        return new LocalTimeRange().point(point);
    }

    @Override
    public Number toNumber(LocalTimeRange value) {
        if (value == null)
            return null;
        LocalTime point = value.getPointValue();
        if (point == null)
            return null;
        return point.toNanoOfDay();
    }

    @Override
    public boolean toBoolean(LocalTimeRange value) {
        Number number = toNumber(value);
        if (number == null)
            return false;
        return number.longValue() != 0L;
    }

    public static final LocalTimeRangeVarConverter INSTANCE = new LocalTimeRangeVarConverter();

}
