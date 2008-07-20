package net.bodz.bas.types.buf;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.types.buf.Buffer.CharBuffer;

import org.junit.Test;

public class BufferTest {

    @Test
    public void test1() {
        CharBuffer buf = new CharBuffer(6, 8);
        assertEquals("", buf.toString());
        buf.append("world");
        assertEquals("world", buf.toString());
        assertEquals(5, buf.size());
        assertEquals(8, buf.capacity());
        buf.prepend("hello");
        assertEquals("helloworld", buf.toString());
        assertEquals(10, buf.size());
        assertEquals(16, buf.capacity());
        buf.insert(3, "--");
        assertEquals("hel--loworld", buf.toString());
        buf.delete(0, 10);
        assertEquals("ld", buf.toString());
        buf.compact();
        assertEquals("ld", buf.toString());
        assertEquals(2, buf.size());
        assertEquals(2, buf.capacity());
        buf.append("out");
        assertEquals("ldout", buf.toString());
        assertEquals(5, buf.size());
        assertEquals(8, buf.capacity());
    }

    public static void main(String[] args) {
        new BufferTest().test1();
    }

}
