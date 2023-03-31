package net.bodz.bas.io.data;

import java.io.IOException;

import org.junit.Test;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.BByteIn;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.LengthType;

public abstract class AbstractDataInTest
        extends TestData {

    protected IDataIn getDataIn(byte[] bytes) {
        BByteIn byteIn = new BByteIn(bytes);
        return create(byteIn);
    }

    protected abstract IDataIn create(IByteIn byteIn);

    @Test
    public void testReadByte()
            throws IOException {
        IDataIn in = getDataIn(seqBytes);
        assertEquals((byte) 0x11, in.readByte());
        assertEquals((byte) 0x22, in.readByte());
        assertEquals((byte) 0x33, in.readByte());
        assertEquals((byte) 0x44, in.readByte());
    }

    @Test
    public void testReadFullyByteArray()
            throws IOException {
        IDataIn in = getDataIn(seqBytes);
        byte[] buf = new byte[4];
        in.readBytes(buf);
        assertArrayEquals(new byte[] { (byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0x44 }, buf);
        in.readBytes(buf);
        assertArrayEquals(new byte[] { (byte) 0x55, (byte) 0x66, (byte) 0x77, (byte) 0x88 }, buf);
    }

    @Test
    public void testReadBoolean()
            throws IOException {
        IDataIn in = getDataIn(seqBytes);
        for (int i = 0; i < 15; i++) {
            assertTrue("Offset " + i, in.readBool());
        }
        assertFalse(in.readBool());
    }

    @Test
    public void testReadUtf8Char()
            throws IOException, ParseException {
        IDataIn in = getDataIn(utf8Bytes);
        int len = utf8String.length();
        for (int i = 0; i < len; i++) {
            char expect = utf8String.charAt(i);
            char actual = in.readUtf8Char();
            assertEquals("Offset " + i, expect, actual);
        }
    }

    @Test
    public void testReadUtf8String_C1()
            throws Exception {
        byte[] hdr = { (byte) utf8String.length() };
        byte[] nstr = Arrays.concat(hdr, utf8Bytes);
        IDataIn in = getDataIn(nstr);
        String actual = in.readUtf8String(LengthType.charCountPrefix8).string;
        assertEquals(utf8String, actual);
    }

    @Test
    public void testReadUtf8String_B1()
            throws Exception {
        byte[] hdr = { (byte) utf8Bytes.length };
        byte[] nstr = Arrays.concat(hdr, utf8Bytes);
        IDataIn in = getDataIn(nstr);
        String actual = in.readUtf8String(LengthType.byteCountPrefix8).string;
        assertEquals(utf8String, actual);
    }

    @Test
    public void testReadUtf8String_Z()
            throws Exception {
        byte[] z = { 0 };
        byte[] strz = Arrays.concat(utf8Bytes, z);
        IDataIn in = getDataIn(strz);
        String actual = in.readUtf8String(LengthType.terminatedByNul).string;
        assertEquals(utf8String, actual);
    }

    @Test
    public void testReadCharEnc()
            throws IOException, ParseException {
        IDataIn in = getDataIn(utf8Bytes);
        int len = utf8String.length();
        for (int i = 0; i < len; i++) {
            char expect = utf8String.charAt(i);
            char actual = in.readChar(utf8).character;
            assertEquals("Offset " + i, expect, actual);
        }
    }

    @Test
    public void testReadStringEnc_C1()
            throws Exception {
        byte[] hdr = { (byte) utf8String.length() };
        byte[] nstr = Arrays.concat(hdr, utf8Bytes);
        IDataIn in = getDataIn(nstr);
        String actual = in.readString(LengthType.charCountPrefix8, utf8).string;
        assertEquals(utf8String, actual);
    }

    @Test
    public void testReadStringEnc_B1()
            throws Exception {
        byte[] hdr = { (byte) utf8Bytes.length };
        byte[] nstr = Arrays.concat(hdr, utf8Bytes);
        IDataIn in = getDataIn(nstr);
        String actual = in.readString(LengthType.byteCountPrefix8, utf8).string;
        assertEquals(utf8String, actual);
    }

    @Test
    public void testReadStringEnc_Z()
            throws Exception {
        byte[] z = { 0 };
        byte[] strz = Arrays.concat(utf8Bytes, z);
        IDataIn in = getDataIn(strz);
        String actual = in.readString(LengthType.terminatedByNul, utf8).string;
        assertEquals(utf8String, actual);
    }

}
