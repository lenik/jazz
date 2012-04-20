package net.bodz.bas.c.reflect.query;

import java.lang.reflect.Method;

import org.junit.Test;

public class DeclaredMethodsTest
        extends ReflectQueryTestBase {

    @Test
    public void testStopAt()
            throws Exception {
        Method[] array = new DeclaredMethods(A2.class).stopAt(Object.class).toArray();
        assertEquals(8, array.length);

        array = new DeclaredMethods(A2.class).stopAt(A.class).toArray();
        assertEquals(4, array.length);

        array = new DeclaredMethods(A2.class).stopAt(A1.class).toArray();
        assertEquals(2, array.length);

        array = new DeclaredMethods(A2.class).stopAt(A2.class).toArray();
        assertEquals(0, array.length);
    }

    @Test
    public void testNamePrefix()
            throws Exception {
        Method[] array = new DeclaredMethods(A2.class).nameStartsWith("f_").toArray();
        assertEquals(8, array.length);

        array = new DeclaredMethods(A2.class).nameStartsWith("xxx_").toArray();
        assertEquals(0, array.length);
    }

    @Test
    public void testNameSuffix()
            throws Exception {
        Method[] array = new DeclaredMethods(A2.class).nameEndsWith("_public").toArray();
        assertEquals(2, array.length);

        array = new DeclaredMethods(A2.class).nameEndsWith("_xxx").toArray();
        assertEquals(0, array.length);
    }

}
