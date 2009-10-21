package net.bodz.bas.text.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnescapeTest {

    @Test
    public void testInterpString() {
        class D {
            void o(String input, String expected) {
                String actual = Unescape.unescape(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); // 1 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello", "hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("he\\\\llo", "he\\llo"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("he\\\"llo", "he\"llo"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("he\\\'llo", "he\'llo"); //$NON-NLS-1$ //$NON-NLS-2$

        d.o("\\thello", "\thello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("h\\tello", "h\tello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hell\\to", "hell\to"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello\\t", "hello\t"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("\\t", "\t"); //$NON-NLS-1$ //$NON-NLS-2$

        d.o("\\x9hello", "\thello"); // 11 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hell\\x9o", "hell\to"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello\\x9", "hello\t"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("\\x9", "\t"); //$NON-NLS-1$ //$NON-NLS-2$

        d.o("\\11hello", "\thello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("h\\11ello", "h\tello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hell\\11o", "hell\to"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello\\11", "hello\t"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("\\11", "\t"); //$NON-NLS-1$ //$NON-NLS-2$

        d.o("\\x41hello", "Ahello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("h\\x41ello", "hAello"); // 21 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hell\\x41o", "hellAo"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello\\x41", "helloA"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("\\x41", "A"); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
