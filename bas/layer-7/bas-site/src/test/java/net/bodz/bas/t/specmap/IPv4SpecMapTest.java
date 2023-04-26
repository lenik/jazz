package net.bodz.bas.t.specmap;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.t.specmap.IPv4SpecMap;
import net.bodz.bas.t.specmap.SpecNodeDumper;

public class IPv4SpecMapTest
        extends Assert {

    @Test
    public void testPrefix_8()
            throws ParseException {
        IPv4SpecMap<String> map = new IPv4SpecMap<>();
        map.addPrefix("192.168.1.1", 24, "lan-1");
        map.addPrefix("192.168.2.1", 24, "lan-2");

        String name = map.find("192.168.1.21");
        assertEquals("lan-1", name);

        name = map.find("192.168.2.21");
        assertEquals("lan-2", name);

        name = map.find("192.168.3.21");
        assertNull(name);
    }

    @Test
    public void testPrefix_frag()
            throws ParseException {
        IPv4SpecMap<String> map = new IPv4SpecMap<>();
        map.addPrefix("192.168.0.0", 17, "lan-1");
        map.addPrefix("192.168.128.0", 17, "lan-2");

        String name = map.find("192.168.0.0");
        assertEquals("lan-1", name);

        name = map.find("192.168.127.255");
        assertEquals("lan-1", name);

        name = map.find("192.168.128.0");
        assertEquals("lan-2", name);

        name = map.find("192.168.255.255");
        assertEquals("lan-2", name);
    }

    public static void main(String[] args)
            throws ParseException {
        IPv4SpecMap<String> map = new IPv4SpecMap<>();
        map.addPrefix("192.168.0.0", 17, "lan-1");
        map.addPrefix("192.168.128.0", 17, "lan-2");
        BCharOut buf = new BCharOut();
        map.accept(new SpecNodeDumper<>(buf.indented()));
        System.out.println(buf.toString());
    }

}
