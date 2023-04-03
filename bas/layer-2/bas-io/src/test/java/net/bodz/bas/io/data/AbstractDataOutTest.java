package net.bodz.bas.io.data;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Test;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.io.BByteOut;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.StringLengthType;

public abstract class AbstractDataOutTest
        extends TestData {

    protected DataBuffer getDataOut() {
        BByteOut buf = new BByteOut();
        IDataOut dataOut = create(buf);
        return new DataBuffer(buf, dataOut);
    }

    protected abstract IDataOut create(IByteOut byteOut);

    @Test
    public void testWriteBytes()
            throws IOException {
        DataBuffer db = getDataOut();
        db.write(seqBytes);
        byte[] byteArray = db.toByteArray();
        assertArrayEquals(seqBytes, byteArray);
    }

    @Test
    public void testWriteUtf8Char()
            throws IOException {
        DataBuffer db = getDataOut();
        int len = utf8String.length();
        for (int i = 0; i < len; i++) {
            char ch = utf8String.charAt(i);
            db.writeUtf8Char(ch);
        }
        byte[] actual = db.toByteArray();
        assertArrayEquals(utf8Bytes, actual);
    }

    @Test
    public void testWriteUtf8String_C1()
            throws Exception {
        byte[] hdr = { (byte) utf8String.length() };
        byte[] expected = Arrays.concat(hdr, utf8Bytes);
        DataBuffer db = getDataOut();
        db.writeUtf8String(StringLengthType.charCountPrefix8, utf8String);
        byte[] actual = db.toByteArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWriteUtf8String_B1()
            throws Exception {
        byte[] hdr = { (byte) utf8Bytes.length };
        byte[] expected = Arrays.concat(hdr, utf8Bytes);
        DataBuffer db = getDataOut();
        db.writeUtf8String(StringLengthType.byteCountPrefix8, utf8String);
        byte[] actual = db.toByteArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWriteUtf8String_Z()
            throws Exception {
        byte[] z = { 0 };
        byte[] expected = Arrays.concat(utf8Bytes, z);
        DataBuffer db = getDataOut();
        db.writeUtf8String(StringLengthType.terminatedByNull, utf8String);
        byte[] actual = db.toByteArray();
        assertArrayEquals(expected, actual);
    }

    Charset charset = Charsets.UTF_8;

    @Test
    public void testWriteCharEnc()
            throws IOException {
        DataBuffer db = getDataOut();
        int len = utf8String.length();
        for (int i = 0; i < len; i++) {
            char ch = utf8String.charAt(i);
            db.writeChar(ch, charset);
        }
        byte[] actual = db.toByteArray();
        assertArrayEquals(utf8Bytes, actual);
    }

    @Test
    public void testWriteStringEnc_C1()
            throws Exception {
        byte[] hdr = { (byte) utf8String.length() };
        byte[] expected = Arrays.concat(hdr, utf8Bytes);
        DataBuffer db = getDataOut();
        db.writeString(StringLengthType.charCountPrefix8, utf8String, charset);
        byte[] actual = db.toByteArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWriteStringEnc_B1()
            throws Exception {
        byte[] hdr = { (byte) utf8Bytes.length };
        byte[] expected = Arrays.concat(hdr, utf8Bytes);
        DataBuffer db = getDataOut();
        db.writeString(StringLengthType.byteCountPrefix8, utf8String, charset);
        byte[] actual = db.toByteArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWriteStringEnc_Z()
            throws Exception {
        byte[] z = { 0 };
        byte[] expected = Arrays.concat(utf8Bytes, z);
        DataBuffer db = getDataOut();
        db.writeString(StringLengthType.terminatedByNull, utf8String, charset);
        byte[] actual = db.toByteArray();
        assertArrayEquals(expected, actual);
    }

}
