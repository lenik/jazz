package net.bodz.bas.sio;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.sio.nibbles.BNibbleIn;
import net.bodz.bas.sio.nibbles.LANibbleIn;

public class LANibbleInTest
        extends Assert {

    static final byte[] seq = { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef };

    static LANibbleIn getIn() {
        BNibbleIn bin = new BNibbleIn(seq);
        LANibbleIn in = new LANibbleIn(bin);
        return in;
    }

    @Test
    public void testLAOneByOne()
            throws IOException {
        LANibbleIn in = getIn();
        for (int off = 0; off < 0x10; off++) {
            assertFalse(in.isBuffered());

            assertEquals(off, in.look4b());
            assertTrue(in.isBuffered());

            assertEquals(off, in.look4b());
            assertTrue(in.isBuffered());

            assertEquals(off, in.read4b());
            assertFalse(in.isBuffered());
        }

        assertEquals(-1, in.read4b());
        in.close();
    }

    @Test
    public void testLAOneByTwo()
            throws IOException {
        LANibbleIn in = getIn();
        byte[] nib2 = new byte[1];

        for (int off = 0; off <= 14; off += 2) {
            assertFalse(in.isBuffered());

            assertEquals(off, in.look4b());
            assertTrue(in.isBuffered());

            assertEquals(2, in.read4b(nib2));
            assertFalse(in.isBuffered());
            int val1 = off;
            int val2 = off + 1;
            int val = (val1 << 4) | val2;
            assertEquals(val, nib2[0] & 0xff);
        }
        assertEquals(-1, in.read4b());
        in.close();
    }

}
