package net.bodz.bas.util.type;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.c.type.Types;

import org.junit.Assert;
import org.junit.Test;

public class TypesTest
        extends Assert {

    interface I {
    }

    class X
            implements I {
    }

    interface I2
            extends I {
    }

    class X2
            extends X
            implements I2 {
    }

    @Test
    public void testOverlayedIfaces() {
        Set<Class<?>> implied = new HashSet<Class<?>>();
        for (Class<?> t : Types.implications(X2.class))
            implied.add(t);

        assertTrue(implied.contains(I.class));
        assertTrue(implied.contains(X.class));
        assertTrue(implied.contains(I2.class));
        assertTrue(implied.contains(X2.class));
        assertTrue(implied.contains(Object.class));
        assertFalse(implied.contains(Integer.class));
    }

}
