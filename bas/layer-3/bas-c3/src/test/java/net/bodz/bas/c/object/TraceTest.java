package net.bodz.bas.c.object;

import org.junit.Assert;
import org.junit.Test;

public class TraceTest
        extends Assert {

    static volatile boolean _deopt = true;

    @Test
    public void test1()
            throws Exception {
        Trace.enabled = true;

        String tom = "tom";
        String tom2 = "to";
        if (_deopt)
            tom2 += "m"; // to avoid optimization.
        assertNotSame("Should not be optimized by compiler. ", tom, tom2);
        String lucy = "lucy";

        Trace.trace(tom, "love lucy");
        Trace.trace(tom2, "hate lucy");

        Trace.trace(lucy, "is");
        Trace.trace(lucy, " confused");

        Trace.link(tom, "love", lucy);
        Trace.link(tom2, "hate", lucy);
        Trace.link(tom, "friend", tom2, lucy);

        Trace.link(lucy, "hate", tom);
        Trace.unlink(lucy, "hate", tom); // empty set
    }

}
