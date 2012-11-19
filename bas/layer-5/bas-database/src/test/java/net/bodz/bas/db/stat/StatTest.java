package net.bodz.bas.db.stat;

import org.junit.Assert;
import org.junit.Test;

public class StatTest
        extends Assert {

    @Test
    public void sumUp1() {
        TestCounterSpec spec = new TestCounterSpec();
        StatNode root = new StatNode(spec);

        ICounter<Long> fooTotal = root.resolveCounter("foo/total", true);
        assertNotNull(fooTotal);

        fooTotal.increase();
        assertEquals(1L, (long) fooTotal.getValue());

        ICounter<Long> fooBarTotal = root.resolveCounter("foo/bar/total", true);
        assertEquals(0L, (long) fooBarTotal.getValue());
        fooBarTotal.setValue(3L);

        assertEquals(4L, (long) fooTotal.getValue());
    }

}
