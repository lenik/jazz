package net.bodz.bas.t.range;

import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantConvertContext;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class LocalDateRange
        extends TemporalAccessRange<LocalDateRange, LocalDate> {

    private static final long serialVersionUID = 1L;

    static final LocalDateComparator ORDER = LocalDateComparator.INSTANCE;

    public LocalDateRange(boolean startInclusive, LocalDate start, boolean endInclusive, LocalDate end) {
        super(ORDER, startInclusive, start, endInclusive, end);
    }

    public LocalDateRange(LocalDate start, LocalDate end) {
        super(ORDER, start, end);
    }

    public LocalDateRange() {
        super(ORDER);
    }

    @Override
    protected LocalDate valueFrom(TemporalAccessor temporal) {
        return LocalDate.from(temporal);
    }

    @Override
    public LocalDate parseNonNullValue(String s)
            throws ParseException {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.parseLocalDate(s);
    }

    @Override
    public LocalDate preceding(LocalDate val) {
        return val.minusDays(1);
    }

    @Override
    public LocalDate successor(LocalDate val) {
        return val.plusDays(1);
    }

    @Override
    protected String format(LocalDate val) {
        IVariantConvertContext context = VariantConvertContexts.getContext();
        return context.formatLocalDate(val);
    }

}
