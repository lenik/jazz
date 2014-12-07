package net.bodz.bas.c.java.util;

import java.util.Date;

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
