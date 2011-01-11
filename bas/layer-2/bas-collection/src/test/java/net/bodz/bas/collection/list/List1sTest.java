package net.bodz.bas.collection.list;

import junit.framework.TestCase;
import net.bodz.bas.collection.list.List1s;

import org.junit.Test;

public class List1sTest
        extends TestCase {

    @Test
    public void test1() {
        List1s<Object> list = new List1s<Object>();
        for (int repeat = 0; repeat < 10; repeat++) {
            assertTrue(list.isEmpty());
            list.add(10);
            assertEquals("[10]", list.toString());
            list.add(20);
            assertEquals("[10, 20]", list.toString());
            list.add(30);
            assertEquals("[10, 20, 30]", list.toString());
            list.remove(0);
            assertEquals("[20, 30]", list.toString());
            list.remove(1);
            assertEquals("[20]", list.toString());
            list.remove(0);
            assertEquals("[]", list.toString());
            list.add(0, 0);
            assertEquals("[0]", list.toString());
            list.add(1, 30);
            assertEquals("[0, 30]", list.toString());
            list.add(1, 20);
            assertEquals("[0, 20, 30]", list.toString());
            list.add(0, 10);
            assertEquals("[10, 0, 20, 30]", list.toString());
            list.remove(1);
            assertEquals("[10, 20, 30]", list.toString());
            list.clear();
            assertEquals("[]", list.toString());
        }
    }

}
