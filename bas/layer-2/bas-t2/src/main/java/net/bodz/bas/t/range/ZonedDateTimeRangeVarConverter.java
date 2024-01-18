package net.bodz.bas.t.range;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class ZonedDateTimeRangeVarConverter
        extends AbstractTemporalRangeVarConverter<ZonedDateTimeRange, ZonedDateTime> {

    public ZonedDateTimeRangeVarConverter() {
        super(ZonedDateTimeRange.class);
    }

    @Override
    public ZonedDateTimeRange fromString(String in)
            throws TypeConvertException {
        ZonedDateTimeRange range = new ZonedDateTimeRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public ZonedDateTimeRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long epochMilli = in.longValue();
        Instant instant = Instant.ofEpochMilli(epochMilli);
        ZonedDateTime point = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        return new ZonedDateTimeRange().point(point);
    }

    @Override
    public Number toNumber(ZonedDateTimeRange value) {
        if (value == null)
            return null;
        ZonedDateTime point = value.getPointValue();
        if (point == null)
            return null;
        return point.toInstant().toEpochMilli();
    }

    @Override
    public boolean toBoolean(ZonedDateTimeRange value) {
        Number number = toNumber(value);
        if (number == null)
            return false;
        return number.longValue() != 0L;
    }

    public static final ZonedDateTimeRangeVarConverter INSTANCE = new ZonedDateTimeRangeVarConverter();

}
