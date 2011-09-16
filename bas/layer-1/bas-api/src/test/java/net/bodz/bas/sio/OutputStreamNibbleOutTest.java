package net.bodz.bas.sio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.bodz.bas.sio.bits.NibByteArrayOutputStream;
import net.bodz.bas.sio.bits.OutputStreamNibbleOut;

import org.junit.Assert;
import org.junit.Test;

public class OutputStreamNibbleOutTest
        extends Assert {

    static final byte[] seq = { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef };

    @Test
    public void testToString()
            throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        buf.write("hello".getBytes("ascii"));
        assertEquals("hello", buf.toString());

        OutputStreamNibbleOut out = new OutputStreamNibbleOut(buf);
        assertEquals("hello", out.toString());

        // A: 0x41
        out.write4b(4);
        assertEquals("hello.4", out.toString());

        out.write4b(1);
        assertEquals("helloA", out.toString());

        out.write4b(0xf);
        assertEquals("helloA.f", out.toString());
    }

    @Test
    public void testWrite1()
            throws IOException {
        OutputStreamNibbleOut out = new OutputStreamNibbleOut(new NibByteArrayOutputStream());

        assertEquals("", out.toString());

        out.write4b(3);
        assertEquals(".3", out.toString());

        out.write4b(7);
        assertEquals("37", out.toString());
    }

    @Test
    public void testWriteAligned()
            throws IOException {
        OutputStreamNibbleOut out = new OutputStreamNibbleOut(new NibByteArrayOutputStream());
        out.write4b(seq, 0, 2);
        assertEquals("01", out.toString());

        out.write4b(2);
        assertEquals("01.2", out.toString());

        out.write4b(2);
        assertEquals("0122", out.toString());

        out.write4b(seq, 6, 4);
        assertEquals("0122 6789", out.toString());
    }

    @Test
    public void testWriteNonAligned()
            throws IOException {
        OutputStreamNibbleOut out = new OutputStreamNibbleOut(new NibByteArrayOutputStream());
        out.write4b(seq, 0, 3);
        assertEquals("01.2", out.toString());

        out.write4b(seq, 3, 6);
        assertEquals("0123 4567.8", out.toString());

        out.write4b(seq, 4, 3);
        assertEquals("0123 4567 8456", out.toString());

        out.write4b(seq, 4, 3);
        assertEquals("0123 4567 8456 45.6", out.toString());
    }

}
