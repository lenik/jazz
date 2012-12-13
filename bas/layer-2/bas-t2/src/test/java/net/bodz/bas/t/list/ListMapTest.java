package net.bodz.bas.t.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.t.map.ListMap;

public class ListMapTest
        extends Assert {

    @Test
    public void test1() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        Map<Integer, String> map = new ListMap<String>(list);
        assertEquals("a", map.get(0));
        assertEquals("b", map.get(1));
        assertEquals("c", map.get(2));

        Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals("a", it.next().getValue());
        assertEquals("b", it.next().getValue());
        Entry<Integer, String> cEntry = it.next();
        cEntry.setValue("x");
        assertEquals("x", list.get(2));

        map.clear();
        assertEquals(0, list.size());

        list.add("hello");
        assertEquals(1, map.size());
    }

}
