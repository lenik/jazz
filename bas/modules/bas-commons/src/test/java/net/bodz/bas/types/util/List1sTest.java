package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.bodz.bas.commons.collection.fast.List1s;

import org.junit.Test;

public class List1sTest {

    @Test
    public void test1() {
        List1s<Object> list = new List1s<Object>();
        for (int repeat = 0; repeat < 10; repeat++) {
            assertTrue(list.isEmpty());
            list.add(10);
            assertEquals("[10]", list.toString()); //$NON-NLS-1$
            list.add(20);
            assertEquals("[10, 20]", list.toString()); //$NON-NLS-1$
            list.add(30);
            assertEquals("[10, 20, 30]", list.toString()); //$NON-NLS-1$
            list.remove(0);
            assertEquals("[20, 30]", list.toString()); //$NON-NLS-1$
            list.remove(1);
            assertEquals("[20]", list.toString()); //$NON-NLS-1$
            list.remove(0);
            assertEquals("[]", list.toString()); //$NON-NLS-1$
            list.add(0, 0);
            assertEquals("[0]", list.toString()); //$NON-NLS-1$
            list.add(1, 30);
            assertEquals("[0, 30]", list.toString()); //$NON-NLS-1$
            list.add(1, 20);
            assertEquals("[0, 20, 30]", list.toString()); //$NON-NLS-1$
            list.add(0, 10);
            assertEquals("[10, 0, 20, 30]", list.toString()); //$NON-NLS-1$
            list.remove(1);
            assertEquals("[10, 20, 30]", list.toString()); //$NON-NLS-1$
            list.clear();
            assertEquals("[]", list.toString()); //$NON-NLS-1$
        }
    }

}
