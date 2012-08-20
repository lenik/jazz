package net.bodz.bas.c.java.util.regex;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.string.StringEscape;

public class StringEscapeTest
        extends Assert {

    @Test
    public void testEscape() {
        class D {
            void o(String input, String expected) {
                String actual = StringEscape.java(input);
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
