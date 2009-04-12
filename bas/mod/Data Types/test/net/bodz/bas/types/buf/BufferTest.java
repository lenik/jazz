package net.bodz.bas.types.buf;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.types.buf.Buffer.CharBuffer;

import org.junit.Test;

public class BufferTest {

    @Test
    public void test1() {
        CharBuffer buf = new CharBuffer(6, 8);
        assertEquals("", buf.toString()); //$NON-NLS-1$
        buf.append("world"); //$NON-NLS-1$
        assertEquals("world", buf.toString()); //$NON-NLS-1$
        assertEquals(5, buf.size());
        assertEquals(8, buf.capacity());
        buf.prepend("hello"); //$NON-NLS-1$
        assertEquals("helloworld", buf.toString()); //$NON-NLS-1$
        assertEquals(10, buf.size());
        assertEquals(16, buf.capacity());
        buf.insert(3, "--"); //$NON-NLS-1$
        assertEquals("hel--loworld", buf.toString()); //$NON-NLS-1$
        buf.delete(0, 10);
        assertEquals("ld", buf.toString()); //$NON-NLS-1$
        buf.compact();
        assertEquals("ld", buf.toString()); //$NON-NLS-1$
        assertEquals(2, buf.size());
        assertEquals(2, buf.capacity());
        buf.append("out"); //$NON-NLS-1$
        assertEquals("ldout", buf.toString()); //$NON-NLS-1$
        assertEquals(5, buf.size());
        assertEquals(8, buf.capacity());
    }

    public static void main(String[] args) {
        new BufferTest().test1();
    }

}
