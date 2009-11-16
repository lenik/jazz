package net.bodz.bas.types.der;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.types.util.Comparators;

import org.junit.Test;

public class DerMapTest {

    String scan(Map<?, ?> map) {
        List<Object> keys = new ArrayList<Object>(map.keySet());
        Collections.sort(keys, Comparators.STD);
        StringBuffer buf = null;
        for (Object k : keys) {
            Object v = map.get(k);
            if (buf == null) {
                buf = new StringBuffer();
                buf.append("["); //$NON-NLS-1$
            } else {
                buf.append(", "); //$NON-NLS-1$
            }
            buf.append(k + "=" + v); //$NON-NLS-1$
        }
        if (buf == null)
            return "[]"; //$NON-NLS-1$
        buf.append("]"); //$NON-NLS-1$
        return buf.toString();
    }

    @Test
    public void test1() {
        HashMap<String, String> orig = new HashMap<String, String>();
        orig.put("name", "lenik"); //$NON-NLS-1$ //$NON-NLS-2$
        orig.put("age", "13"); //$NON-NLS-1$ //$NON-NLS-2$
        orig.put("color", "black"); //$NON-NLS-1$ //$NON-NLS-2$
        orig.put("size", "big"); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("[age=13, color=black, name=lenik, size=big]", scan(orig)); //$NON-NLS-1$

        DerMap<String, String> m1 = new DerHashMap<String, String>(orig);
        Iterator<Entry<String, String>> m1eit = m1.entrySet().iterator();
        assertTrue(m1eit.hasNext());

        // the order in default hashmap implementation:
        // color, age, ...
        Entry<String, String> m11 = m1eit.next();
        String m11k = m11.getKey();
        if ("color".equals(m11k)) //$NON-NLS-1$
            assertEquals("black", m11.getValue()); //$NON-NLS-1$

        m1.put("age", "20"); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("[age=20, color=black, name=lenik, size=big]", scan(m1)); //$NON-NLS-1$

        m1.remove("color"); //$NON-NLS-1$
        assertEquals("[age=20, name=lenik, size=big]", scan(m1)); //$NON-NLS-1$

        m1.remove("age"); //$NON-NLS-1$
        assertEquals("[name=lenik, size=big]", scan(m1)); //$NON-NLS-1$

        m1.put("color", "red"); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("[color=red, name=lenik, size=big]", scan(m1)); //$NON-NLS-1$

        m1.put("age", null); //$NON-NLS-1$
        assertEquals("[age=null, color=red, name=lenik, size=big]", scan(m1)); //$NON-NLS-1$

        DerMap<String, String> m2 = new DerHashMap<String, String>(m1);
        assertEquals("[age=null, color=red, name=lenik, size=big]", scan(m2)); //$NON-NLS-1$
        m2.put("age", "30"); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("[age=30, color=red, name=lenik, size=big]", scan(m2)); //$NON-NLS-1$
        m2.put("name", "hello"); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("[age=30, color=red, name=hello, size=big]", scan(m2)); //$NON-NLS-1$
        m2.remove("age"); //$NON-NLS-1$
        assertEquals("[color=red, name=hello, size=big]", scan(m2)); //$NON-NLS-1$
    }

}
