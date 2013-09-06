package net.bodz.bas.c.java.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.io.ConcatReader;

public class ConcatReaderTest
        extends Assert {

    ConcatReader read(String... sv) {
        StringReader[] rv = new StringReader[sv.length];
        for (int i = 0; i < sv.length; i++)
            rv[i] = new StringReader(sv[i]);
        return new ConcatReader(rv);
    }

    String read(int n, Reader reader)
            throws IOException {
        char[] buf = new char[n];
        int read = reader.read(buf);
        if (read == -1)
            return "EOF";
        return new String(buf, 0, read);
    }

    String[] data = { "hello", "", "world", "" };

    @Test
    public void testRead()
            throws Exception {
        ConcatReader cr = read(data);
        assertTrue(cr.ready());
        assertEquals('h', cr.read());
        assertEquals('e', cr.read());
        assertEquals('l', cr.read());
        assertEquals('l', cr.read());
        assertEquals('o', cr.read());
        assertEquals('w', cr.read());
        assertEquals('o', cr.read());
        assertEquals('r', cr.read());
        assertEquals('l', cr.read());
        assertEquals('d', cr.read());
        assertEquals(-1, cr.read());
        assertFalse(cr.ready());

    }

    @Test
    public void testFastRead1()
            throws Exception {
        ConcatReader cr = read(data);
        assertEquals("h", read(1, cr));
        assertEquals("e", read(1, cr));
        assertEquals("l", read(1, cr));
        assertEquals("l", read(1, cr));
        assertEquals("o", read(1, cr));
        assertEquals("w", read(1, cr));
        assertEquals("o", read(1, cr));
        assertEquals("r", read(1, cr));
        assertEquals("l", read(1, cr));
        assertEquals("d", read(1, cr));
        assertEquals("", read(1, cr));
        assertEquals("EOF", read(1, cr));
    }

    @Test
    public void testFastRead2()
            throws Exception {
        ConcatReader cr = read(data);
        assertEquals("he", read(2, cr));
        assertEquals("ll", read(2, cr));
        assertEquals("o", read(2, cr));
        assertEquals("wo", read(2, cr));
        assertEquals("rl", read(2, cr));
        assertEquals("d", read(2, cr));
        assertEquals("", read(2, cr));
        assertEquals("EOF", read(2, cr));
    }

    @Test
    public void testFastRead3()
            throws Exception {
        ConcatReader cr = read(data);
        assertEquals("hel", read(3, cr));
        assertEquals("lo", read(3, cr));
        assertEquals("wor", read(3, cr));
        assertEquals("ld", read(3, cr));
        assertEquals("", read(3, cr));
        assertEquals("EOF", read(3, cr));
    }

    @Test
    public void testFastRead4()
            throws Exception {
        ConcatReader cr = read(data);
        assertEquals("hell", read(4, cr));
        assertEquals("o", read(4, cr));
        assertEquals("worl", read(4, cr));
        assertEquals("d", read(4, cr));
        assertEquals("", read(4, cr));
        assertEquals("EOF", read(4, cr));
    }

}
