package net.bodz.bas.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import net.bodz.bas.net.CURL.Alpha;
import net.bodz.bas.types.TextMap;

import org.junit.Test;

public class CURLTest {

    @Test
    public void test1() {
        String s = "http://user:password@host/a/b/c.ext#anchor?a=b+c&c=x%26y"; //$NON-NLS-1$
        CURL curl = new CURL(s);
        assertEquals("http", curl.getType()); //$NON-NLS-1$
        Alpha[] alphas = curl.getAlphas();
        assertEquals(2, alphas.length);

        Alpha a = alphas[0];
        String[] ap = a.getInitParameters();
        assertEquals(2, ap.length);
        assertEquals("user", ap[0]); //$NON-NLS-1$
        assertEquals("password", ap[1]); //$NON-NLS-1$
        String[] ab = a.getBetas();
        assertEquals(4, ab.length);
        assertEquals("host", ab[0]); //$NON-NLS-1$
        assertEquals("a", ab[1]); //$NON-NLS-1$
        assertEquals("b", ab[2]); //$NON-NLS-1$
        assertEquals("c.ext", ab[3]); //$NON-NLS-1$

        Alpha b = alphas[1];
        String[] bp = b.getInitParameters();
        assertNull(bp);
        String[] bb = b.getBetas();
        assertEquals(1, bb.length);
        assertEquals("anchor", bb[0]); //$NON-NLS-1$

        TextMap<String> params = curl.getParameters();
        assertEquals(2, params.size());
        Set<Entry<String, String>> es = params.entrySet();
        List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(es);
        Entry<String, String> e;
        e = list.get(0);
        assertEquals("a", e.getKey()); //$NON-NLS-1$
        assertEquals("b c", e.getValue()); // b+c //$NON-NLS-1$
        e = list.get(1);
        assertEquals("c", e.getKey()); //$NON-NLS-1$
        assertEquals("x&y", e.getValue()); // x%26y //$NON-NLS-1$

        String reformat = curl.toString();
        assertEquals(s, reformat);
    }

}
