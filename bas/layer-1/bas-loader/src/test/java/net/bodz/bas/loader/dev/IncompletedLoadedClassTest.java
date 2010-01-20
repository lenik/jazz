package net.bodz.bas.loader.dev;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IncompletedLoadedClassTest {

    static int seq = 0;

    static class A {
        static {
            seq++;
        }
    }

    @Test
    public void testLoad() {
        assertEquals("seq before load", 0, seq);
        IncompletedLoadedClass.load(A.class);
        assertEquals("seq after load", 1, seq);
        IncompletedLoadedClass.load(A.class);
        assertEquals("only load once", 1, seq);
    }

}
