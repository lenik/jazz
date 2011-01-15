package net.bodz.bas.util.exception;

import org.junit.Assert;
import org.junit.Test;

public class ErrTest
        extends Assert {

    @Test
    public void test1() {
        try {
            throw new IllegalArgumentException();
        } catch (Throwable t) {
            Err.setMessagePrefix(t, "world");
            assertEquals("prefix-null", "world", t.getMessage());
            Err.setMessagePrefix(t, "hello");
            assertEquals("prefix-s", "hello: world", t.getMessage());
        }
    }

}
