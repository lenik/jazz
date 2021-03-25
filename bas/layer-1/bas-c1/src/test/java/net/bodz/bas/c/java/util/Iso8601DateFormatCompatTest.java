package net.bodz.bas.c.java.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

public class Iso8601DateFormatCompatTest {

    @Test
    public void testSamples()
            throws ParseException {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        SimpleDateFormat sdf = new Iso8601DateFormatCompat();
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        assertEquals("1970-01-01T08:00:00.000+08:00", sdf.format(new Date(0)));
        assertEquals(0L, sdf.parse("1970-01-01T08:00:00.000+08:00").getTime());

        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        assertEquals("1970-01-01T00:00:00.000Z", sdf.format(new Date(0)));
        assertEquals(0L, sdf.parse("1970-01-01T00:00:00.000Z").getTime());
    }

}
