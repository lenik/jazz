package net.bodz.bas.reflect.query;

import static org.junit.Assert.assertEquals;

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
        Method[] array = new DeclaredMethods(A2.class).startsWithName("f_").toArray();
        assertEquals(8, array.length);

        array = new DeclaredMethods(A2.class).startsWithName("xxx_").toArray();
        assertEquals(0, array.length);
    }

    @Test
    public void testNameSuffix()
            throws Exception {
        Method[] array = new DeclaredMethods(A2.class).endsWithName("_public").toArray();
        assertEquals(2, array.length);

        array = new DeclaredMethods(A2.class).endsWithName("_xxx").toArray();
        assertEquals(0, array.length);
    }

}
