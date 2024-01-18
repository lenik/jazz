package net.bodz.bas.t.range;

import java.time.Instant;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;

public class InstantRangeVarConverter
        extends AbstractTemporalRangeVarConverter<InstantRange, Instant> {

    public InstantRangeVarConverter() {
        super(InstantRange.class);
    }

    @Override
    public InstantRange fromString(String in)
            throws TypeConvertException {
        InstantRange range = new InstantRange();
        try {
            return range.parse(in);
        } catch (ParseException e) {
            throw new TypeConvertException(e.getMessage(), e);
        }
    }

    @Override
    public InstantRange fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long epochMilli = in.longValue();
        Instant point = Instant.ofEpochMilli(epochMilli);
        return new InstantRange().point(point);
    }

    @Override
    public Number toNumber(InstantRange value) {
        if (value == null)
            return null;
        Instant point = value.getPointValue();
        if (point == null)
            return null;
        return point.toEpochMilli();
    }

    @Override
    public boolean toBoolean(InstantRange value) {
        Number number = toNumber(value);
        if (number == null)
            return false;
        return number.longValue() != 0L;
    }

    public static final InstantRangeVarConverter INSTANCE = new InstantRangeVarConverter();

}
