package net.bodz.bas.sio.util;

import java.io.IOException;

import junit.framework.TestCase;
import net.bodz.bas.sio.nibbles.BNibbleIn;
import net.bodz.bas.sio.nibbles.INibbleIn;

import org.junit.Test;

public class BNibbleInTest
        extends TestCase {

    static final byte[] seq = { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef };

    static INibbleIn getIn() {
        BNibbleIn bin = new BNibbleIn(seq, 0, 15);
        return bin;
    }

    @Test
    public void testReadByNibbles()
            throws IOException {
        INibbleIn in = getIn();
        for (int off = 0; off < 15; off++)
            assertEquals(off, in.read4b());
        assertEquals(-1, in.read4b());
        in.close();
    }

    @Test
    public void testReadBy2Nibbles()
            throws IOException {
        INibbleIn in = getIn();
        byte[] nib2 = new byte[1];
        for (int off = 0; off <= 13; off += 2) {
            assertEquals(2, in.read4b(nib2));
            int val1 = off;
            int val2 = off + 1;
            int val = (val1 << 4) | val2;
            assertEquals(val, nib2[0] & 0xff);
        }
        // nib2 remains: cd
        assertEquals(1, in.read4b(nib2));
        int val1 = 14;
        int val2 = 0x0d;
        int val = (val1 << 4) | val2;
        assertEquals(val, nib2[0] & 0xff);
        in.close();
    }

    @Test
    public void testReadBy3Nibbles()
            throws IOException {
        INibbleIn in = getIn();
        byte[] nib3 = new byte[2];
        for (int off = 0; off <= 12; off += 3) {
            assertEquals(3, in.read4b(nib3, 0, 3));
            int val1 = off;
            int val2 = off + 1;
            int val3 = off + 2;
            int byt1 = (val1 << 4) | val2;
            int byt2 = val3 << 4;
            assertEquals(byt1, nib3[0] & 0xff);
            assertEquals(byt2, nib3[1] & 0xff);
        }
        assertEquals(-1, in.read4b(nib3, 0, 3));
        in.close();
    }

    @Test
    public void testReadByRandomNibbles()
            throws IOException {
        INibbleIn in = getIn();
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
        assertEquals(3, in.read4b(buf, 3, 100)); // 04 7c de
        assertEquals(0x7c, buf[1] & 0xff);
        assertEquals(0xde, buf[2] & 0xff);

        assertEquals(-1, in.read4b());
        in.close();
    }

}
