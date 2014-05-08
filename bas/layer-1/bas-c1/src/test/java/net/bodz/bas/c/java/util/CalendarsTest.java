package net.bodz.bas.c.java.util;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class CalendarsTest
        extends Assert {

    @Test
    public void testParseMillisTZ() {
        Calendar calendar = Calendars.parseTimestampTZ("1399457012 +0800");
        Date date = calendar.getTime();
        assertEquals("20140507-060332", Dates.D8T6.format(date));
    }

}
