package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Date;

import net.bodz.bas.commons.util.Dates;

import org.junit.Test;

public class DatesTest {

    @Test
    public void test1() throws ParseException {
        for (int i = 0; i < 100000; i++) {
            if (i % 10000 == 0)
                System.out.println(i);
            Date date = Dates.YYYYMMDD.parse("19990406"); //$NON-NLS-1$
            String s = Dates.MM_DD_YYYY.format(date);
            assertEquals("04/06/1999", s); //$NON-NLS-1$
        }
    }

}
