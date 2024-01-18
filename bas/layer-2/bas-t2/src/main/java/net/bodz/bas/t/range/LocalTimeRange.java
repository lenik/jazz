package net.bodz.bas.t.range;

import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantConvertContext;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class LocalTimeRange
        extends TemporalAccessRange<LocalTimeRange, LocalTime> {

    private static final long serialVersionUID = 1L;

    static final LocalTimeComparator ORDER = LocalTimeComparator.INSTANCE;

    public LocalTimeRange(boolean startInclusive, LocalTime start, boolean endInclusive, LocalTime end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public LocalTimeRange(LocalTime start, LocalTime end) {
        super(ORDER, start, end);
    }

    public LocalTimeRange() {
        super(ORDER);
    }

    @Override
    protected LocalTime valueFrom(TemporalAccessor temporal) {
        return LocalTime.from(temporal);
    }

    @Override
    public LocalTime parseNonNullValue(String s)
            throws ParseException {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.parseLocalTime(s);
    }

    @Override
    public LocalTime preceding(LocalTime val) {
        return val.minusNanos(1);
    }

    @Override
    public LocalTime successor(LocalTime val) {
        return val.plusNanos(1);
    }

    @Override
    protected String format(LocalTime val) {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.formatLocalTime(val);
    }

}
