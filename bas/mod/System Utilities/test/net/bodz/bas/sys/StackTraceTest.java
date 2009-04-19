package net.bodz.bas.sys;

import org.junit.Test;

public class StackTraceTest {

    @Test
    public void test1() throws Exception {
        StackTrace.dump(System.err);
    }

    @Test
    public void test2() throws Exception {
        StackTrace.dump(Thread.currentThread(), System.err);
    }

}
