package net.bodz.bas.jvm.stack;

import net.bodz.bas.jvm.stack.StackTrace;

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
