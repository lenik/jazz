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
        }, "My Frame", "我的 方法 123", "This is a named frame");
        entry.run();
    }

    void appMain() {
        System.out.println("app stuff");
        Thread.dumpStack();
    }

    @Test
    public void test1() throws Exception {
        booter();
    }

}
