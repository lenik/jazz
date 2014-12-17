package net.bodz.bas.c.java.util;

import java.text.DateFormat;
import java.util.Date;

import net.bodz.bas.err.ParseException;

public class DateInterval {

    Date start;
    Date end;
    boolean inclusiveEnd;

    public DateInterval(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public static DateInterval minMax(Date min, Date max) {
        DateInterval interval = new DateInterval(min, max);
        interval.inclusiveEnd = true;
        return interval;
    }

    public static DateInterval parse(String s, boolean inclusiveEnd)
            throws ParseException {
        return parse(s, inclusiveEnd, Dates.YYYY_MM_DD);
    }

    public static DateInterval parse(String s, boolean inclusiveEnd, DateFormat dateFormat)
            throws ParseException {
        try {
            int colon = s.indexOf(':');
            if (colon == -1) {
                Date point = dateFormat.parse(s);
                return minMax(point, point);
            }
            String startStr = s.substring(0, colon);
            String endStr = s.substring(colon + 1);
            Date start = startStr.isEmpty() ? null : dateFormat.parse(startStr);
            Date end = endStr.isEmpty() ? null : dateFormat.parse(endStr);
            DateInterval dateInterval = new DateInterval(start, end);
            dateInterval.inclusiveEnd = inclusiveEnd;
            return dateInterval;
        } catch (java.text.ParseException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

}
