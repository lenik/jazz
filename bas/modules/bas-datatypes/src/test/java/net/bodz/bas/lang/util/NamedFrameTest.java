package net.bodz.bas.lang.util;

import net.bodz.bas.lang.util.NamedFrame;

import org.junit.Test;

public class NamedFrameTest {

    void booter() {
        Runnable entry = NamedFrame.wrap(new Runnable() {
            @Override
            public void run() {
                appMain();
            }
        }, "My Frame", "我的 方法 123", "This is a named frame"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        entry.run();
    }

    void appMain() {
        System.out.println("app stuff"); //$NON-NLS-1$
        Thread.dumpStack();
    }

    @Test
    public void test1() throws Exception {
        booter();
    }

}
