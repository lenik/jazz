package net.bodz.bas.t.buffer;

import org.junit.Assert;
import org.junit.Test;

public class MovableByteBufferTest
        extends Assert {

    byte[] sample = "0123456789abcdefghij".getBytes();

    @Test
    public void testByteAt() {
        MovableByteBuffer buf = new MovableByteBuffer(sample, 3, 5);
        assertEquals((byte) '7', buf.byteAt(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testByteAtIoobe() {
        MovableByteBuffer buf = new MovableByteBuffer(sample, 3, 5);
        assertEquals((byte) '7', buf.byteAt(5));
    }

    @Test
    public void testResizeToSmaller() {
        MovableByteBuffer buf = new MovableByteBuffer(sample, 3, 5);
        buf.resize(4);
        byte[] newBytes = buf.getArray();
        assertSame(sample, newBytes);
    }

    @Test
    public void testCapacityAlign() {
        MovableByteBuffer buf = new MovableByteBuffer(sample, 3, 5);
        // buf.resize(0b11010010);
        buf.resize(0xD2);
        int capacity = buf.capacity();
        // assertEquals(0b1_0000_0000, capacity);
        assertEquals(0x100, capacity);
    }

    public static void main(String[] args) {
        MovableByteBuffer buf = new MovableByteBuffer(0, 0);
        for (int i = 0; i < 100; i++) {
            buf.ensureSize(i);
            System.out.println(buf.capacity());
        }
    }

}
