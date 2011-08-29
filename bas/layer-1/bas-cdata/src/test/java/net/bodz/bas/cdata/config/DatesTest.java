package net.bodz.bas.cdata.config;

import java.text.ParseException;
import java.util.Date;


import org.junit.Assert;
import org.junit.Test;

public class DatesTest
        extends Assert {

    @Test
    public void testConvertDateFormat()
            throws ParseException {
        Date date = Dates.YYYYMMDD.parse("19990406");
        String s = Dates.MM_DD_YYYY.format(date);
        assertEquals("04/06/1999", s);
    }

}
