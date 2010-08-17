package net.bodz.bas.collection.tree;

import junit.framework.TestCase;

import org.junit.Test;

public class TreePathTest
        extends TestCase {

    @Test
    public void test1()
            throws Exception {
        TreePath p = new TreePath("/abc/def");
        assertTrue(p.isAbsolute());
        assertEquals("/abc/def", p.getPath());

        TreePath q = new TreePath("../cat/../cat2/");
        assertTrue(!q.isAbsolute());
        assertEquals("../cat2", q.getPath());

        TreePath pq = new TreePath(p, q);
        assertTrue(pq.isAbsolute());
        assertEquals("/abc/cat2", pq.getPath());

        TreePath x = new TreePath("/abc/cat2////");
        assertEquals(pq, x);
    }

}
