package net.bodz.bas.types.der;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DerListTest {

    @Test
    public void test1() {
        String[] numbers = { //
        "one", "two", "three" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        List<String> orig = new ArrayList<String>();
        for (String n : numbers)
            orig.add(n);
        DerList<String> der = new DerArrayList<String>(orig);
        assertEquals(3, der.size());
        assertEquals("[one, two, three]", der.toString()); //$NON-NLS-1$

        der.add("cat"); // one two three cat //$NON-NLS-1$
        assertEquals("[one, two, three, cat]", der.toString()); //$NON-NLS-1$

        der.add(2, "dog"); // one two dog three cat //$NON-NLS-1$
        assertEquals("two", der.get(1)); //$NON-NLS-1$
        assertEquals("dog", der.get(2)); //$NON-NLS-1$
        assertEquals("three", der.get(3)); //$NON-NLS-1$
        assertEquals("cat", der.get(4)); //$NON-NLS-1$
        assertEquals("[one, two, dog, three, cat]", der.toString()); //$NON-NLS-1$

        der.remove(1); // one dog three cat
        assertEquals("[one, dog, three, cat]", der.toString()); //$NON-NLS-1$
        der.remove(1);// one three cat
        assertEquals("[one, three, cat]", der.toString()); //$NON-NLS-1$
        der.add(0, "hello"); // one null three cat //$NON-NLS-1$
        assertEquals("[hello, one, three, cat]", der.toString()); //$NON-NLS-1$

        der.add(1, null); // one null three cat
        assertNull(der.get(1));
        assertEquals("[hello, null, one, three, cat]", der.toString()); //$NON-NLS-1$
        assertEquals(5, der.size());

        der.reset();
        assertEquals(3, der.size());
        assertEquals("one", der.get(0)); //$NON-NLS-1$
        assertEquals("three", der.get(2)); //$NON-NLS-1$

        der.clear();
        assertEquals(0, der.size());

        der.reset();
        assertEquals(3, der.size());
        assertEquals("one", der.get(0)); //$NON-NLS-1$
        assertEquals("three", der.get(2)); //$NON-NLS-1$
    }

}
