package net.bodz.bas.types.util;

import static org.junit.Assert.assertArrayEquals;
import net.bodz.bas.lang.modules.collection.ints.IntIterator;
import net.bodz.bas.lang.modules.collection.util.Arrays2;

import org.junit.Test;

public class Arrays2Test {

    @Test
    public void testConvertInts() {
        int[] src = { 10, 20, 30 };
        IntIterator iter = Arrays2.iterator(src);
        int[] dst = Arrays2.convert(iter);
        int[] exp = { 10, 20, 30 };
        assertArrayEquals(exp, dst);
    }

}
