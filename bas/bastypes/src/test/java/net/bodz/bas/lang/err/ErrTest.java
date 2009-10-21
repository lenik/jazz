package net.bodz.bas.lang.err;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ErrTest {

    @Test
    public void test1() {
        try {
            throw new IllegalArgumentException();
        } catch (Throwable t) {
            Err.setMessagePrefix(t, "world"); //$NON-NLS-1$
            assertEquals("prefix-null", "world", t.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
            Err.setMessagePrefix(t, "hello"); //$NON-NLS-1$
            assertEquals("prefix-s", "hello: world", t.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

}
