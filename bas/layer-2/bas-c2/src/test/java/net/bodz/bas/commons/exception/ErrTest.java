package net.bodz.bas.commons.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ErrTest {

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
