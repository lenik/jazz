package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.io.AbstractByteOut;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharOut;

/**
 * The bytes written to this {@link IByteOut}, is decoded into a {@link ICharOut}.
 */
public class DecodedByteOut
        extends AbstractByteOut {

    private final ICharOut charOut;
    private final CharsetDecoder decoder;

    /** The encode input buffer */
    private ByteBuffer byteBuffer;
    /** The encode output buffer */
    private CharBuffer charBuffer;

    int __chunks;

    public DecodedByteOut(ICharOut charOut, String charsetName) {
        this(charOut, Charset.forName(charsetName));
    }

    public DecodedByteOut(ICharOut charOut, Charset charset) {
        this(charOut, charset.newDecoder());
    }

    public DecodedByteOut(ICharOut charOut, CharsetDecoder decoder) {
        if (charOut == null)
            throw new NullPointerException("charOut");
        if (decoder == null)
            throw new NullPointerException("decoder");
        this.charOut = charOut;
        this.decoder = decoder;
        this.byteBuffer = TranscodeConfig.allocateDecodeInputBuffer();
        this.charBuffer = TranscodeConfig.allocateOutputBuffer(decoder);
    }

    @Override
    public void write(int byt)
            throws IOException {
        byteBuffer.put((byte) byt);
        conv(false);
        __chunks++;
    }

    @Override
    public void write(byte[] bytes, int off, int len)
            throws IOException {
        while (len > 0) {
            if (!byteBuffer.hasRemaining()) {
                // Decode buffer is too small.
                // Needs to read more bytes to decode the next char.
                throw new BufferUnderflowException();
            }
            int chunk = Math.min(len, byteBuffer.remaining());
            byteBuffer.put(bytes, off, chunk);
            off += chunk;
            len -= chunk;
            conv(false);
            __chunks++;
        }
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
        conv(true);
    }

    void conv(boolean force)
            throws IOException {
        byteBuffer.flip();
        CoderResult result = decoder.decode(byteBuffer, charBuffer, force);
        byteBuffer.compact();

        if (result.isError()) {
            byte errorByte = byteBuffer.get();
            char errorChar = (char) (errorByte & 0xff);
            charOut.write(errorChar);
        }

        charBuffer.flip();
        charOut.write(charBuffer);
        charBuffer.clear();
    }

}
