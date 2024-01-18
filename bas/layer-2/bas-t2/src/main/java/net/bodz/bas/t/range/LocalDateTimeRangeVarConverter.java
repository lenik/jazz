package net.bodz.bas.t.range;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class LocalDateTimeRangeVarConverter
        extends AbstractTemporalRangeVarConverter<LocalDateTimeRange, LocalDateTime> {

    public LocalDateTimeRangeVarConverter() {
        super(LocalDateTimeRange.class);
    }

    @Override
    public LocalDateTimeRange fromString(String in)
            throws TypeConvertException {
        LocalDateTimeRange range = new LocalDateTimeRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public LocalDateTimeRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long epochMilli = in.longValue();
        Instant instant = Instant.ofEpochMilli(epochMilli);
        LocalDateTime point = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return new LocalDateTimeRange().point(point);
    }

    @Override
    public Number toNumber(LocalDateTimeRange value) {
        if (value == null)
            return null;
        LocalDateTime point = value.getPointValue();
        if (point == null)
            return null;
        return point.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    @Override
    public boolean toBoolean(LocalDateTimeRange value) {
        Number number = toNumber(value);
        if (number == null)
            return false;
        return number.longValue() != 0L;
    }

    public static final LocalDateTimeRangeVarConverter INSTANCE = new LocalDateTimeRangeVarConverter();

}
