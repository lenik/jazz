package net.bodz.bas.lang.util;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class ReflectsTest {

    public static class Outer {

        public class Inner {
        }

        public Inner inner = new Inner();

    }

    @Test
    public void testGetEnclosingInstance() {
        Outer outer = new Outer();
        Object outer2 = Reflects.getEnclosingInstance(outer.inner);
        assertSame(outer, outer2);

        Object notexist = Reflects.getEnclosingInstance(outer2);
        assertNull(notexist);
    }

}
