package net.bodz.bas.text.util;

import junit.framework.TestCase;

import org.junit.Test;

public class StringsTest
        extends TestCase {

    @Test
    public void testEllipseStringIntString() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        assertEquals("..", Strings.ellipse(s, 2, "..."));
        assertEquals("a...", Strings.ellipse(s, 4, "..."));
    }

    @Test
    public void testEllipseStringIntStringStringString() {
        String s = "a/b/c/d/e/f/g/h/i";
        assertEquals("a...", Strings.ellipse(s, 4, "...", "/", "/"));
        assertEquals("a/b/...", Strings.ellipse(s, 7, "...", "/", "/"));
        assertEquals("a/b.../i", Strings.ellipse(s, 8, "...", "/", "/"));
        assertEquals("a/b/c.../i", Strings.ellipse(s, 10, "...", "/", "/"));
    }

    @Test
    public void testHyphen() {
        class D {
            void o(String input, String expected) {
                String actual = Strings.hyphenatize(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("hello", "hello");
        d.o("helloWorld", "hello-world");
        d.o("HelloWorld", "hello-world");
        d.o("ABC", "abc");
        d.o("UUEncode", "uuencode");
        d.o("FFTestAndRTest", "fftest-and-rtest");
    }

    @Test
    public void testUcfirstWords()
            throws Exception {
        class D {
            void o(String input, String expected) {
                String actual = Strings.ucfirstWords(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("hello", "Hello");
        d.o("hello world", "Hello World");
        d.o("   abc  def  ", "   Abc  Def  ");
    }

}
