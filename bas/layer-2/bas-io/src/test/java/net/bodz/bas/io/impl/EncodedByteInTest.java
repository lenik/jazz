package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Test;

import net.bodz.bas.io.BByteOut;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.StringCharIn;

public class EncodedByteInTest
        extends CodecIOTestCase {

    EncodedByteIn in;

    byte[] conv(String string, Charset charset)
            throws IOException {
        StringCharIn cin = new StringCharIn(string);
        in = new EncodedByteIn(cin, charset);

        BByteOut buf = new BByteOut();
        IByteOut.fn.dump(buf, in);

        return buf.toByteArray();
    }

    void convByChunk(String string, Charset charset, int chunkSize, int processCount)
            throws IOException {
        TranscodeConfig.chunkSize = chunkSize;

        byte[] expected = string.getBytes(charset);
        byte[] actual = conv(string, charset);

        assertArrayEquals(expected, actual);
        assertEquals(processCount, in.__chunks);
    }

    @Test
    public void testEncodeAscii()
            throws IOException {
        convByChunk("Hello, world!", ASCII, 100, 1);
    }

    @Test
    public void testEncodeAsciiEmpty()
            throws IOException {
        convByChunk("", ASCII, 100, 0);
    }

    @Test
    public void testEncodeAsciiSmallChunk()
            throws IOException {
        convByChunk("12345678", ASCII, 4, 2);
    }

    @Test
    public void testEncodeAsciiAtomChunk()
            throws IOException {
        String string = "hello, world";
        convByChunk(string, ASCII, 1, string.length());
    }

    @Test
    public void testEncodeUtf8()
            throws IOException {
        convByChunk("你好", UTF8, 100, 1);
    }

    @Test
    public void testEncodeUtf8SmallChunkAligned()
            throws IOException {
        convByChunk("七个字符的长度", UTF8, 3, 3); // 七个字.符的长.度
    }

    @Test
    public void testEncodeUtf8SmallChunkMixed()
            throws IOException {
        convByChunk("你好,世界", UTF8, 3, 2);
    }

    @Test
    public void testEncodeUtf8AtomChunk()
            throws IOException {
        String string = "你好,世界";
        convByChunk(string, UTF8, 1, string.length());
    }

}
