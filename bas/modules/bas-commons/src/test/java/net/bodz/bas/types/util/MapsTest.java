package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class MapsTest {

    @Test
    public void test1() {
        List<String> list = new ArrayList<String>();
        list.add("a"); //$NON-NLS-1$
        list.add("b"); //$NON-NLS-1$
        list.add("c"); //$NON-NLS-1$
        Map<Integer, String> map = Maps.toMap(list);
        assertEquals("a", map.get(0)); //$NON-NLS-1$
        assertEquals("b", map.get(1)); //$NON-NLS-1$
        assertEquals("c", map.get(2)); //$NON-NLS-1$

        Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals("a", it.next().getValue()); //$NON-NLS-1$
        assertEquals("b", it.next().getValue()); //$NON-NLS-1$
        Entry<Integer, String> cEntry = it.next();
        cEntry.setValue("x"); //$NON-NLS-1$
        assertEquals("x", list.get(2)); //$NON-NLS-1$

        map.clear();
        assertEquals(0, list.size());

        list.add("hello"); //$NON-NLS-1$
        assertEquals(1, map.size());
    }

}
