package net.bodz.bas.reflect;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import net.bodz.bas.jdk6compat.jdk7emul.IllegalAccessException;

import org.junit.Test;

public class ReflectsTest {

    public static class Outer {

        public class Inner {
        }

        public Inner inner = new Inner();

    }

    @Test
    public void testGetEnclosingInstance()
            throws IllegalAccessException {
        Outer outer = new Outer();
        Object outer2 = Reflects.getEnclosingInstance(outer.inner);
        assertSame(outer, outer2);

        Object notexist = Reflects.getEnclosingInstance(outer2);
        assertNull(notexist);
    }

}
