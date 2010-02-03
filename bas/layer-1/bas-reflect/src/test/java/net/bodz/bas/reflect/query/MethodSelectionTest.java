package net.bodz.bas.reflect.query;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

public class MethodSelectionTest
        extends ReflectQueryTestBase {

    @Test
    public void testOverrideOf_Public()
            throws Exception {
        Method m = A2.class.getMethod("f_public");

        assertEquals(1, ReflectQuery.selectMethods(A2.class).overrideOf(m).toArray().length);

        assertEquals(2, ReflectQuery.selectDeclaredMethods(A2.class).overrideOf(m).toArray().length);
    }

    @Test
    public void testOverrideOf_Protected()
            throws Exception {
        Method m = A2.class.getDeclaredMethod("f_protected");

        assertEquals(0, ReflectQuery.selectMethods(A2.class).overrideOf(m).toArray().length);

        assertEquals(2, ReflectQuery.selectDeclaredMethods(A2.class).overrideOf(m).toArray().length);
    }

}
