package net.bodz.bas.collection.buffer;

import org.junit.Assert;
import org.junit.Test;

@Deprecated
public class CharRingTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        CharRing cr = new CharRing("hello");
        assertTrue(cr.isFull());
        assertEquals('h', cr.readChar());
        assertEquals('e', cr.readChar());
        assertEquals('l', cr.readChar());
        assertEquals('l', cr.readChar());
        assertEquals('o', cr.readChar());
        assertTrue(cr.isEmpty());
        cr.write("world");
        assertEquals('w', cr.readChar());
        assertEquals('o', cr.readChar());
        cr.write("xy");
        assertEquals('r', cr.readChar());
        assertEquals('l', cr.readChar());
        assertEquals('d', cr.readChar());
        assertEquals('x', cr.readChar());
        assertEquals('y', cr.readChar());
        assertTrue(cr.isEmpty());
        cr.write("hello");
        assertEquals("ll", cr.toString(2, 2));
        assertEquals("hel", cr.readString(3));
        cr.write("xyz");
        assertEquals("lox", cr.readString(3));
        cr.write("123");
        assertEquals("yz123", cr.toString());
        assertEquals('2', (char) cr.remove(3));
        assertEquals('y', (char) cr.remove(0));
        assertEquals("z13", cr.toString());
    }

}
