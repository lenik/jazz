package net.bodz.lily.t.struct;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class CalendarDateTest
        extends Assert {

    @Test
    public void test()
            throws ParseException {
        CalendarDate date = new CalendarDate();
        date.setAsString("1922-4-05w7-1 13:04:56");
        System.out.println(date);
    }

}
