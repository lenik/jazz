package net.bodz.bas.sio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.sio.bits.InputStreamNibbleIn;

import org.junit.Assert;
import org.junit.Test;

public class InputStreamNibbleInTest
        extends Assert {

    static final byte[] seq = { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef };

    @Test
    public void testReadByNibbles()
            throws IOException {
        InputStream bin = new ByteArrayInputStream(seq);
        InputStreamNibbleIn in = new InputStreamNibbleIn(bin);
        for (int off = 0; off < 0x10; off++)
            assertEquals(off, in.read4b());
        assertEquals(-1, in.read4b());
        in.close();
    }

    @Test
    public void testReadBy2Nibbles()
            throws IOException {
        InputStream bin = new ByteArrayInputStream(seq);
        InputStreamNibbleIn in = new InputStreamNibbleIn(bin);
        byte[] nib2 = new byte[1];
        for (int off = 0; off < 0x10; off += 2) {
            assertEquals(2, in.read4b(nib2));
            int val1 = off;
            int val2 = off + 1;
            int val = (val1 << 4) | val2;
            assertEquals(val, nib2[0] & 0xff);
        }
        in.close();
    }

    @Test
    public void testReadBy3Nibbles()
            throws IOException {
        InputStream bin = new ByteArrayInputStream(seq);
        InputStreamNibbleIn in = new InputStreamNibbleIn(bin);
        byte[] nib3 = new byte[2];
        for (int off = 0; off < 15; off += 3) {
            assertEquals(3, in.read4b(nib3, 0, 3));
            int val1 = off;
            int val2 = off + 1;
            int val3 = off + 2;
            int byt1 = (val1 << 4) | val2;
            int byt2 = val3 << 4;
            assertEquals(byt1, nib3[0] & 0xff);
            assertEquals(byt2, nib3[1] & 0xff);
        }
        // remains 1 nibble.
        // nib3 = cd e0, before the read.
        assertEquals(1, in.read4b(nib3, 0, 3));
        assertEquals(0xfd, nib3[0] & 0xff);
        assertEquals(0xe0, nib3[1] & 0xff);
        in.close();
    }

    @Test
    public void testReadByRandomNibbles()
            throws IOException {
        InputStream bin = new ByteArrayInputStream(seq);
        InputStreamNibbleIn in = new InputStreamNibbleIn(bin);
        byte[] buf = new byte[16];
        assertEquals(0, in.read4b());

        assertEquals(2, in.read4b(buf, 1, 2)); // 01 20
        assertEquals(0x01, buf[0] & 0xff);
        assertEquals(0x20, buf[1] & 0xff);

        assertEquals(3, in.read4b());

        assertEquals(3, in.read4b(buf, 1, 3)); // 04 56
        assertEquals(0x04, buf[0] & 0xff);
        assertEquals(0x56, buf[1] & 0xff);

        assertEquals(4, in.read4b(buf, 2, 4)); // 04 78 9a
        assertEquals(0x78, buf[1] & 0xff);
        assertEquals(0x9a, buf[2] & 0xff);

        assertEquals(0x0b, in.read4b());
        assertEquals(4, in.read4b(buf, 3, 100)); // 04 7c de f
        assertEquals(0x7c, buf[1] & 0xff);
        assertEquals(0xde, buf[2] & 0xff);
        assertEquals(0xf0, buf[3] & 0xff);

        assertEquals(-1, in.read4b());
        in.close();
    }

}
