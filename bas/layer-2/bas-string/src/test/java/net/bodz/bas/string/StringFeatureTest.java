package net.bodz.bas.string;

import junit.framework.TestCase;

import net.bodz.bas.string.StringFeature;

import org.junit.Test;

public class StringFeatureTest
        extends TestCase {

    @Test
    public void testCount_CharPattern()
            throws Exception {
        class D {
            void o(String input, int expected) {
                int actual = StringFeature.count(input, '.');
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("empty", 0);
        d.o("hello.world", 1);
        d.o("oh... my god! ", 3);
        d.o(". dot .. everywhere .", 4);
    }

    @Test
    public void testCount_StringPattern1()
            throws Exception {
        class D {
            void o(String input, int expected) {
                int actual = StringFeature.count(input, ".");
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("empty", 0);
        d.o("hello.world", 1);
        d.o("oh... my god! ", 3);
        d.o(". dot .. everywhere .", 4);
    }

    @Test
    public void testCount_StringPattern2() {
        class D {
            void o(String input, int expected) {
                int actual = StringFeature.count(input, "..");
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("empty", 0);
        d.o("hello.world", 0);
        d.o("oh... my god! ", 1);
        d.o(". dot .. everywhere .", 1);
    }

}
