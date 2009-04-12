package net.bodz.bas.io;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

public class ReaderInputStreamTest {

    @Test
    public void testRead() throws IOException {
        // E4 BD A0 E5 A5 BD
        String s = "abc你好"; //$NON-NLS-1$
        Reader reader = new StringReader(s);
        ReaderInputStream in = new ReaderInputStream(reader, "utf-8"); //$NON-NLS-1$

        assertEquals((int) 'a', in.read());
        assertEquals((int) 'b', in.read());
        assertEquals((int) 'c', in.read());

        assertEquals(0xE4, in.read());
        assertEquals(0xBD, in.read());
        assertEquals(0xA0, in.read());
        assertEquals(0xE5, in.read());
        assertEquals(0xA5, in.read());
        assertEquals(0xBD, in.read());
    }

}
