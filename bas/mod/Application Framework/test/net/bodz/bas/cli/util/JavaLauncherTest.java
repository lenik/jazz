package net.bodz.bas.cli.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JavaLauncherTest {

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
    public void testMain() throws Exception {
        assertEquals(1, state);
        JavaLauncher.main(new String[] { //
                Cat.class.getName() });
        assertEquals(100, state);
        JavaLauncher.main(new String[] { //
                Cat.class.getName(), "200" });
        assertEquals(200, state);
    }

}
