package net.bodz.bas.repr.path.builtin;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.repr.path.PathDispatchException;

public class FieldPathDispatcherTest
        extends Assert {

    public String publicField = "a";
    protected String protectedField = "b";
    private String privateField = "c";

    @Test
    public void testDispatchPublicField()
            throws PathDispatchException {
        Object test = new FieldPathDispatcherTest();
        FieldPathDispatcher fd = new FieldPathDispatcher();
        Object target = fd.dispatchTest(test, "publicField", null);
        assertSame(publicField, target);
    }

    @Test(expected = PathDispatchException.class)
    public void testDispatchProtectedField()
            throws PathDispatchException {
        Object test = new FieldPathDispatcherTest();
        FieldPathDispatcher fd = new FieldPathDispatcher();
        Object target = fd.dispatchTest(test, "protectedField", null);
        assertSame(protectedField, target);
    }

    @Test(expected = PathDispatchException.class)
    public void testDispatchPrivateField()
            throws PathDispatchException {
        Object test = new FieldPathDispatcherTest();
        FieldPathDispatcher fd = new FieldPathDispatcher();
        Object target = fd.dispatchTest(test, "privateField", null);
        assertSame(privateField, target);
    }

}
