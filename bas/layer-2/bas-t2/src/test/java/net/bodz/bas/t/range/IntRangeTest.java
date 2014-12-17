package net.bodz.bas.t.range;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.t.range.IntRange;

public class IntRangeTest
        extends Assert {

    @Test
    public void test1() {
        IntRange range = new IntRange(10, 20);
        assertEquals(10, range.size());
        assertEquals(10, (int) range.ceiling(3));
        assertEquals(19, (int) range.floor(100));
        assertEquals(null, range.ceiling(20));
        assertEquals(10, (int) range.first());
        assertEquals(19, (int) range.last());
        range.add(9);
        assertEquals(11, range.size());
        for (int i = 19; i >= 9; i--)
            range.remove(i);
        assertEquals(true, range.isEmpty());
    }

}
