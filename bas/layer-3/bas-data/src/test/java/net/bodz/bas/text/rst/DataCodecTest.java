package net.bodz.bas.text.rst;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.text.rst.DataCodec;
import net.bodz.bas.text.rst.IDataCodec;

public class DataCodecTest
        extends Assert {

    static final IDataCodec codec = new DataCodec();

    @Test
    public void testParseByte()
            throws ParseException {
        assertEquals((byte) 30, codec.parseByte("30"));
        assertEquals((byte) 0x40, codec.parseByte("0x40"));
        assertEquals((byte) -1, codec.parseByte("-1"));
        assertEquals((byte) 0xff, codec.parseByte("0xff"));
    }

    @Test(expected = ParseException.class)
    public void testParseByteOverflow()
            throws ParseException {
        codec.parseByte("256");
    }

    @Test
    public void testParseShort()
            throws ParseException {
        assertEquals((short) 3030, codec.parseShort("3030"));
        assertEquals((short) 0x4040, codec.parseShort("0x4040"));
        assertEquals((short) -1, codec.parseShort("-1"));
        assertEquals((short) 0xffff, codec.parseShort("0xffff"));
    }

    @Test(expected = ParseException.class)
    public void testParseShortOverflow()
            throws ParseException {
        codec.parseShort("65536");
    }

    @Test
    public void testParseInt()
            throws ParseException {
        assertEquals(30303030, codec.parseInt("30303030"));
        assertEquals(0x40404040, codec.parseInt("0x40404040"));
        assertEquals(-1, codec.parseInt("-1"));
        assertEquals(0xffffffff, codec.parseInt("0xffffffff"));
    }

    @Test
    public void testParseLong()
            throws ParseException {
        assertEquals(3030303030303030L, codec.parseLong("3030303030303030"));
        assertEquals(0x4040404040404040L, codec.parseLong("0x4040404040404040"));
        assertEquals(-1L, codec.parseLong("-1"));
        assertEquals(0xffffffffffffffffL, codec.parseLong("0xffffffffffffffff"));
    }

    @Test
    public void testParseFloat()
            throws ParseException {
        assertEquals(0.0f, codec.parseFloat("0"), 0);
        assertEquals(12.345f, codec.parseFloat("12.345"), 0);
    }

    @Test
    public void testParseDouble()
            throws ParseException {
        assertEquals(0.0, codec.parseDouble("0"), 0);
        assertEquals(12.345, codec.parseDouble("12.345"), 0);
    }

    @Test
    public void testParseBool()
            throws ParseException {
        assertTrue(codec.parseBool("true"));
        assertFalse(codec.parseBool("false"));
    }

    @Test
    public void testParseChar()
            throws ParseException {
        assertEquals('a', codec.parseChar("\"a\""));
        assertEquals('a', codec.parseChar("\"abc\""));
        assertEquals(' ', codec.parseChar("\" \""));
        assertEquals('\0', codec.parseChar("\"\""));
        assertEquals('\n', codec.parseChar("\"\\n\""));
    }

    @Test
    public void testParseString()
            throws ParseException {
        assertEquals("a", codec.parseString("\"a\""));
        assertEquals("abc", codec.parseString("\"abc\""));
        assertEquals(" ", codec.parseString("\" \""));
        assertEquals("", codec.parseString("\"\""));
        assertEquals("\n", codec.parseString("\"\\n\""));
        assertEquals("A\nB", codec.parseString("\"A\\nB\""));
    }

    @Test
    public void testParseBytesString()
            throws ParseException {
        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, }, codec.parseBytes("1 2 3 4 5"));
        assertArrayEquals(new byte[] { 0x10, 20, 0x30, 40, 0x50, }, codec.parseBytes("10h 20 30h 40 0x50"));
    }

    @Test
    public void testParseBytesStringByteArrayIntInt()
            throws ParseException {
        byte[] buf;
        assertEquals(3, codec.parseBytes("1 2 3 4 5", buf = new byte[8], 0, 3));
        assertArrayEquals(new byte[] { 1, 2, 3, 0, 0, 0, 0, 0 }, buf);

        assertEquals(5, codec.parseBytes("1 2 3 4 5", buf = new byte[8], 0, 8));
        assertArrayEquals(new byte[] { 1, 2, 3, 4, 5, 0, 0, 0 }, buf);
    }

    @Test
    public void testParseShortsString()
            throws ParseException {
        assertArrayEquals(new short[] { 1, 2, 3, 4, 5, }, codec.parseShorts("1 2 3 4 5"));
        assertArrayEquals(new short[] { 0x10, 20, 0x30, 40, 0x50, }, codec.parseShorts("10h 20 30h 40 0x50"));
    }

    @Test
    public void testParseShortsStringShortArrayIntInt()
            throws ParseException {
        short[] buf;
        assertEquals(3, codec.parseShorts("1 2 3 4 5", buf = new short[8], 0, 3));
        assertArrayEquals(new short[] { 1, 2, 3, 0, 0, 0, 0, 0 }, buf);

        assertEquals(5, codec.parseShorts("1 2 3 4 5", buf = new short[8], 0, 8));
        assertArrayEquals(new short[] { 1, 2, 3, 4, 5, 0, 0, 0 }, buf);
    }

    @Test
    public void testParseIntsString()
            throws ParseException {
        assertArrayEquals(new int[] { 1, 2, 3, 4, 5, }, codec.parseInts("1 2 3 4 5"));
        assertArrayEquals(new int[] { 0x10, 20, 0x30, 40, 0x50, }, codec.parseInts("10h 20 30h 40 0x50"));
    }

    @Test
    public void testParseIntsStringIntArrayIntInt()
            throws ParseException {
        int[] buf;
        assertEquals(3, codec.parseInts("1 2 3 4 5", buf = new int[8], 0, 3));
        assertArrayEquals(new int[] { 1, 2, 3, 0, 0, 0, 0, 0 }, buf);

        assertEquals(5, codec.parseInts("1 2 3 4 5", buf = new int[8], 0, 8));
        assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 0, 0, 0 }, buf);
    }

    @Test
    public void testParseLongsString()
            throws ParseException {
        assertArrayEquals(new long[] { 1, 2, 3, 4, 5, }, codec.parseLongs("1 2 3 4 5"));
        assertArrayEquals(new long[] { 0x10, 20, 0x30, 40, 0x50, }, codec.parseLongs("10h 20 30h 40 0x50"));
    }

    @Test
    public void testParseLongsStringLongArrayIntInt()
            throws ParseException {
        long[] buf;
        assertEquals(3, codec.parseLongs("1 2 3 4 5", buf = new long[8], 0, 3));
        assertArrayEquals(new long[] { 1, 2, 3, 0, 0, 0, 0, 0 }, buf);

        assertEquals(5, codec.parseLongs("1 2 3 4 5", buf = new long[8], 0, 8));
        assertArrayEquals(new long[] { 1, 2, 3, 4, 5, 0, 0, 0 }, buf);
    }

}
