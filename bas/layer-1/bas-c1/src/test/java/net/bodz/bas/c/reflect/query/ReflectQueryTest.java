package net.bodz.bas.c.reflect.query;

import org.junit.Test;

public class ReflectQueryTest
        extends ReflectQueryTestBase {

    @Test
    public void testSelectMethods()
            throws Exception {
        assertEquals(2, ReflectQuery.selectMethods(A2.class).nameStartsWith("f_").toArray().length);
    }

    @Test
    public void testSelectDeclaredMethods()
            throws Exception {
        assertEquals(8, ReflectQuery.selectDeclaredMethods(A2.class).nameStartsWith("f_").toArray().length);
    }

}
