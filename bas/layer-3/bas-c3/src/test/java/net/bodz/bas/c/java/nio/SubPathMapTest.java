package net.bodz.bas.c.java.nio;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.util.Collections;

public class SubPathMapTest
        extends Assert {

    SubPathMap<String> map = new SubPathMap<>();

    public SubPathMapTest() {
        map.put("/usr/lib/bar", "bar");
        map.put("/usr/lib/baz", "baz");
        map.put("/usr/foo", "foo");
        map.put("/usr/fox", "fox");
        map.put("/usr/foo/bar", "foo-bar");
    }

    @Test
    public void testMeet() {
        assertEquals("baz", map.meet("/usr/lib/baz"));
        assertNull("baz", map.meet("/usr/lib/bazz"));
        assertEquals("foo", map.meet("/usr/foo/barr"));
    }

    @Test
    public void testJoin() {
        assertEquals(Collections.toSet("bar", "baz"), map.joinValueSet("/usr/lib/"));
        assertEquals(Collections.toSet(), map.joinValueSet("/usr/lib/ba"));
        assertEquals(Collections.toSet("foo", "foo-bar"), map.joinValueSet("/usr/foo"));
    }

}
