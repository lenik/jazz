package net.bodz.bas.text.util;

import static org.junit.Assert.assertEquals;

import java.nio.CharBuffer;

import org.junit.Test;

public class BufParsersTest {

    @Test
    public void testGetIntCharBuffer() {
        class D {
            void o(String input, String expected) {
                CharBuffer buf = CharBuffer.wrap(input);
                int val = BufParsers.getInt(buf, 10, 1023);
                String actual = val + "|" + buf; //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("0", "0|"); // 1 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("1xyz", "1|xyz"); //  //$NON-NLS-1$ //$NON-NLS-2$
        d.o("xyz", "-1|xyz"); //  //$NON-NLS-1$ //$NON-NLS-2$
        d.o("", "-1|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("1", "1|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("00000001", "1|"); //  //$NON-NLS-1$ //$NON-NLS-2$
        d.o("9", "9|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("99", "99|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("999", "999|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("9999", "999|9"); //  //$NON-NLS-1$ //$NON-NLS-2$

        d.o("1000", "1000|"); // 11 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("1020", "1020|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("1023", "1023|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("1024", "102|4"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("1033", "103|3"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("01023", "1023|"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testGetLongCharBuffer() {
        class D {
            void o(String input, String expected) {
                CharBuffer buf = CharBuffer.wrap(input);
                long val = BufParsers.getLong(buf, 10, 111222333444L);
                String actual = val + "|" + buf; //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("0", "0|"); // 1 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("1xyz", "1|xyz"); //  //$NON-NLS-1$ //$NON-NLS-2$
        d.o("xyz", "-1|xyz"); //  //$NON-NLS-1$ //$NON-NLS-2$
        d.o("", "-1|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("1", "1|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("00000001", "1|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("99999999999", "99999999999|"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("999999999999", "99999999999|9"); //  //$NON-NLS-1$ //$NON-NLS-2$

        d.o("111222333443", "111222333443|"); // 11 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("111222333444", "111222333444|"); // 11 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("111222333445", "11122233344|5"); // 11 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("000111222333444", "111222333444|"); // 11 //$NON-NLS-1$ //$NON-NLS-2$
    }

}
