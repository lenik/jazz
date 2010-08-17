package net.bodz.bas.collection.scope;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class DerListTest
        extends TestCase {

    @Test
    public void test1() {
        String[] numbers = { //
        "one", "two", "three" };
        List<String> orig = new ArrayList<String>();
        for (String n : numbers)
            orig.add(n);
        DerList<String> der = new DerArrayList<String>(orig);
        assertEquals(3, der.size());
        assertEquals("[one, two, three]", der.toString());

        der.add("cat"); // one two three cat
        assertEquals("[one, two, three, cat]", der.toString());

        der.add(2, "dog"); // one two dog three cat
        assertEquals("two", der.get(1));
        assertEquals("dog", der.get(2));
        assertEquals("three", der.get(3));
        assertEquals("cat", der.get(4));
        assertEquals("[one, two, dog, three, cat]", der.toString());

        der.remove(1); // one dog three cat
        assertEquals("[one, dog, three, cat]", der.toString());
        der.remove(1);// one three cat
        assertEquals("[one, three, cat]", der.toString());
        der.add(0, "hello"); // one null three cat
        assertEquals("[hello, one, three, cat]", der.toString());

        der.add(1, null); // one null three cat
        assertNull(der.get(1));
        assertEquals("[hello, null, one, three, cat]", der.toString());
        assertEquals(5, der.size());

        der.reset();
        assertEquals(3, der.size());
        assertEquals("one", der.get(0));
        assertEquals("three", der.get(2));

        der.clear();
        assertEquals(0, der.size());

        der.reset();
        assertEquals(3, der.size());
        assertEquals("one", der.get(0));
        assertEquals("three", der.get(2));
    }

}
