package net.bodz.bas.c.reflect.query;

import java.util.Arrays;

import org.junit.Test;

public class ClassSelectionTest
        extends ReflectQueryTestBase {

    static Object[] sig(Object... args) {
        return args;
    }

    @Test
    public void testClassChain()
            throws Exception {
        assertArrayEquals(sig(A2.class, A1.class, A.class, Object.class), //
                new ClassSelection(A2.class).toArray());
    }

    @Test
    public void testClassChain_StopAt()
            throws Exception {
        Class<?>[] a = new ClassSelection(A2.class).stopAt(A.class).toArray();
        System.out.println(Arrays.asList(a));
        assertArrayEquals(sig(A2.class, A1.class), //
                new ClassSelection(A2.class).stopAt(A.class).toArray());
    }

}
