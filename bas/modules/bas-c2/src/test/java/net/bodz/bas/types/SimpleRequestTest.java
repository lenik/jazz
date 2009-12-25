package net.bodz.bas.types;

import static org.junit.Assert.assertEquals;

import net.bodz.bas.commons.tree.TreePath;

import org.junit.Test;

public class SimpleRequestTest {

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
