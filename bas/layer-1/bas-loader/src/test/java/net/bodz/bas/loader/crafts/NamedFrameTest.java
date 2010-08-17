package net.bodz.bas.loader.crafts;

import junit.framework.TestCase;

import org.junit.Test;

public class NamedFrameTest
        extends TestCase {

    @Test
    public void testWrapRunnable()
            throws Exception {
        Runnable entry = NamedFrame.wrap(new Runnable() {
            @Override
            public void run() {
                // [0] getStackTrace()
                // [1] run()
                // [2] NAMED-FRAME
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                StackTraceElement namedFrame = stackTrace[2];
                // space removed.
                assertEquals("MyFrame", namedFrame.getClassName());
                assertEquals("我的方法123", namedFrame.getMethodName());
            }
        }, "My Frame", "我的 方法 123", "This is a named frame");
        entry.run();
    }

}
