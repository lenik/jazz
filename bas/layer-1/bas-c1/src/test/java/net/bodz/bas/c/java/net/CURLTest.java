package net.bodz.bas.c.java.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.net.CURL.Alpha;

public class CURLTest
        extends Assert {

    @Test
    public void test1() {
        String s = "http://user:password@host/a/b/c.ext#anchor?a=b+c&c=x%26y";
        CURL curl = new CURL(s);
        assertEquals("http", curl.getType());
        Alpha[] alphas = curl.getAlphas();
        assertEquals(2, alphas.length);

        Alpha a = alphas[0];
        String[] ap = a.getInitParameters();
        assertEquals(2, ap.length);
        assertEquals("user", ap[0]);
        assertEquals("password", ap[1]);
        String[] ab = a.getBetas();
        assertEquals(4, ab.length);
        assertEquals("host", ab[0]);
        assertEquals("a", ab[1]);
        assertEquals("b", ab[2]);
        assertEquals("c.ext", ab[3]);

        Alpha b = alphas[1];
        String[] bp = b.getInitParameters();
        assertNull(bp);
        String[] bb = b.getBetas();
        assertEquals(1, bb.length);
        assertEquals("anchor", bb[0]);

        Map<String, String> params = curl.getParameters();
        assertEquals(2, params.size());
        Set<Entry<String, String>> es = params.entrySet();
        List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(es);
        Entry<String, String> e;
        e = list.get(0);
        assertEquals("a", e.getKey());
        assertEquals("b c", e.getValue()); // b+c
        e = list.get(1);
        assertEquals("c", e.getKey());
        assertEquals("x&y", e.getValue()); // x%26y

        String reformat = curl.toString();
        assertEquals(s, reformat);
    }

}
