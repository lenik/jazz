package net.bodz.bas.t.range;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantConvertContext;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class LocalDateTimeRange
        extends TemporalAccessRange<LocalDateTimeRange, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    static final LocalDateTimeComparator ORDER = LocalDateTimeComparator.INSTANCE;

    public LocalDateTimeRange(boolean startInclusive, LocalDateTime start, boolean endInclusive, LocalDateTime end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public LocalDateTimeRange(LocalDateTime start, LocalDateTime end) {
        super(ORDER, start, end);
    }

    public LocalDateTimeRange() {
        super(ORDER);
    }

    @Override
    protected LocalDateTime valueFrom(TemporalAccessor temporal) {
        return LocalDateTime.from(temporal);
    }

    @Override
    public LocalDateTime parseNonNullValue(String s)
            throws ParseException {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.parseLocalDateTime(s);
    }

    @Override
    public LocalDateTime preceding(LocalDateTime val) {
        return val.minusNanos(1);
    }

    @Override
    public LocalDateTime successor(LocalDateTime val) {
        return val.plusNanos(1);
    }

    @Override
    protected String format(LocalDateTime val) {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.formatLocalDateTime(val);
    }

}
