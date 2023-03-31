package net.bodz.bas.c.java.nio;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class CharsetsTest
        extends Assert {

    @Test
    public void testEncodeCache() {
        char ch = '你';
        byte[] bv = Charsets.encodeChar(Charsets.UTF_8, ch);
        byte[] bv2 = Charsets.encodeChar(Charsets.UTF_8, ch);
        assertSame(bv, bv2);

        String str = String.valueOf(ch);
        byte[] expected = str.getBytes(Charsets.UTF_8);
        assertArrayEquals(expected, bv);
    }

    @Test
    public void testDecodeCache_ascii1()
            throws ParseException {
        byte[] bytes = { (byte) 'a' };
        char ch = Charsets.decodeChar(Charsets.UTF_8, bytes);
        assertEquals('a', ch);
    }

    @Test(expected = ParseException.class)
    public void testDecodeCache_ascii2()
            throws ParseException {
        byte[] bytes = { (byte) 'a', (byte) 'b' };
        char ch = Charsets.decodeChar(Charsets.UTF_8, bytes);
        assertEquals('a', ch);
    }

    @Test
    public void testDecodeCache_cjk1()
            throws ParseException {
        byte[] bytes = "你".getBytes(Charsets.UTF_8);
        char ch = Charsets.decodeChar(Charsets.UTF_8, bytes);
        assertEquals('你', ch);
    }

    @Test(expected = MalformedInputException.class)
    public void testDecodeCache_cjk1Broken()
            throws ParseException {
        // e4 bd a0
        byte[] bytes = "你".getBytes(Charsets.UTF_8);
        // e4 bd 00
        bytes[bytes.length - 1] = 0;
        char ch = Charsets.decodeChar(Charsets.UTF_8, bytes);
        assertEquals('你', ch);
    }

    @Test(expected = ParseException.class)
    public void testDecodeCache_cjk2()
            throws ParseException {
        byte[] bytes = "你好".getBytes(Charsets.UTF_8);
        char ch = Charsets.decodeChar(Charsets.UTF_8, bytes);
        assertEquals('你', ch);
    }

    @Test(expected = ParseException.class)
    public void testDecodeEmpty()
            throws ParseException {
        byte[] empty = {};
        Charsets.decodeChar(Charsets.UTF_8, empty);
    }

    @Test(expected = ParseException.class)
    public void testDecodeUnderflow()
            throws ParseException {
        byte[] bytes = { (byte) 0x80 };
        Charsets.decodeChar(Charsets.UTF_8, bytes);
    }

    @Test(expected = ParseException.class)
    public void testDecodeUnderflow2()
            throws ParseException {
        byte[] bytes = { (byte) 0xF0 };
        Charsets.decodeChar(Charsets.UTF_8, bytes);
    }

    @Test
    public void testGetDeclaredName() {
        String[] names = { "utf-8", "gb2312", "foobar", "latin1" };
        String[] expects = { "UTF_8", "GB2312", null, "ISO_8859_1" };
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            String expect = expects[i];
            String declaredName = Charsets.getDeclaredName(names[i]);
            assertEquals(name, expect, declaredName);
        }
    }

}
