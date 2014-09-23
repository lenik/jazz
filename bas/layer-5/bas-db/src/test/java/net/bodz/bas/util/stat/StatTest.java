package net.bodz.bas.util.stat;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.util.stat.ICounter;
import net.bodz.bas.util.stat.StatNode;
import net.bodz.bas.util.stat.SubCounterMode;

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

    @Test
    public void overlappedBranches() {
        TestCounterSpec spec = new TestCounterSpec();
        StatNode root = new StatNode(spec);

        StatNode main = root.createChild("main", SubCounterMode.setUp);
        StatNode aux = root.createChild("aux", SubCounterMode.init);

        main.resolveCounter("java/total", true).setValue(100L);
        main.resolveCounter("c/total", true).setValue(200L);

        aux.resolveCounter("mod1/total", true).setValue(50L);
        aux.resolveCounter("mod2/total", true).setValue(0L);
        aux.resolveCounter("mod3/total", true).setValue(150L);

        ICounter<Number> total = root.resolveCounter("total");
        ICounter<Number> mainTotal = root.resolveCounter("main/total");
        ICounter<Number> auxTotal = root.resolveCounter("aux/total");

        // System.out.println(total.getValue());
        assertEquals(300L, total.getValue());

        // System.out.println(mainTotal.getValue());
        assertEquals(300L, mainTotal.getValue());

        // System.out.println(auxTotal.getValue());
        assertEquals(200L, auxTotal.getValue());
    }

}
