package net.bodz.bas.shell.runner;

import org.junit.Assert;
import org.junit.Test;

public class MainMethodInvokerTest
        extends Assert {

    MainMethodInvoker mainInvoker = new MainMethodInvoker();

    static int state = 1;

    // @RunInfo(load = "")
    public static class Cat {
        public static void main(String[] args) {
            state = 100;
            if (args.length > 0) {
                int arg1 = Integer.parseInt(args[0]);
                state = arg1;
            }
        }
    }

    @Test
    public void testMain()
            throws Exception {
        assertEquals(1, state);
        mainInvoker.loadRun(Cat.class);
        assertEquals(100, state);
        mainInvoker.loadRun(Cat.class, "200");
        assertEquals(200, state);
    }

}
