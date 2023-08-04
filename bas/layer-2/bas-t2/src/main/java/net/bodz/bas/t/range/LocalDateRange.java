package net.bodz.bas.t.range;

import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimeParserBucket;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.bas.err.ParseException;

public class LocalDateRange
        extends Range<LocalDateRange, LocalDate> {

    private static final long serialVersionUID = 1L;

    static final LocalDateComparator ORDER = LocalDateComparator.INSTANCE;

    Chronology chrono = GregorianChronology.getInstance();
    DateTimeZone timeZone;
    Locale locale = Locale.getDefault();

    DateTimeFormatter format;
    DateTimeParser parser;

    public LocalDateRange(DateTimeZone timeZone) {
        super(ORDER);
        this.timeZone = timeZone;
        init();
    }

    public LocalDateRange(DateTimeZone timeZone, LocalDate start, LocalDate end) {
        super(ORDER, start, end);
        this.timeZone = timeZone;
        init();
    }

    public LocalDateRange(DateTimeZone timeZone, boolean startInclusive, LocalDate start, boolean endInclusive,
            LocalDate end) {
        super(ORDER, startInclusive, start, endInclusive, end);
        this.timeZone = timeZone;
        init();
    }

    public LocalDateRange() {
        this(DateTimeZone.getDefault());
    }

    public LocalDateRange(LocalDate start, LocalDate end) {
        this(DateTimeZone.getDefault(), start, end);
    }

    void init() {
        format = DateTimeFormat.forPattern("yyyy-MM-dd");
        parser = format.getParser();
    }

    public DateTimeRange toDateTimeRange() {
        DateTimeRange r = new DateTimeRange();
        r.chrono = chrono;
        r.timeZone = timeZone;
        r.locale = locale;
        r.format = format;
        r.parser = parser;
        if (start != null)
            r.start = start.toDateTimeAtStartOfDay();
        if (end != null)
            r.end = end.toDateTimeAtStartOfDay();
        return r;
    }

    public DateTimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(DateTimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public LocalDate now() {
        return new LocalDate(timeZone);
    }

    public void fromNow() {
        start = now();
    }

    public void untilNow() {
        end = now();
    }

    public void setHistoryDays(int days) {
        start = now().minusDays(days);
        end = null;
    }

    @Override
    public LocalDate parseNonNullValue(String s)
            throws ParseException {
        LocalDate now = new LocalDate(chrono);
        DateTimeParserBucket bucket = new DateTimeParserBucket(//
                0, chrono, locale, null, 2000);
        bucket.saveField(chrono.year(), now.getYear());
        bucket.saveField(chrono.monthOfYear(), now.getMonthOfYear());
        bucket.saveField(chrono.dayOfYear(), now.getDayOfYear());

        try {
            parser.parseInto(bucket, s, 0);
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage(), e);
        }
        long time = bucket.computeMillis();
        return new LocalDate(time);
    }

    @Override
    public LocalDate preceding(LocalDate val) {
        LocalDate yesterday = val.minusDays(1);
        return yesterday;
    }

    @Override
    public LocalDate successor(LocalDate val) {
        LocalDate tomorrow = val.plusDays(1);
        return tomorrow;
    }

    public LocalDateRange minMax(java.util.Date min, java.util.Date max) {
        LocalDate a = min == null ? null : new LocalDate(min);
        LocalDate b = max == null ? null : new LocalDate(max);
        return minMax(a, b);
    }

    public LocalDateRange minMax(LocalDate min, LocalDate max) {
        this.start = min;
        this.end = max;
        startInclusive = true;
        endInclusive = true;
        return this;
    }

    public LocalDateRange parse(String s, boolean inclusiveEnd)
            throws ParseException {
        return parse(s, inclusiveEnd, DateTimes.YYYY_MM_DD);
    }

    public LocalDateRange parse(String s, boolean endInclusive, DateTimeFormatter dateFormat)
            throws ParseException {
        int colon = s.indexOf(':');
        if (colon == -1) {
            LocalDate point = parseNonNullValue(s);
            minMax(point, point);
            return this;
        }
        String startStr = s.substring(0, colon);
        String endStr = s.substring(colon + 1);
        LocalDate start = startStr.isEmpty() ? null : parseNonNullValue(startStr);
        LocalDate end = endStr.isEmpty() ? null : parseNonNullValue(endStr);
        this.start = start;
        this.end = end;
        this.endInclusive = endInclusive;
        return this;
    }

    public LocalDate getMin() {
        return start;
    }

    public LocalDate getMax() {
        return end;
    }

    // convert DateTime start/end/from/to

    public DateTime getDateTimeStart() {
        LocalDate start = this.getStart();
        if (start == null)
            return null;
        DateTime localDate = start.toDateTimeAtStartOfDay();
        return localDate;
    }

    public void setDateTimeStart(DateTime dateTimeStart) {
        LocalDate localDate = null;
        if (dateTimeStart != null) {
            localDate = dateTimeStart.toLocalDate();
        }
        this.setStart(localDate);
    }

    public DateTime getDateTimeEnd() {
        LocalDate end = this.getEnd();
        if (end == null)
            return null;
        DateTime localDate = end.toDateTimeAtStartOfDay();
        return localDate;
    }

    public void setDateTimeEnd(DateTime dateTimeEnd) {
        LocalDate localDate = null;
        if (dateTimeEnd != null) {
            localDate = dateTimeEnd.toLocalDate();
        }
        this.setTo(localDate);
    }

    public DateTime getDateTimeFrom() {
        LocalDate start = this.getFrom();
        if (start == null)
            return null;
        DateTime localDate = start.toDateTimeAtStartOfDay();
        return localDate;
    }

    public void setDateTimeFrom(DateTime dateTimeFrom) {
        LocalDate localDate = null;
        if (dateTimeFrom != null) {
            localDate = dateTimeFrom.toLocalDate();
        }
        this.setFrom(localDate);
    }

    public DateTime getDateTimeTo() {
        LocalDate to = this.getTo();
        if (to == null)
            return null;
        DateTime dateTime = to.toDateTimeAtStartOfDay();
        return dateTime;
    }

    public void setDateTimeTo(DateTime dateTimeTo) {
        LocalDate localDate = null;
        if (dateTimeTo != null) {
            localDate = dateTimeTo.toLocalDate();
        }
        this.setTo(localDate);
    }

    // start/end/from/to Year
    public Integer getStartYear() {
        LocalDate start = getStart();
        if (start == null)
            return null;
        return start.getYear();
    }

    public Integer getFromYear() {
        LocalDate from = getFrom();
        if (from == null)
            return null;
        return from.getYear();
    }

    public Integer getEndYear() {
        LocalDate end = getEnd();
        if (end == null)
            return null;
        return end.getYear();
    }

    public Integer getToYear() {
        LocalDate to = getTo();
        if (to == null)
            return null;
        return to.getYear();
    }

    @Override
    protected String format(LocalDate val) {
        String s = DateTimes.YYYY_MM_DD.print(val);
        return s;
    }

}
