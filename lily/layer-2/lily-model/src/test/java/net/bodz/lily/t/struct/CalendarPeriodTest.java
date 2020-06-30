package net.bodz.lily.t.struct;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class CalendarPeriodTest
        extends Assert {

    @Test
    public void test()
            throws ParseException {
        CalendarPeriod period = new CalendarPeriod();
        period.setAsString("1922-4-05 13:04:56");
        period.weeks = 10;
        System.out.println(period);
    }

}
