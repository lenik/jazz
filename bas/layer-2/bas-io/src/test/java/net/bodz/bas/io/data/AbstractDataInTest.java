package net.bodz.bas.io.data;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.BByteIn;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IDataIn;

public abstract class AbstractDataInTest
        extends Assert {

    protected byte[] seqBytes = {
            //
            (byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x77, (byte) 0x88, //
            (byte) 0x99, (byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff, (byte) 0x00, //
    };

    protected byte[] floatBytesBE = {
            //
            (byte) 0x41, (byte) 0x45, (byte) 0x85, (byte) 0x1f, // 12.345f
            (byte) 0x7f, (byte) 0xc0, (byte) 0x00, (byte) 0x00, // NaN
            (byte) 0x7f, (byte) 0x80, (byte) 0x00, (byte) 0x00, // +Inf
    };

    protected byte[] floatBytesLE = {
            //
            (byte) 0x1f, (byte) 0x85, (byte) 0x45, (byte) 0x41, // 12.345f
            (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0x7f, // NaN
            (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x7f, // +Inf
    };

    protected byte[] doubleBytesBE = {
            //
            (byte) 0x40, (byte) 0x28, (byte) 0xb0, (byte) 0xa3, (byte) 0xd7, (byte) 0x0a, (byte) 0x3d, (byte) 0x71, //
            (byte) 0x7f, (byte) 0xf8, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
            (byte) 0x7f, (byte) 0xf0, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
    };

    protected byte[] doubleBytesLE = {
            //
            (byte) 0x71, (byte) 0x3d, (byte) 0x0a, (byte) 0xd7, (byte) 0xa3, (byte) 0xb0, (byte) 0x28, (byte) 0x40, //
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xf8, (byte) 0x7f, //
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xf0, (byte) 0x7f, //
    };

    protected String utf8String = "Hi,ЖЫЯ.笨蛋";
    protected byte[] utf8Bytes = utf8String.getBytes(Charset.forName("utf-8"));

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
            throws IOException {
        IDataIn in = getDataIn(utf8Bytes);
        int len = utf8String.length();
        for (int i = 0; i < len; i++) {
            char expect = utf8String.charAt(i);
            char actual = in.readChar(0);
            assertEquals("Offset " + i, expect, actual);
        }
    }

}
