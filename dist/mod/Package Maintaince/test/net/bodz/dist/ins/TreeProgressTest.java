package net.bodz.dist.ins;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TreeProgressTest {

    double eps = 0.001;

    @Test
    public void test() throws Exception {
        TreeProgress tp = new TreeProgress(10);
        tp.incr();
        tp.incr();
        assertEquals(2 / 10f, tp.get(), eps);
        TreeProgress tp2 = new TreeProgress(tp, 10);
        tp2.incr();
        assertEquals(21 / 100f, tp2.get(), eps);
        tp2.incr();
        assertEquals(22 / 100f, tp2.get(), eps);
        TreeProgress tp3 = new TreeProgress(tp2, 4);
        tp3.incr();
        assertEquals(222.5f / 1000f, tp3.get(), eps);
        tp3.incr();
        assertEquals(225f / 1000f, tp3.get(), eps);
        tp.incr();
        assertEquals(3 / 10f, tp.get(), eps);
    }

}
