package net.bodz.bas.io.resource.preparation;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import net.bodz.bas.collection.iterator.IteratorMX;
import net.bodz.bas.io.resource.builtin.InputStreamSource;

import org.junit.Assert;
import org.junit.Test;

public class StreamReadPreparationTest
        extends Assert {

    @Test
    public void testReadByBlock()
            throws Exception {
        byte[] src = "0123456789abcdefghijklmnopqrstuvwxyz".getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(src);
        IStreamReadPreparation read = new InputStreamSource(in).forRead();

        IteratorMX<char[], ? extends IOException> blocks = read.charBlocks(true);
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
