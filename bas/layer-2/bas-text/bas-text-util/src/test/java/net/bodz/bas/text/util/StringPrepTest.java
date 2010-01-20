package net.bodz.bas.text.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringPrepTest {

    @Test
    public void testEscape() {
        class D {
            void o(String input, String expected) {
                String actual = StringPrep.escape(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", "");
        d.o("hello", "hello");
        d.o("a\"b\'c", "a\\\"b\\'c");
        d.o("a\nb\rc", "a\\nb\\rc");
        d.o("a\\b\\\\c\\\\\\d", "a\\\\b\\\\\\\\c\\\\\\\\\\\\d");
    }

}
