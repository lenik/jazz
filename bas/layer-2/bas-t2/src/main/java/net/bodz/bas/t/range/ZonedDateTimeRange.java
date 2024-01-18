package net.bodz.bas.t.range;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantConvertContext;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class ZonedDateTimeRange
        extends TemporalAccessRange<ZonedDateTimeRange, ZonedDateTime> {

    private static final long serialVersionUID = 1L;

    static final ZonedDateTimeComparator ORDER = ZonedDateTimeComparator.INSTANCE;

    public ZonedDateTimeRange(boolean startInclusive, ZonedDateTime start, boolean endInclusive, ZonedDateTime end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public ZonedDateTimeRange(ZonedDateTime start, ZonedDateTime end) {
        super(ORDER, start, end);
    }

    public ZonedDateTimeRange() {
        super(ORDER);
    }

    @Override
    protected ZonedDateTime valueFrom(TemporalAccessor temporal) {
        return ZonedDateTime.from(temporal);
    }

    @Override
    public ZonedDateTime parseNonNullValue(String s)
            throws ParseException {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.parseZonedDateTime(s);
    }

    @Override
    public ZonedDateTime preceding(ZonedDateTime val) {
        return val.minusNanos(1);
    }

    @Override
    public ZonedDateTime successor(ZonedDateTime val) {
        return val.plusNanos(1);
    }

    @Override
    protected String format(ZonedDateTime val) {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.formatZonedDateTime(val);
    }

}
