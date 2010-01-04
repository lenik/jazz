package net.bodz.bas.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

public class ConcatReaderTest {

    ConcatReader read(String... sv) {
        StringReader[] rv = new StringReader[sv.length];
        for (int i = 0; i < sv.length; i++)
            rv[i] = new StringReader(sv[i]);
        return new ConcatReader(rv);
    }

    String read(int n, Reader reader) throws IOException {
        char[] buf = new char[n];
        int read = reader.read(buf);
        if (read == -1)
            return "EOF"; //$NON-NLS-1$
        return new String(buf, 0, read);
    }

    String[] data = { "hello", "", "world", "" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

    @Test
    public void testRead() throws Exception {
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
    public void testFastRead1() throws Exception {
        ConcatReader cr = read(data);
        assertEquals("h", read(1, cr)); //$NON-NLS-1$
        assertEquals("e", read(1, cr)); //$NON-NLS-1$
        assertEquals("l", read(1, cr)); //$NON-NLS-1$
        assertEquals("l", read(1, cr)); //$NON-NLS-1$
        assertEquals("o", read(1, cr)); //$NON-NLS-1$
        assertEquals("w", read(1, cr)); //$NON-NLS-1$
        assertEquals("o", read(1, cr)); //$NON-NLS-1$
        assertEquals("r", read(1, cr)); //$NON-NLS-1$
        assertEquals("l", read(1, cr)); //$NON-NLS-1$
        assertEquals("d", read(1, cr)); //$NON-NLS-1$
        assertEquals("", read(1, cr)); //$NON-NLS-1$
        assertEquals("EOF", read(1, cr)); //$NON-NLS-1$
    }

    @Test
    public void testFastRead2() throws Exception {
        ConcatReader cr = read(data);
        assertEquals("he", read(2, cr)); //$NON-NLS-1$
        assertEquals("ll", read(2, cr)); //$NON-NLS-1$
        assertEquals("o", read(2, cr)); //$NON-NLS-1$
        assertEquals("wo", read(2, cr)); //$NON-NLS-1$
        assertEquals("rl", read(2, cr)); //$NON-NLS-1$
        assertEquals("d", read(2, cr)); //$NON-NLS-1$
        assertEquals("", read(2, cr)); //$NON-NLS-1$
        assertEquals("EOF", read(2, cr)); //$NON-NLS-1$
    }

    @Test
    public void testFastRead3() throws Exception {
        ConcatReader cr = read(data);
        assertEquals("hel", read(3, cr)); //$NON-NLS-1$
        assertEquals("lo", read(3, cr)); //$NON-NLS-1$
        assertEquals("wor", read(3, cr)); //$NON-NLS-1$
        assertEquals("ld", read(3, cr)); //$NON-NLS-1$
        assertEquals("", read(3, cr)); //$NON-NLS-1$
        assertEquals("EOF", read(3, cr)); //$NON-NLS-1$
    }

    @Test
    public void testFastRead4() throws Exception {
        ConcatReader cr = read(data);
        assertEquals("hell", read(4, cr)); //$NON-NLS-1$
        assertEquals("o", read(4, cr)); //$NON-NLS-1$
        assertEquals("worl", read(4, cr)); //$NON-NLS-1$
        assertEquals("d", read(4, cr)); //$NON-NLS-1$
        assertEquals("", read(4, cr)); //$NON-NLS-1$
        assertEquals("EOF", read(4, cr)); //$NON-NLS-1$
    }

}
