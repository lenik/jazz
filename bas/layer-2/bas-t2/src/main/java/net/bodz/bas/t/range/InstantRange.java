package net.bodz.bas.t.range;

import java.time.Instant;
import java.time.temporal.TemporalAccessor;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantConvertContext;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class InstantRange
        extends TemporalAccessRange<InstantRange, Instant> {

    private static final long serialVersionUID = 1L;

    static final InstantComparator ORDER = InstantComparator.INSTANCE;

    public InstantRange(boolean startInclusive, Instant start, boolean endInclusive, Instant end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public InstantRange(Instant start, Instant end) {
        super(ORDER, start, end);
    }

    public InstantRange() {
        super(ORDER);
    }

    @Override
    protected Instant valueFrom(TemporalAccessor temporal) {
        return Instant.from(temporal);
    }

    @Override
    public Instant parseNonNullValue(String s)
            throws ParseException {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.parseInstant(s);
    }

    @Override
    public Instant preceding(Instant val) {
        return val.minusNanos(1);
    }

    @Override
    public Instant successor(Instant val) {
        return val.plusNanos(1);
    }

    @Override
    protected String format(Instant val) {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.formatInstant(val);
    }

}
