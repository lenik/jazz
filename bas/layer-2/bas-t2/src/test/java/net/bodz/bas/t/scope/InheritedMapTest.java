package net.bodz.bas.t.scope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

public class InheritedMapTest
        extends Assert {

    String scan(Map<String, ?> map) {
        List<String> keys = new ArrayList<String>(map.keySet());
        Collections.sort(keys);
        StringBuilder buf = null;
        for (Object k : keys) {
            Object v = map.get(k);
            if (buf == null) {
                buf = new StringBuilder();
                buf.append("[");
            } else {
                buf.append(", ");
            }
            buf.append(k + "=" + v);
        }
        if (buf == null)
            return "[]";
        buf.append("]");
        return buf.toString();
    }

    @Test
    public void test1() {
        HashMap<String, String> orig = new HashMap<String, String>();
        orig.put("name", "lenik");
        orig.put("age", "13");
        orig.put("color", "black");
        orig.put("size", "big");
        assertEquals("[age=13, color=black, name=lenik, size=big]", scan(orig));

        InheritedMap<String, String> m1 = new InheritedHashMap<String, String>(orig);
        Iterator<Entry<String, String>> m1eit = m1.entrySet().iterator();
        assertTrue(m1eit.hasNext());

        // the order in default hashmap implementation:
        // color, age, ...
        Entry<String, String> m11 = m1eit.next();
        String m11k = m11.getKey();
        if ("color".equals(m11k))
            assertEquals("black", m11.getValue());

        m1.put("age", "20");
        assertEquals("[age=20, color=black, name=lenik, size=big]", scan(m1));

        m1.remove("color");
        assertEquals("[age=20, name=lenik, size=big]", scan(m1));

        m1.remove("age");
        assertEquals("[name=lenik, size=big]", scan(m1));

        m1.put("color", "red");
        assertEquals("[color=red, name=lenik, size=big]", scan(m1));

        m1.put("age", null);
        assertEquals("[age=null, color=red, name=lenik, size=big]", scan(m1));

        InheritedMap<String, String> m2 = new InheritedHashMap<String, String>(m1);
        assertEquals("[age=null, color=red, name=lenik, size=big]", scan(m2));
        m2.put("age", "30");
        assertEquals("[age=30, color=red, name=lenik, size=big]", scan(m2));
        m2.put("name", "hello");
        assertEquals("[age=30, color=red, name=hello, size=big]", scan(m2));
        m2.remove("age");
        assertEquals("[color=red, name=hello, size=big]", scan(m2));
    }

}
