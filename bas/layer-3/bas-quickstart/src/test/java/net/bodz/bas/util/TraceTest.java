package net.bodz.bas.util;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class TraceTest {

    static volatile boolean _deopt = true;

    @Test
    public void test1() throws Exception {
        Trace.enabled = true;
        Trace.setLogDate("yy/MM/dd HH:mm:ss.SSS"); 

        String tom = "tom"; 
        String tom2 = "to"; 
        if (_deopt)
            tom2 += "m"; // to avoid optimization. 
        assertNotSame("Should not be optimized by compiler. ", tom, tom2); 
        String lucy = "lucy"; 

        Trace.p(tom, "love lucy"); 
        Trace.p(tom2, "hate lucy"); 

        Trace.p(lucy, "is"); 
        Trace.c(lucy, " confused"); 

        Trace.link(tom, "love", lucy); 
        Trace.link(tom2, "hate", lucy); 
        Trace.link(tom, "friend", tom2, lucy); 

        Trace.link(lucy, "hate", tom); 
        Trace.unlink(lucy, "hate", tom); // empty set 
    }

}
