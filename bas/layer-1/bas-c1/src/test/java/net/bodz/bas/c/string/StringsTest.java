package net.bodz.bas.c.string;

import org.junit.Assert;
import org.junit.Test;

public class StringsTest
        extends Assert {

    @Test
    public void testLcfirst()
            throws Exception {
        class D {
            void o(String input, String expected) {
                String actual = Strings.lcfirst(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", "");
        d.o("H", "h");
        d.o("HH", "hH");
        d.o("HHH", "HHH");
        d.o("HHHH", "HHHH");
        d.o("UClassLoader", "uClassLoader");
        d.o("URClassLoader", "URClassLoader");
        d.o("URLClassLoader", "URLClassLoader");
    }

    @Test
    public void testEllipseStringIntString() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        assertEquals("..", Strings.ellipsis(s, 2, "..."));
        assertEquals("a...", Strings.ellipsis(s, 4, "..."));
    }

    @Test
    public void testEllipseStringIntStringStringString() {
        String s = "a/b/c/d/e/f/g/h/i";
        assertEquals("a...", Strings.ellipsis(s, 4, "...", "/", "/"));
        assertEquals("a/b/...", Strings.ellipsis(s, 7, "...", "/", "/"));
        assertEquals("a/b.../i", Strings.ellipsis(s, 8, "...", "/", "/"));
        assertEquals("a/b/c.../i", Strings.ellipsis(s, 10, "...", "/", "/"));
    }

    @Test
    public void testHyphen() {
        class D {
            void o(String input, String expected) {
                String actual = StringId.HYPHEN.breakCamel(input);
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
