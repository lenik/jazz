package net.bodz.bas.t.range;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Comparator;

import net.bodz.bas.err.ParseException;

public abstract class TemporalAccessRange<This, T extends TemporalAccessor>
        extends Range<This, T> {

    private static final long serialVersionUID = 1L;

    public TemporalAccessRange(Comparator<T> comparator) {
        super(comparator);
    }

    public TemporalAccessRange(Comparator<T> comparator, T start, T end) {
        super(comparator, start, end);
    }

    public TemporalAccessRange(Comparator<T> comparator, boolean startInclusive, T start, boolean endInclusive, T end) {
        super(comparator, startInclusive, start, endInclusive, end);
    }

    protected T now() {
        Instant instant = Instant.now();
        return valueFrom(instant);
    }

    public void fromNow() {
        start = now();
    }

    public void untilNow() {
        end = now();
    }

    public This minMax(T min, T max) {
        this.start = min;
        this.end = max;
        startInclusive = true;
        endInclusive = true;
        return _this();
    }

    public This parse(String s, boolean endInclusive)
            throws ParseException {
        return parse(s, endInclusive, null);
    }

    public This parse(String s, boolean endInclusive, DateTimeFormatter format)
            throws ParseException {
        int comma = s.indexOf(',');
        if (comma == -1) {
            T point = parseNonNullValue(s);
            minMax(point, point);
            return _this();
        }
        String startStr = s.substring(0, comma);
        String endStr = s.substring(comma + 1);
        T start = startStr.isEmpty() ? null : parseNonNullValue(startStr);
        T end = endStr.isEmpty() ? null : parseNonNullValue(endStr);
        this.start = start;
        this.end = end;
        this.endInclusive = endInclusive;
        return _this();
    }

    public T getMin() {
        return start;
    }

    public T getMax() {
        return end;
    }

    // convert LocalDate start/end/from/to

    protected abstract T valueFrom(TemporalAccessor temporal);

    public LocalDate getLocalDateStart() {
        T start = this.getStart();
        if (start == null)
            return null;
        LocalDate localDate = LocalDate.from(start);
        return localDate;
    }

    public void setLocalDateStart(LocalDate localDateStart) {
        this.setStart(valueFrom(localDateStart));
    }

    public LocalDate getLocalDateEnd() {
        T end = this.getEnd();
        if (end == null)
            return null;
        LocalDate localDate = LocalDate.from(end);
        return localDate;
    }

    public void setLocalDateEnd(LocalDate localDateEnd) {
        this.setTo(valueFrom(localDateEnd));
    }

    public LocalDate getLocalDateFrom() {
        T start = this.getFrom();
        if (start == null)
            return null;
        LocalDate localDate = LocalDate.from(start);
        return localDate;
    }

    public void setLocalDateFrom(LocalDate localDateFrom) {
        this.setFrom(valueFrom(localDateFrom));
    }

    public LocalDate getLocalDateTo() {
        T to = this.getTo();
        if (to == null)
            return null;
        LocalDate localDate = LocalDate.from(to);
        return localDate;
    }

    public void setLocalDateTo(LocalDate localDateTo) {
        this.setTo(valueFrom(localDateTo));
    }

    // start/end/from/to Year
    public Integer getStartYear() {
        T start = getStart();
        if (start == null)
            return null;
        return start.get(ChronoField.YEAR);
    }

    public Integer getFromYear() {
        T from = getFrom();
        if (from == null)
            return null;
        return from.get(ChronoField.YEAR);
    }

    public Integer getEndYear() {
        T end = getEnd();
        if (end == null)
            return null;
        return end.get(ChronoField.YEAR);
    }

    public Integer getToYear() {
        T to = getTo();
        if (to == null)
            return null;
        return to.get(ChronoField.YEAR);
    }

    @Override
    protected abstract String format(T val);

}
