package net.bodz.bas.util;

import junit.framework.TestCase;
import net.bodz.bas.collection.tree.TreePath;
import net.bodz.bas.util.SimpleRequest;

import org.junit.Test;

public class SimpleRequestTest
        extends TestCase {

    @Test
    public void test1() {
        SimpleRequest req = new SimpleRequest(null, "abc://def?name=lenik&age=13#mid");
        assertEquals("abc", req.getProtocol());
        assertEquals("mid", req.getAnchor());
        assertEquals(new TreePath("def"), req.getPath());
        assertEquals("lenik", req.getString("name"));
        assertEquals(13, req.getInt("age"));
    }

}
