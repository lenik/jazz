package net.bodz.bas.c.java.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

public class ReaderInputStreamTest
        extends Assert {

    @Test
    public void testRead()
            throws IOException {
        // E4 BD A0 E5 A5 BD
        String s = "abc你好";
        Reader reader = new StringReader(s);
        ReaderInputStream in = new ReaderInputStream(reader, "utf-8");
        try {
            assertEquals('a', in.read());
            assertEquals('b', in.read());
            assertEquals('c', in.read());

            assertEquals(0xE4, in.read());
            assertEquals(0xBD, in.read());
            assertEquals(0xA0, in.read());
            assertEquals(0xE5, in.read());
            assertEquals(0xA5, in.read());
            assertEquals(0xBD, in.read());
        } finally {
            reader.close();
        }
    }

}
