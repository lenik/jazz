package net.bodz.bas.t.specmap;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.specmap.IPv6Utils;

public class IPv6UtilsTest
        extends Assert {

    String echo(String ip)
            throws ParseException {
        int[] addr = IPv6Utils.parse(ip);
        return IPv6Utils.format(addr);
    }

    void testConv(String ip)
            throws ParseException {
        String actual = echo(ip);
        assertEquals("identity " + ip, ip, actual);
    }

    @Test
    public void testParseFormat_cleft()
            throws ParseException {
        testConv("::1");
    }

    @Test
    public void testParseFormat_cright()
            throws ParseException {
        testConv("1::");
    }

    @Test
    public void testParseFormat_cmid()
            throws ParseException {
        testConv("aa::ff");
    }

    @Test
    public void testParseFormat_max_left()
            throws ParseException {
        testConv("::1");
        testConv("::1:0");
        testConv("::1:0:0");
        testConv("::1:0:0:0");
    }

    @Test
    public void testParseFormat_max_right()
            throws ParseException {
        testConv("1::");
        testConv("0:1::");
        testConv("0:0:1::");
        testConv("0:0:0:1::");
    }

    @Test
    public void testParseFormat_reals()
            throws ParseException {
        testConv("fe80::3ce6:69ff:fea6:99e8");
        testConv("fe80::42:c8ff:fe33:9578");
        testConv("2406:da18:6a8:f200::100");
    }

}
