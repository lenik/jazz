package net.bodz.bas.t.range;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantConvertContext;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class OffsetDateTimeRange
        extends TemporalAccessRange<OffsetDateTimeRange, OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    static final OffsetDateTimeComparator ORDER = OffsetDateTimeComparator.INSTANCE;

    public OffsetDateTimeRange(boolean startInclusive, OffsetDateTime start, boolean endInclusive, OffsetDateTime end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public OffsetDateTimeRange(OffsetDateTime start, OffsetDateTime end) {
        super(ORDER, start, end);
    }

    public OffsetDateTimeRange() {
        super(ORDER);
    }

    @Override
    protected OffsetDateTime valueFrom(TemporalAccessor temporal) {
        return OffsetDateTime.from(temporal);
    }

    @Override
    public OffsetDateTime parseNonNullValue(String s)
            throws ParseException {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.parseOffsetDateTime(s);
    }

    @Override
    public OffsetDateTime preceding(OffsetDateTime val) {
        return val.minusNanos(1);
    }

    @Override
    public OffsetDateTime successor(OffsetDateTime val) {
        return val.plusNanos(1);
    }

    @Override
    protected String format(OffsetDateTime val) {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.formatOffsetDateTime(val);
    }

}
