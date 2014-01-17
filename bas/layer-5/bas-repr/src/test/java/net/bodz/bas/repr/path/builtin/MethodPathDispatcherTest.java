package net.bodz.bas.repr.path.builtin;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.repr.path.PathDispatchException;

public class MethodPathDispatcherTest
        extends Assert {

    public String greet(String name) {
        return "hey, " + name;
    }

    @Test
    public void testGreet()
            throws PathDispatchException {
        MethodPathDispatcher disp = new MethodPathDispatcher();
        Object actual = disp.dispatchTest(this, "greet/lily");
        assertEquals("hey, lily", actual);
    }

    public String method1(String a, int b) {
        return a + ":" + b;
    }

    @Test
    public void testMethodSig()
            throws PathDispatchException {
        MethodPathDispatcher disp = new MethodPathDispatcher();
        Object actual = disp.dispatchTest(this, "method1:Si/hello/3");
        assertEquals("hello:3", actual);
    }

}
