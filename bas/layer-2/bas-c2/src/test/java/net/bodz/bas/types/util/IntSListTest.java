package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.bodz.bas.collection.list.IntSList;

import org.junit.Test;

public class IntSListTest {

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
