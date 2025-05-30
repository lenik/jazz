package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.meta.decl.NotNull;

/**
 * The chars read from this {@link ICharIn}, is decoded from a {@link IByteIn}.
 */
public class DecodedCharIn
        implements ICharIn {

    private final IByteIn byteIn;
    private final CharsetDecoder decoder;

    /**
     * The encode input buffer
     */
    private final ByteBuffer byteBuffer;
    /**
     * The encode output buffer
     */
    private final CharBuffer charBuffer;

    int __chunks;

    public DecodedCharIn(IByteIn byteIn, String charsetName) {
        this(byteIn, Charset.forName(charsetName));
    }

    /**
     * @def ~ctor(byteIn, charset.newDecoder())
     */
    public DecodedCharIn(IByteIn byteIn, Charset charset) {
        this(byteIn, charset.newDecoder());
    }

    public DecodedCharIn(IByteIn byteIn, CharsetDecoder decoder) {
        if (byteIn == null)
            throw new NullPointerException("byteIn");
        if (decoder == null)
            throw new NullPointerException("decoder");
        this.byteIn = byteIn;
        this.decoder = decoder;
        this.byteBuffer = TranscodeConfig.allocateDecodeInputBuffer();
        this.charBuffer = TranscodeConfig.allocateOutputBuffer(decoder);
        charBuffer.flip();
    }

    @Override
    public int read()
            throws IOException {
        if (!charBuffer.hasRemaining())
            if (!prefetch())
                return -1;
        char ch = charBuffer.get();
        return ch;
    }

    @Override
    public int read(@NotNull char[] buf, int off, int len)
            throws IOException {
        int pos = off;
        while (len > 0) {
            if (!charBuffer.hasRemaining())
                if (!prefetch())
                    return pos == off ? -1 : pos - off;
            int block = Math.min(len, charBuffer.remaining());
            charBuffer.get(buf, pos, block);
            pos += block;
            len -= block;
        }
        return pos - off;
    }

    boolean prefetch()
            throws IOException {
        if (!byteBuffer.hasRemaining())
            throw new BufferUnderflowException();
        int cb = byteIn.read(byteBuffer);
        charBuffer.clear();
        // byteBuffer: ****B|_____________| charBuffer: |________|
        byteBuffer.flip();
        // byteBuffer: |****B|_____________ charBuffer: |________|
        decoder.decode(byteBuffer, charBuffer, cb == -1);
        // byteBuffer: ***|*B|_____________ charBuffer: ###|_____|
        byteBuffer.compact();
        // byteBuffer: *B|________________| charBuffer: ###|_____|
        charBuffer.flip();
        // byteBuffer: *B|________________| charBuffer: |###|_____
        if (cb == -1) {
            // Don't count trailing chunks.
            boolean hasTrailing = charBuffer.hasRemaining();
            return hasTrailing;
        } else {
            __chunks++;
        }
        return true;
    }

    @Override
    public void close() {
    }

}
