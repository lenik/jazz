package net.bodz.bas.regex;

import junit.framework.TestCase;

import net.bodz.bas.regex.StringQuote;

import org.junit.Test;

public class StringQuoteTest
        extends TestCase {

    @Test
    public void testEscape() {
        class D {
            void o(String input, String expected) {
                String actual = StringQuote.escape(input);
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
