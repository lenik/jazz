package net.bodz.bas.io.res.tools;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.res.builtin.InputStreamSource;
import net.bodz.bas.t.iterator.immed.Mitorx;

public class StreamReadingTest
        extends Assert {

    @Test
    public void testReadByBlock()
            throws Exception {
        byte[] src = "0123456789abcdefghijklmnopqrstuvwxyz".getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(src);
        StreamReading reading = new InputStreamSource(in).to(StreamReading.class);
        reading.blockSize(5);

        Mitorx<char[], ? extends IOException> blocks = reading.charBlocks(true);
        assertEquals("01234", new String(blocks._next()));
        assertEquals("56789", new String(blocks._next()));
        assertEquals("abcde", new String(blocks._next()));
        assertEquals("fghij", new String(blocks._next()));
        assertEquals("klmno", new String(blocks._next()));
        assertEquals("pqrst", new String(blocks._next()));
        assertEquals("uvwxy", new String(blocks._next()));
        assertEquals("z", new String(blocks._next()));
    }

}
