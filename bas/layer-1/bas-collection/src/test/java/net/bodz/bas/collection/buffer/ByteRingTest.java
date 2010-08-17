package net.bodz.bas.collection.buffer;

import junit.framework.TestCase;

import org.junit.Test;

public class ByteRingTest
        extends TestCase {

    @Test
    public void test1()
            throws Exception {
        ByteRing br = new ByteRing("hello".getBytes());
        assertTrue(br.isFull());
        assertEquals('h', br.readByte());
        assertEquals('e', br.readByte());
        assertEquals('l', br.readByte());
        assertEquals('l', br.readByte());
        assertEquals('o', br.readByte());
        assertTrue(br.isEmpty());
        br.write("world".getBytes(), 0, 5);
        assertEquals('w', br.readByte());
        assertEquals('o', br.readByte());
        br.write("xy".getBytes(), 0, 2);
        assertEquals('r', br.readByte());
        assertEquals('l', br.readByte());
        assertEquals('d', br.readByte());
        assertEquals('x', br.readByte());
        assertEquals('y', br.readByte());
        assertTrue(br.isEmpty());
        br.write("hello".getBytes(), 0, 5);
        assertEquals("ll", br.toString(2, 2));
        assertEquals("hel", br.readString(3));
        br.write("xyz".getBytes(), 0, 3);
        assertEquals("lox", br.readString(3));
        br.write("123".getBytes(), 0, 3);
        assertEquals("yz123", br.toString());
        assertEquals((byte) '2', (byte) br.remove(3));
        assertEquals((byte) 'y', (byte) br.remove(0));
        assertEquals("z13", br.toString());
    }

}
