package net.bodz.bas.variant.map;

import net.bodz.bas.collection.tree.TreePath;

import org.junit.Assert;
import org.junit.Test;

public class SimpleRequestTest
        extends Assert {

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
