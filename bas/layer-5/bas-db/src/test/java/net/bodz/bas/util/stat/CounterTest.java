package net.bodz.bas.util.stat;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.util.stat.Counter;

public class CounterTest
        extends Assert {

    @Test
    public void incrDecr() {
        Counter<Integer> counter = new Counter<Integer>("foo", 10);
        counter.increase();
        assertEquals(11, (int) counter.getValue());
        counter.increase();
        assertEquals(12, (int) counter.getValue());
        counter.decrease();
        counter.decrease();
        assertEquals(10, (int) counter.getValue());
    }

}
