package net.bodz.bas.t.specmap;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class HostSpecMapTest
        extends Assert {

    HostSpecMap<String> map = new HostSpecMap<>();

    public HostSpecMapTest() {
        map.addPattern("*.example.com", 80, "web");
        map.addTop("www.example.com", null, "web");
        map.addPattern("*", 21, "ftp");
        map.addPattern("*.foo.bar", null, "foo");
        map.addPattern("*.bar", null, "bar");
    }

    @Test
    public void testSubDomain() {
        String val = map.find("bar.example.com", 80);
        assertEquals("web", val);
    }

    @Test
    public void testSameDomain() {
        String val = map.find("example.com", 80);
        assertEquals("web", val);
    }

    @Test
    public void testPort() {
        String val = map.find("whatever", 21);
        assertEquals("ftp", val);
    }

    @Ignore
    @Test
    public void testPortPriority() {
        String val = map.find("foo.bar", 21);
        assertEquals("ftp", val);
    }

    @Test
    public void testNearestParent() {
        String val = map.find("foo.bar", 100);
        assertEquals("foo", val);

        val = map.find("sub.foo.bar", 100);
        assertEquals("foo", val);
        val = map.find("s.s.s.sub.foo.bar", 100);
        assertEquals("foo", val);

        val = map.find("other.bar", 100);
        assertEquals("bar", val);

        val = map.find("bar", 100);
        assertEquals("bar", val);
    }

}
