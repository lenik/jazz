package net.bodz.bas.repr.req;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.t.pojo.PathEntries;

public class MutableRequestTest
        extends Assert {

    @Test
    public void testParseURL() {
        MutableRequest req = new MutableRequest(null, "abc://def?name=lenik&age=13#mid");
        assertEquals("abc", req.getProtocol());
        assertEquals("mid", req.getAnchor());
        assertEquals(new PathEntries("def"), req.getPath());
        assertEquals("lenik", req.getString("name"));
        assertEquals((Integer) 13, req.getInt("age"));
    }

}
