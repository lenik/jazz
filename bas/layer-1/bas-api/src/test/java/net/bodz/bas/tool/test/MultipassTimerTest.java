package net.bodz.bas.tool.test;

import org.junit.Assert;
import org.junit.Test;

public class MultipassTimerTest
        extends Assert {

    /**
     * XXX too slow.
     */
    @Test
    public void test1()
            throws Exception {
        MultipassTimer b = new MultipassTimer();
        double sin = b.measureAverageDuration(new ITimingTarget() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++)
                    Math.sin(1.23);
            }
        });
        System.out.println(sin + " ns/op");
    }

}
