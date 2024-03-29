package net.bodz.bas.t.specmap;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class NetSpecMapTest
        extends Assert {

    NetSpecMap<String> map = new NetSpecMap<>();

    public NetSpecMapTest()
            throws ParseException {
        map.putTop("1.2.3.4", "ip");
        map.putTop("1.2.0.0/16", "ip/16");
        map.putTop("::1", "ip6");
        map.putTop("foo.com", "foo.com");
        map.nameMap.putPattern("*.com", "*.com");
    }

    @Test
    public void testFind_ip4()
            throws ParseException {
        assertEquals("ip", map.find("1.2.3.4"));
    }

    @Test
    public void testFind_ip4_prefix()
            throws ParseException {
        assertEquals("ip/16", map.find("1.2.3.5"));
    }

    @Test
    public void testFind_ip6()
            throws ParseException {
        assertEquals("ip6", map.find("::0:1"));
    }

    @Test
    public void testFind_name()
            throws ParseException {
        assertEquals("foo.com", map.find("foo.com"));
    }

    @Test
    public void testFind_domain()
            throws ParseException {
        assertEquals("*.com", map.find("bar.com"));
        assertEquals("*.com", map.find("cat.foo.com"));
    }

    @Test
    public void testVhostSets()
            throws ParseException {
        NetSpecMap<String> map = new NetSpecMap<>();
        map.nameMap.putPattern("*.lo", "/simple");
        map.nameMap.putPattern("special.lo", "spec");

        InetPort32 ap = InetPort32.parse("0.0.0.0/0");
        map.ipv4Map.putPrefix(ap, "/ip");
        assertEquals("/simple", //
                map.find("foo.lo"));
        assertEquals("/simple", //
                map.find("foo.lo:1800"));
        assertEquals("spec", //
                map.find("special.lo:333"));
        assertEquals("/ip", //
                map.find("0.0.0.0"));
        assertEquals("/ip", //
                map.find("1.2.3.4"));
    }

}
