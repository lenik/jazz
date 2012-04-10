package net.bodz.mda.xjdoc.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

class Foo10 {
}

public class TypeNameContextTest {

    TypeNameContext context;

    @Before
    public void init() {
        context = new TypeNameContext(TypeNameContext.class);
    }

    @Test
    public void testPrimitiveType() {
        assertEquals("int", context.importType(int.class));
    }

    @Test
    public void testJavaLangType() {
        assertEquals("String", context.importType(String.class));
    }

    @Test
    public void testSimpleType() {
        assertEquals("Foo10", context.importType(Foo10.class));
    }

    @Test
    public void testArrayType() {
        Object a = new Foo10[][] {};
        String alias = context.importType(a.getClass());
        assertEquals("Foo10[][]", alias);

        for (String fqcn : context.importTypeMap.values()) {
            System.out.println("%import " + fqcn);
        }
    }

}
