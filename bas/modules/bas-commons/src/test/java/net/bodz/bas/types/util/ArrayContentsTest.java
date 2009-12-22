package net.bodz.bas.types.util;

import static net.bodz.bas.commons.collection.util.ArrayOps.Ints;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ArrayContentsTest {

    @Test
    public void test1() {
        final Map<Object, Object> map = new HashMap<Object, Object>();
        int[] a = { 1, 2, 3 };
        int[] b = { 5, 6 };
        int[] c = { 1, 2, 3 };
        Object A = Ints.contents(a);
        Object B = Ints.contents(b);
        Object C = Ints.contents(c);

        map.put(a, "a"); //$NON-NLS-1$
        map.put(b, "b"); //$NON-NLS-1$

        map.put(A, "A"); //$NON-NLS-1$
        map.put(B, "B"); //$NON-NLS-1$

        assertEquals("a", map.get(a)); // //$NON-NLS-1$
        assertNull(map.get(c));
        assertEquals("A", map.get(A));
        assertEquals("A", map.get(C));
    }

}
