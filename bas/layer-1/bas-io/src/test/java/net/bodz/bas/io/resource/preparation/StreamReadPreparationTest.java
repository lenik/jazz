package net.bodz.bas.io.resource.preparation;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.io.resource.builtin.InputStreamSource;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;

import org.junit.Test;

public class StreamReadPreparationTest {

    @Test
    public void testReadByBlock()
            throws Exception {
        byte[] src = "0123456789abcdefghijklmnopqrstuvwxyz".getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(src);
        IStreamReadPreparation read = new InputStreamSource(in).forRead();

        ImmediateIteratorX<char[], ? extends IOException> blocks = read.charBlocks(true);
        assertEquals("01234", new String(blocks.next()));
        assertEquals("56789", new String(blocks.next()));
        assertEquals("abcde", new String(blocks.next()));
        assertEquals("fghij", new String(blocks.next()));
        assertEquals("klmno", new String(blocks.next()));
        assertEquals("pqrst", new String(blocks.next()));
        assertEquals("uvwxy", new String(blocks.next()));
        assertEquals("z", new String(blocks.next()));
    }

}
