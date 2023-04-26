package net.bodz.bas.t.specmap;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.specmap.IPv4Utils;

public class IPv4UtilsTest
        extends Assert {

    String echo(String ip)
            throws ParseException {
        int[] addr = IPv4Utils.parse(ip);
        return IPv4Utils.format(addr);
    }

    void testConv(String ip)
            throws ParseException {
        String actual = echo(ip);
        assertEquals("identity " + ip, ip, actual);
    }

    @Test
    public void testParseFormat()
            throws ParseException {
        testConv("0.0.0.0");
        testConv("127.0.0.1");
        testConv("192.168.1.1");
    }

    @Test(expected = ParseException.class)
    public void testLess()
            throws ParseException {
        testConv("0.0.0");
    }

    @Test(expected = ParseException.class)
    public void testMore()
            throws ParseException {
        testConv("0.0.0.0.0");
    }

}
