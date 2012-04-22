package net.bodz.art.obfuz.seals;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;

import net.bodz.art.obfuz.seals.AccumEntropy;

import org.junit.Test;

public class AccumEntropyTest {

    @Test
    public void testGet() {
        AccumEntropy e = new AccumEntropy(4, 17);
        e.drop((byte) 100);
        e.drop((byte) 100);
        e.drop((byte) 100);
        e.drop((byte) 100);
        byte[] pool = e.getPool();
        byte[] sample = { 16, -9, 7, 0 };
        assertArrayEquals(sample, pool);
        int len = 10;
        ByteBuffer buf = ByteBuffer.allocate(len);
        e.get(buf);
        int n = buf.remaining();
        assertEquals(n, 0);
        buf.flip();
        assertEquals(len, buf.remaining());
        byte[] wraps = { 16, -9, 7, 0, 16, -9, 7, 0, 16, -9, };
        byte[] array = buf.array();
        assertArrayEquals(wraps, array);
    }

    @Test
    public void testGetInt() {
        AccumEntropy e = new AccumEntropy(4, 17);
        assertEquals(0, e.getInt());
        e.drop((byte) 1);
        assertEquals(1, e.getInt());
        e.drop((byte) 1);
        assertEquals(18, e.getInt());
        e.drop((byte) 1);
        assertEquals(307, e.getInt());
        e.drop((byte) 1);
        assertEquals(5220, e.getInt());
        e.drop((byte) 10);
        assertEquals(88750, e.getInt());
        e.drop((byte) 10);
        assertEquals(1508760, e.getInt());
        e.drop((byte) 10);
        assertEquals(25648930, e.getInt());
        e.drop((byte) 1);
        assertEquals(436031811, e.getInt());
        e.drop((byte) 1);
        assertEquals(-1177393804, e.getInt());
        e.drop((byte) 1);
        assertEquals(1459141813, e.getInt());
        e.drop((byte) 1);
        assertEquals(-964392954, e.getInt());
    }

}
