package net.bodz.bas.collection.list;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class SortedListTest
        extends Assert {

    @Test
    public void test1() {
        SortedList<String> sl = new SortedArrayList<String>();
        String[] v = { "cat", "dog", "apple", "hello", "10", "3", "9" };
        for (String s : v)
            sl.add(s);
        assertEquals("[10, 3, 9, apple, cat, dog, hello]", sl.toString());

        sl.remove(0);
        assertEquals("[3, 9, apple, cat, dog, hello]", sl.toString());

        sl.remove(2);
        assertEquals("[3, 9, cat, dog, hello]", sl.toString());

        sl.add("baby");
        assertEquals("[3, 9, baby, cat, dog, hello]", sl.toString());

        Iterator<String> it = sl.iterator();
        assertEquals("3", it.next());
        assertEquals("9", it.next());
        assertEquals("baby", it.next());

        assertEquals(4, sl.indexOf("dog"));
        assertEquals(-1, sl.indexOf("dogx"));
        assertEquals(4, sl.binarySearch("dog"));
        assertEquals(-6, sl.binarySearch("dogx"));

        sl.clear();
        assertEquals("[]", sl.toString());

        for (String s : v)
            sl.add(s);
        assertEquals("[10, 3, 9, apple, cat, dog, hello]", sl.toString());
    }

}
