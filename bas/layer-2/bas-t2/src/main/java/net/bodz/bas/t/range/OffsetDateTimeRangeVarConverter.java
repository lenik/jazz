package net.bodz.bas.t.range;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class OffsetDateTimeRangeVarConverter
        extends AbstractTemporalRangeVarConverter<OffsetDateTimeRange, OffsetDateTime> {

    public OffsetDateTimeRangeVarConverter() {
        super(OffsetDateTimeRange.class);
    }

    @Override
    public OffsetDateTimeRange fromString(String in)
            throws TypeConvertException {
        OffsetDateTimeRange range = new OffsetDateTimeRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public OffsetDateTimeRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long epochMilli = in.longValue();
        Instant instant = Instant.ofEpochMilli(epochMilli);
        OffsetDateTime point = OffsetDateTime.ofInstant(instant, ZoneOffset.systemDefault());
        return new OffsetDateTimeRange().point(point);
    }

    @Override
    public Number toNumber(OffsetDateTimeRange value) {
        if (value == null)
            return null;
        OffsetDateTime point = value.getPointValue();
        if (point == null)
            return null;
        return point.toInstant().toEpochMilli();
    }

    @Override
    public boolean toBoolean(OffsetDateTimeRange value) {
        Number number = toNumber(value);
        if (number == null)
            return false;
        return number.longValue() != 0L;
    }

    public static final OffsetDateTimeRangeVarConverter INSTANCE = new OffsetDateTimeRangeVarConverter();

}
