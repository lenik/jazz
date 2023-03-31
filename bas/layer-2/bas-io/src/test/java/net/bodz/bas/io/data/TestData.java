package net.bodz.bas.io.data;

import java.nio.charset.Charset;

import org.junit.Assert;

import net.bodz.bas.c.java.nio.Charsets;

public abstract class TestData
        extends Assert {

    protected static final byte[] seqBytes = {
            //
            (byte) 0x11, (byte) 0x22, (byte) 0x33, (byte) 0x44, (byte) 0x55, (byte) 0x66, (byte) 0x77, (byte) 0x88, //
            (byte) 0x99, (byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff, (byte) 0x00, //
    };

    protected static final byte[] floatBytesBE = {
            //
            (byte) 0x41, (byte) 0x45, (byte) 0x85, (byte) 0x1f, // 12.345f
            (byte) 0x7f, (byte) 0xc0, (byte) 0x00, (byte) 0x00, // NaN
            (byte) 0x7f, (byte) 0x80, (byte) 0x00, (byte) 0x00, // +Inf
    };

    protected static final byte[] floatBytesLE = {
            //
            (byte) 0x1f, (byte) 0x85, (byte) 0x45, (byte) 0x41, // 12.345f
            (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0x7f, // NaN
            (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x7f, // +Inf
    };

    protected static final byte[] doubleBytesBE = {
            //
            (byte) 0x40, (byte) 0x28, (byte) 0xb0, (byte) 0xa3, (byte) 0xd7, (byte) 0x0a, (byte) 0x3d, (byte) 0x71, //
            (byte) 0x7f, (byte) 0xf8, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
            (byte) 0x7f, (byte) 0xf0, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, //
    };

    protected static final byte[] doubleBytesLE = {
            //
            (byte) 0x71, (byte) 0x3d, (byte) 0x0a, (byte) 0xd7, (byte) 0xa3, (byte) 0xb0, (byte) 0x28, (byte) 0x40, //
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xf8, (byte) 0x7f, //
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xf0, (byte) 0x7f, //
    };

    protected static final Charset utf8 = Charsets.UTF_8;
    protected static final String utf8String = "Hi,ЖЫЯ.笨蛋";
    protected static final byte[] utf8Bytes = utf8String.getBytes(Charset.forName("utf-8"));

}
