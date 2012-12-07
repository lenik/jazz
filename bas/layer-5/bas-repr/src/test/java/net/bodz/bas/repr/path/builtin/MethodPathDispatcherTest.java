package net.bodz.bas.repr.path.builtin;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.repr.path.PathDispatchException;

public class MethodPathDispatcherTest
        extends Assert {

    public String method1(String a, int b) {
        return a + ":" + b;
    }

    public String say(String name) {
        return "hey, " + name;
    }

    @Test
    public void test_Si()
            throws PathDispatchException {
        MethodPathDispatcher disp = new MethodPathDispatcher();
        Object actual = disp.dispatchTest(this, "method1:Si/hello/3");
        assertEquals("hello:3", actual);
    }

    @Test
    public void test_say()
            throws PathDispatchException {
        MethodPathDispatcher disp = new MethodPathDispatcher();
        Object actual = disp.dispatchTest(this, "say/clock");
        assertEquals("hey, clock", actual);
    }

}
