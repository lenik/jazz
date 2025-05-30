package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.meta.decl.NotNull;

/**
 * The chars written to this {@link ICharOut}, is encoded into a {@link IByteOut}.
 */
public class EncodedCharOut
        implements ICharOut {

    private final IByteOut byteOut;
    private final CharsetEncoder encoder;

    /**
     * The encode input buffer
     */
    private final CharBuffer charBuffer;

    /**
     * The encode output buffer
     */
    private final ByteBuffer byteBuffer;

    int __chunks;

    public EncodedCharOut(IByteOut byteOut, String charsetName) {
        this(byteOut, Charset.forName(charsetName));
    }

    public EncodedCharOut(IByteOut byteOut, Charset charset) {
        this(byteOut, charset.newEncoder());
    }

    public EncodedCharOut(IByteOut byteOut, CharsetEncoder encoder) {
        if (byteOut == null)
            throw new NullPointerException("byteOut");
        if (encoder == null)
            throw new NullPointerException("encoder");
        this.byteOut = byteOut;
        this.encoder = encoder;
        this.charBuffer = TranscodeConfig.allocateEncodeInputBuffer();
        this.byteBuffer = TranscodeConfig.allocateOutputBuffer(encoder);
    }

    @Override
    public void writeChar(int ch)
            throws IOException {
        // charBuffer: ****|______________| byteBuffer: ****|________|
        charBuffer.put((char) ch);
        conv(false);
        __chunks++;
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len)
            throws IOException {
        while (len > 0) {
            int chunk = Math.min(len, charBuffer.remaining());
            charBuffer.put(chars, off, chunk);
            off += chunk;
            len -= chunk;
            conv(false);
            __chunks++;
        }
    }

    @Override
    public void flush()
            throws IOException {
        conv(true);
    }

    @Override
    public void close()
            throws IOException {
        flush();
    }

    private void conv(boolean force)
            throws IOException {
        charBuffer.flip();
        CoderResult result = encoder.encode(charBuffer, byteBuffer, false);
        charBuffer.compact();

        if (result.isError()) {
            int pos = charBuffer.position();
            char errorChar = charBuffer.get(pos);
            errorChar = '?';
            charBuffer.put(pos, errorChar);
        }

        byteBuffer.flip();
        byteOut.write(byteBuffer);
        byteBuffer.clear();
    }

}
