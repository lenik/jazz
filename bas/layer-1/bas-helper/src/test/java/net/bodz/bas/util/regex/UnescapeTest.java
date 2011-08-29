package net.bodz.bas.util.regex;

import org.junit.Assert;
import org.junit.Test;

public class UnescapeTest
        extends Assert {

    @Test
    public void testInterpString() {
        class D {
            void o(String input, String expected) {
                String actual = Unescape.unescape(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); // 1
        d.o("hello", "hello");
        d.o("he\\\\llo", "he\\llo");
        d.o("he\\\"llo", "he\"llo");
        d.o("he\\\'llo", "he\'llo");

        d.o("\\thello", "\thello");
        d.o("h\\tello", "h\tello");
        d.o("hell\\to", "hell\to");
        d.o("hello\\t", "hello\t");
        d.o("\\t", "\t");

        d.o("\\x9hello", "\thello"); // 11
        d.o("hell\\x9o", "hell\to");
        d.o("hello\\x9", "hello\t");
        d.o("\\x9", "\t");

        d.o("\\11hello", "\thello");
        d.o("h\\11ello", "h\tello");
        d.o("hell\\11o", "hell\to");
        d.o("hello\\11", "hello\t");
        d.o("\\11", "\t");

        d.o("\\x41hello", "Ahello");
        d.o("h\\x41ello", "hAello"); // 21
        d.o("hell\\x41o", "hellAo");
        d.o("hello\\x41", "helloA");
        d.o("\\x41", "A");
    }

}
