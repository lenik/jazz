package net.bodz.bas.c.type;

import org.junit.Assert;
import org.junit.Test;

public class TypeNameTest
        extends Assert {

    @Test
    public void testJoinNamesClassOfQArray() {
        class D {
            void o(Class<?>[] input, String expected) {
                String actual = TypeArray.of(input).joinNames(", ", true);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o(new Class<?>[] { java.util.List.class, java.lang.String.class }, //
                "List, String");
    }

}
