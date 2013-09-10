package net.bodz.bas.c.string;

import org.junit.Assert;
import org.junit.Test;

public class StringNumTest
        extends Assert {

    @Test
    public void testParseByteString() {
        assertEquals((byte) 0, StringNum.parseByte("0"));
        assertEquals((byte) 123, StringNum.parseByte("123"));
        assertEquals((byte) -100, StringNum.parseByte("-100"));
    }

    @Test
    public void testParseByteStringInt() {
        assertEquals((byte) 0x30, StringNum.parseByte("30", 16));
        assertEquals((byte) 0x30, StringNum.parseByte("0x30", 0));
        assertEquals((byte) 0xff, StringNum.parseByte("0xff", 0));
    }

    @Test
    public void testParseShortString() {
        assertEquals((short) 0, StringNum.parseShort("0"));
        assertEquals((short) 123, StringNum.parseShort("123"));
        assertEquals((short) -100, StringNum.parseShort("-100"));
    }

    @Test
    public void testParseShortStringInt() {
        assertEquals((short) 0x3030, StringNum.parseShort("3030", 16));
        assertEquals((short) 0x3030, StringNum.parseShort("0x3030", 0));
        assertEquals((short) 0xffff, StringNum.parseShort("0xffff", 0));
    }

    @Test
    public void testParseIntString() {
        assertEquals(0, StringNum.parseInt("0"));
        assertEquals(123, StringNum.parseInt("123"));
        assertEquals(-100, StringNum.parseInt("-100"));
    }

    @Test(expected = NumberFormatException.class)
    public void testParseIntStringEmpty() {
        StringNum.parseInt("   ");
    }

    @Test(expected = NumberFormatException.class)
    public void testParseIntStringBadChar() {
        StringNum.parseInt("xyz");
    }

    @Test
    public void testParseIntStringInt() {
        assertEquals(0x30405060, StringNum.parseInt("30405060", 16));
        assertEquals(0x30405060, StringNum.parseInt("0x30405060", 0));
        assertEquals(0xffffffff, StringNum.parseInt("0xffffffff", 0));
    }

    @Test
    public void testParseLongString() {
        assertEquals(0L, StringNum.parseLong("0"));
        assertEquals(123L, StringNum.parseLong("123"));
        assertEquals(-100L, StringNum.parseLong("-100"));
    }

    @Test
    public void testParseLongStringInt() {
        assertEquals(0x3040506030405060L, StringNum.parseLong("3040506030405060", 16));
        assertEquals(0x3040506030405060L, StringNum.parseLong("0x3040506030405060", 0));
        assertEquals(0xffffffffffffffffL, StringNum.parseLong("0xffffffffffffffff", 0));
    }

}
