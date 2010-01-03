package net.bodz.bas.util;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class TraceTest {

    static volatile boolean _deopt = true;

    @Test
    public void test1() throws Exception {
        Trace.enabled = true;
        Trace.setLogDate("yy/MM/dd HH:mm:ss.SSS"); //$NON-NLS-1$

        String tom = "tom"; //$NON-NLS-1$
        String tom2 = "to"; //$NON-NLS-1$
        if (_deopt)
            tom2 += "m"; // to avoid optimization. //$NON-NLS-1$
        assertNotSame("Should not be optimized by compiler. ", tom, tom2); //$NON-NLS-1$
        String lucy = "lucy"; //$NON-NLS-1$

        Trace.p(tom, "love lucy"); //$NON-NLS-1$
        Trace.p(tom2, "hate lucy"); //$NON-NLS-1$

        Trace.p(lucy, "is"); //$NON-NLS-1$
        Trace.c(lucy, " confused"); //$NON-NLS-1$

        Trace.link(tom, "love", lucy); //$NON-NLS-1$
        Trace.link(tom2, "hate", lucy); //$NON-NLS-1$
        Trace.link(tom, "friend", tom2, lucy); //$NON-NLS-1$

        Trace.link(lucy, "hate", tom); //$NON-NLS-1$
        Trace.unlink(lucy, "hate", tom); // empty set //$NON-NLS-1$
    }

}
