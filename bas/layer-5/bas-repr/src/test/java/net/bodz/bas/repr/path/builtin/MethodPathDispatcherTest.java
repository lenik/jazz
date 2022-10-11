package net.bodz.bas.repr.path.builtin;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.repr.path.IPathDispatcherHelper;
import net.bodz.bas.repr.path.PathDispatchException;

public class MethodPathDispatcherTest
        extends Assert
        implements
            IPathDispatcherHelper {

    public String greet(String name) {
        return "hey, " + name;
    }

    @Test
    public void testGreet()
            throws PathDispatchException {
        MethodPathDispatcher disp = new MethodPathDispatcher();
        Object actual = dispatchTest(disp, this, "greet:S/lily", null);
        assertEquals("hey, lily", actual);
    }

    public String method1(String a, int b) {
        return a + ":" + b;
    }

    @Test
    public void testMethodSig()
            throws PathDispatchException {
        MethodPathDispatcher disp = new MethodPathDispatcher();
        Object actual = dispatchTest(disp, this, "method1:Si/hello/3", null);
        assertEquals("hello:3", actual);
    }

}
