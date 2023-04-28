package net.bodz.bas.t.specmap;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.BCharOut;

public class IPv6SpecMapTest
        extends Assert {

    @Test
    public void testPrefix_16()
            throws ParseException {
        IPv6SpecMap<String> map = new IPv6SpecMap<>();
        map.addPrefix("a:b:1:1::", 48, "lan-1");
        map.addPrefix("a:b:2:1::", 48, "lan-2");

        String name = map.find("a:b:1::");
        assertEquals("lan-1", name);

        name = map.find("a:b:2::");
        assertEquals("lan-2", name);

        name = map.find("a:b:3::");
        assertNull(name);
    }

    @Test
    public void testPrefix_frag()
            throws ParseException {
        IPv6SpecMap<String> map = new IPv6SpecMap<>();
        map.addPrefix("a:b:0::", 33, "lan-1");
        map.addPrefix("a:b:8000::", 33, "lan-2");

        String name = map.find("a:b:0::");
        assertEquals("lan-1", name);

        name = map.find("a:b:7fff::");
        assertEquals("lan-1", name);

        name = map.find("a:b:8000::");
        assertEquals("lan-2", name);

        name = map.find("a:b:ffff::");
        assertEquals("lan-2", name);
    }

    public static void main(String[] args)
            throws ParseException {
        IPv6SpecMap<String> map = new IPv6SpecMap<>();
        map.addPrefix("a:b:0::", 33, "lan-1");
        map.addPrefix("a:b:8000::", 33, "lan-2");
        BCharOut buf = new BCharOut();
        map.accept(new SpecNodeDumper<>(buf.indented()));
        System.out.println(buf.toString());
    }

}
