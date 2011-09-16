package net.bodz.bas.sio.util;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.charset.Charset;

import net.bodz.bas.sio.BByteIn;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.util.DecodedCharIn;
import net.bodz.bas.sio.util.TranscodeConfig;

import org.junit.Assert;
import org.junit.Test;

public class DecodedCharInTest
        extends Assert {

    static final Charset ASCII = Charset.forName("ASCII");
    static final Charset UTF8 = Charset.forName("UTF8");

    DecodedCharIn in;

    String rconv(byte[] bytes, Charset charset)
            throws IOException {
        BByteIn bin = new BByteIn(bytes);
        in = new DecodedCharIn(bin, charset);

        BCharOut buffer = new BCharOut();
        buffer.dump(in);

        return buffer.toString();
    }

    void rconvByChunk(String string, Charset charset, int chunkSize, int processCount)
            throws IOException {
        TranscodeConfig.chunkSize = chunkSize;

        byte[] bytes = string.getBytes(charset);
        String actual = rconv(bytes, charset);

        assertEquals(string, actual);
        assertEquals(processCount, in.__chunks);
    }

    @Test
    public void testDecodeAscii()
            throws IOException {
        rconvByChunk("Hello, world!", ASCII, 100, 1);
    }

    @Test
    public void testDecodeAsciiEmpty()
            throws IOException {
        rconvByChunk("", ASCII, 100, 0);
    }

    @Test
    public void testDecodeAsciiSmallChunk()
            throws IOException {
        rconvByChunk("12345678", ASCII, 4, 2);
    }

    @Test
    public void testDecodeAsciiAtomChunk()
            throws IOException {
        String string = "hello, world";
        rconvByChunk(string, ASCII, 1, string.length());
    }

    @Test
    public void testDecodeUtf8()
            throws IOException {
        rconvByChunk("你好", UTF8, 100, 1);
    }

    @Test
    public void testDecodeUtf8SmallChunkAligned()
            throws IOException {
        rconvByChunk("七个字符的长度", UTF8, 3, 7);
    }

    @Test
    public void testDecodeUtf8SmallChunkMixed()
            throws IOException {
        rconvByChunk("你好,世界", UTF8, 3, 5);
    }

    @Test(expected = BufferUnderflowException.class)
    public void testDecodeUtf8AtomChunk()
            throws IOException {
        String string = "你好,世界";
        int bytes = string.getBytes(UTF8).length;
        rconvByChunk(string, UTF8, 1, bytes);
    }

}
