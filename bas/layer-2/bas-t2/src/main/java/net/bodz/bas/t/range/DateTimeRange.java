package net.bodz.bas.t.range;

import java.text.DateFormat;
import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimeParserBucket;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.err.ParseException;

public class DateTimeRange
        extends Range<DateTimeRange, DateTime> {

    private static final long serialVersionUID = 1L;

    static final DateTimeComparator ORDER = DateTimeComparator.getInstance();

    Chronology chrono = GregorianChronology.getInstance();
    DateTimeZone timeZone;
    Locale locale = Locale.getDefault();

    DateTimeFormatter format;
    DateTimeParser parser;

    public DateTimeRange(DateTimeZone timeZone) {
        super(ORDER);
        this.timeZone = timeZone;
        init();
    }

    public DateTimeRange(DateTimeZone timeZone, DateTime start, DateTime end) {
        super(ORDER, start, end);
        this.timeZone = timeZone;
        init();
    }

    public DateTimeRange(DateTimeZone timeZone, boolean startInclusive, DateTime start, boolean endInclusive,
            DateTime end) {
        super(ORDER, startInclusive, start, endInclusive, end);
        this.timeZone = timeZone;
        init();
    }

    public DateTimeRange() {
        this(DateTimeZone.getDefault());
    }

    public DateTimeRange(DateTime start, DateTime end) {
        this(DateTimeZone.getDefault(), start, end);
    }

    void init() {
        format = DateTimeFormat.forPattern("yyyy-MM-dd");
        parser = format.getParser();
    }

    public DateTimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(DateTimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public DateTime now() {
        return new DateTime(timeZone);
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
    public DateTime parseValue(String s)
            throws ParseException {
        DateTime now = new DateTime(chrono);
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
        return new DateTime(time);
    }

    @Override
    public DateTime preceding(DateTime val) {
        long millis = val.getMillis();
        millis--;
        DateTime prec = new DateTime(millis);
        return prec;
    }

    @Override
    public DateTime successor(DateTime val) {
        long millis = val.getMillis();
        millis++;
        DateTime succ = new DateTime(millis);
        return succ;
    }

    public DateTimeRange minMax(DateTime min, DateTime max) {
        this.start = min;
        this.end = max;
        startInclusive = true;
        endInclusive = true;
        return this;
    }

    public DateTimeRange parse(String s, boolean inclusiveEnd)
            throws ParseException {
        return parse(s, inclusiveEnd, Dates.YYYY_MM_DD);
    }

    public DateTimeRange parse(String s, boolean endInclusive, DateFormat dateFormat)
            throws ParseException {
        int colon = s.indexOf(':');
        if (colon == -1) {
            DateTime point = parseValue(s);
            minMax(point, point);
            return this;
        }
        String startStr = s.substring(0, colon);
        String endStr = s.substring(colon + 1);
        DateTime start = startStr.isEmpty() ? null : parseValue(startStr);
        DateTime end = endStr.isEmpty() ? null : parseValue(endStr);
        this.start = start;
        this.end = end;
        this.endInclusive = endInclusive;
        return this;
    }

    public DateTime getMin() {
        return start;
    }

    public DateTime getMax() {
        return end;
    }

}
