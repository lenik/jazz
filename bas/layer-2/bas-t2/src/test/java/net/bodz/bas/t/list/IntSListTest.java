package net.bodz.bas.t.list;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.t._int.IntSList;

public class IntSListTest
        extends Assert {

    @Test
    public void test1() {
        IntSList ints = new IntSList(5);
        assertEquals("0-1-2-3-4", ints.toString());
        assertFalse(ints.isEmpty());

        ints.remove(1);
        assertEquals("0-2-3-4", ints.toString());

        ints.remove(1);
        assertEquals("0-3-4", ints.toString());

        ints.remove(0);
        assertEquals("3-4", ints.toString());

        ints.remove(1);
        assertEquals("3", ints.toString());

        ints.remove(0);
        assertEquals("", ints.toString());
        assertTrue(ints.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveBadIndex() {
        IntSList ints = new IntSList(3);
        assertEquals("0-1-2", ints.toString());
        ints.remove(3);
    }

}
