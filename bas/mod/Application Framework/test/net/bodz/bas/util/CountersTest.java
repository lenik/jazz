package net.bodz.bas.util;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.a.Counts;

import org.junit.Test;

@Counts("CountersTest.db")
public class CountersTest {

    @Test
    public void test1() throws Exception {
        net.bodz.bas.util.Counters counters = new net.bodz.bas.util.Counters(
                getClass());
        System.out.println(counters);
        Counter buildid = counters.get("buildid");
        Counter packid = counters.get("packid");

        packid.init();
        assertEquals(1, packid.getAndIncrease());
        assertEquals(2, packid.getAndIncrease());
        assertEquals(3, packid.get());

        Thread.sleep(10);
        buildid.increase();

        assertEquals(1, packid.getAndIncrease());

        counters.save();
    }

}
