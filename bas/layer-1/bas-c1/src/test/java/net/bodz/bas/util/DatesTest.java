package net.bodz.bas.util;

import java.text.ParseException;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;

public class DatesTest
        extends TestCase {

    @Test
    public void testConvertDateFormat()
            throws ParseException {
        Date date = Dates.YYYYMMDD.parse("19990406");
        String s = Dates.MM_DD_YYYY.format(date);
        assertEquals("04/06/1999", s);
    }

}
