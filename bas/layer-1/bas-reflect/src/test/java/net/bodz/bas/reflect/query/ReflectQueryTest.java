package net.bodz.bas.reflect.query;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReflectQueryTest
        extends ReflectQueryTestBase {

    @Test
    public void testSelectMethods()
            throws Exception {
        assertEquals(2, ReflectQuery.selectMethodsInClass(A2.class).startsWithName("f_").toArray().length);
    }

    @Test
    public void testSelectDeclaredMethods()
            throws Exception {
        assertEquals(8, ReflectQuery.selectDeclaredMethodsInClass(A2.class).startsWithName("f_").toArray().length);
    }

}
