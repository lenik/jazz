package net.bodz.bas.io.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import net.bodz.bas.io.AbstractByteIn;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;

/**
 * The bytes read from this {@link IByteIn}, is encoded from a {@link ICharIn}.
 */
public class EncodedByteIn
        extends AbstractByteIn {

    private final ICharIn charIn;
    private final CharsetEncoder encoder;

    /** The encode input buffer */
    private CharBuffer charBuffer;
    /** The encode output buffer */
    private ByteBuffer byteBuffer;

    int __chunks;

    public EncodedByteIn(ICharIn charIn, String charsetName) {
        this(charIn, Charset.forName(charsetName));
    }

    public EncodedByteIn(ICharIn charIn, Charset charset) {
        this(charIn, charset.newEncoder());
    }

    public EncodedByteIn(ICharIn charIn, CharsetEncoder encoder) {
        if (charIn == null)
            throw new NullPointerException("charIn");
        if (encoder == null)
            throw new NullPointerException("encoder");
        this.charIn = charIn;
        this.encoder = encoder;
        this.charBuffer = TranscodeConfig.allocateEncodeInputBuffer();
        this.byteBuffer = TranscodeConfig.allocateOutputBuffer(encoder);
        byteBuffer.flip();
    }

    @Override
    public int read()
            throws IOException {
        if (!byteBuffer.hasRemaining())
            if (!prefetch())
                return -1;
        byte b = byteBuffer.get();
        return b;
    }

    @Override
    public int read(byte[] buf, int off, int len)
            throws IOException {
        int pos = off;
        while (len > 0) {
            if (!byteBuffer.hasRemaining())
                if (!prefetch())
                    return pos == off ? -1 : pos - off;
            int block = Math.min(len, byteBuffer.remaining());
            byteBuffer.get(buf, pos, block);
            pos += block;
            len -= block;
        }
        return pos - off;
    }

    boolean prefetch()
            throws IOException {
        int cc = charIn.read(charBuffer);
        byteBuffer.clear();
        // charBuffer: ****C|_____________| byteBuffer: |________|
        charBuffer.flip();
        // charBuffer: |****C|_____________ byteBuffer: |________|
        encoder.encode(charBuffer, byteBuffer, cc == -1);
        // charBuffer: ***|*C|_____________ byteBuffer: ###|_____|
        charBuffer.compact();
        // charBuffer: *C|________________| byteBuffer: ###|_____|
        byteBuffer.flip();
        // charBuffer: *C|________________| byteBuffer: |###|_____
        if (cc == -1) {
            // Don't count trailing chunks.
            boolean hasTrailing = byteBuffer.hasRemaining();
            return hasTrailing;
        } else {
            __chunks++;
        }
        return true;
    }

}
