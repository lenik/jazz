package net.bodz.bas.type.util;

import junit.framework.TestCase;

import org.junit.Test;

public class TypeNameTest
        extends TestCase {

    @Test
    public void testJoinNamesClassOfQArray() {
        class D {
            void o(Class<?>[] input, String expected) {
                String actual = TypeName.join(", ", true, input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o(new Class<?>[] { java.util.List.class, java.lang.String.class }, //
                "List, String");
    }

}
