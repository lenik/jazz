package net.bodz.art.obfuz.seals;

import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.junit.Test;

public class RandomSequenceTest {

    static final boolean littleEndian = true;

    @Test
    public void testGetBytes() {
        RandomSequence seq = new RandomSequence(123);

        int n = seq.next();
        System.out.println(n);
        assertEquals(-1188957731, n);

        seq.reset();
        ByteBuffer buf = ByteBuffer.allocate(10);
        seq.next(buf);
        buf.flip();

        IntBuffer intbuf = buf.asIntBuffer();
        int n1 = intbuf.get();
        if (littleEndian) {
            int n1rev = 0;
            for (int i = 0; i < 4; i++) {
                n1rev <<= 8;
                n1rev |= n1 & 0xff;
                n1 >>= 8;
            }
            assertEquals(n, n1rev);
        } else {
            assertEquals(n, n1);
        }
    }

}
