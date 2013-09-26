package net.bodz.bas.t.pojo;

import org.junit.Assert;
import org.junit.Test;

public class PathEntriesTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        PathEntries p = new PathEntries("/abc/def");
        assertTrue(p.isAbsolute());
        assertEquals("/abc/def", p.getPath());

        PathEntries q = new PathEntries("../cat/../cat2/");
        assertTrue(!q.isAbsolute());
        assertEquals("../cat2", q.getPath());

        PathEntries pq = new PathEntries(p, q);
        assertTrue(pq.isAbsolute());
        assertEquals("/abc/cat2", pq.getPath());

        PathEntries x = new PathEntries("/abc/cat2////");
        assertEquals(pq, x);
    }

}
