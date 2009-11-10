package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class SortedListTest {

    @Test
    public void test1() {
        SortedList<String> sl = new SortedArrayList<String>();
        String[] v = { "cat", "dog", "apple", "hello", "10", "3", "9" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
        for (String s : v)
            sl.add(s);
        assertEquals("[10, 3, 9, apple, cat, dog, hello]", sl.toString()); //$NON-NLS-1$

        sl.remove(0);
        assertEquals("[3, 9, apple, cat, dog, hello]", sl.toString()); //$NON-NLS-1$

        sl.remove(2);
        assertEquals("[3, 9, cat, dog, hello]", sl.toString()); //$NON-NLS-1$

        sl.add("baby"); //$NON-NLS-1$
        assertEquals("[3, 9, baby, cat, dog, hello]", sl.toString()); //$NON-NLS-1$

        Iterator<String> it = sl.iterator();
        assertEquals("3", it.next()); //$NON-NLS-1$
        assertEquals("9", it.next()); //$NON-NLS-1$
        assertEquals("baby", it.next()); //$NON-NLS-1$

        assertEquals(4, sl.indexOf("dog")); //$NON-NLS-1$
        assertEquals(-1, sl.indexOf("dogx")); //$NON-NLS-1$
        assertEquals(4, sl.binarySearch("dog")); //$NON-NLS-1$
        assertEquals(-6, sl.binarySearch("dogx")); //$NON-NLS-1$

        sl.clear();
        assertEquals("[]", sl.toString()); //$NON-NLS-1$

        for (String s : v)
            sl.add(s);
        assertEquals("[10, 3, 9, apple, cat, dog, hello]", sl.toString()); //$NON-NLS-1$
    }

}
