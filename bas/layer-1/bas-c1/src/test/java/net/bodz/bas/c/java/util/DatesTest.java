package net.bodz.bas.c.java.util;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class DatesTest
        extends Assert
        implements
            IDateFormatConsts {

    @Test
    public void testConvertDateFormat()
            throws ParseException {
        Date date = YYYYMMDD.parse("19990406");
        String s = MM_DD_YYYY.format(date);
        assertEquals("04/06/1999", s);
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println(Dates.ISO8601.format(time));
        System.out.println(Dates.ISO8601compat.format(time));
        System.out.println(Dates.ISO8601Z.format(time));
    }

}
