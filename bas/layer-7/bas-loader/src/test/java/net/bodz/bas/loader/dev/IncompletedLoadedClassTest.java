package net.bodz.bas.loader.dev;

import org.junit.Assert;
import org.junit.Test;

public class IncompletedLoadedClassTest
        extends Assert {

    static int seq = 0;

    static class A {
        static {
            seq++;
        }
    }

    @Deprecated
    @Test
    public void testLoad() {
        assertEquals("seq before load", 0, seq);
        IncompletedLoadedClass.load(A.class);
        assertEquals("seq after load", 1, seq);
        IncompletedLoadedClass.load(A.class);
        assertEquals("only load once", 1, seq);
    }

}
