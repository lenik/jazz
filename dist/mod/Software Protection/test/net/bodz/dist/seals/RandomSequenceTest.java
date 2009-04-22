package net.bodz.dist.seals;

import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import net.bodz.bas.lang.err.NotImplementedException;

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
        ByteBuffer bb = ByteBuffer.allocate(10);
        seq.next(bb);
        IntBuffer ib = bb.asIntBuffer();
        int n1 = ib.get();
        if (littleEndian) {
            assertEquals(n, n1);
        } else {
            throw new NotImplementedException("big-endian");
        }
    }

}
