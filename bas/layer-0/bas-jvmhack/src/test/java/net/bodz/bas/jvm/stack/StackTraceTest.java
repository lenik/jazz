package net.bodz.bas.jvm.stack;

import org.junit.Assert;
import org.junit.Test;

public class StackTraceTest
        extends Assert {

    @Test
    public void getCurrentThread()
            throws Exception {
        StackTrace.get();
    }

    @Test
    public void getAnotherThread()
            throws Exception {
        StackTrace.get(Thread.currentThread());
    }

}
