package net.bodz.bas.sio.bits;

import org.junit.Assert;
import org.junit.Test;

public class NibbleUtilTest
        extends Assert {

    static final byte[] seq = { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef };

    void test(byte[] buf, int noff, int ncc, int wd, int ln, String expected) {
        String actual = NibbleUtil.toHex(buf, noff, ncc, wd, ln);
        assertEquals(expected, actual);
    }

    void testf(byte[] buf, int noff, int ncc, int wd, int ln, char delim, String expected) {
        String actual = NibbleUtil.toHex(buf, noff, ncc, wd, ln, delim);
        assertEquals(expected, actual);
    }

    @Test
    public void testWidth() {
        test(seq, 0, 0, 7, 4, "");
        test(seq, 0, 1, 7, 4, "0");
        test(seq, 0, 4, 7, 4, "0123");
        test(seq, 0, 7, 7, 4, "0123 456");
        test(seq, 0, 8, 7, 4, "0123 456\n7");
        test(seq, 8, 8, 7, 4, "89ab cde\nf");
        test(seq, 0, 16, 7, 4, "0123 456\n789a bcd\nef");
    }

    @Test
    public void testDelim() {
        testf(seq, 0, 8, 7, 4, ' ', "0123 456\n7");
        testf(seq, 0, 8, 7, 4, ':', "0123:456\n7");
    }

}
