package net.bodz.bas.sio.util;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import net.bodz.bas.sio.AbstractByteOutEx;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;

/**
 * The bytes written to this {@link IByteOut}, is decoded into a {@link ICharOut}.
 */
public class DecodedByteOut
        extends AbstractByteOutEx {

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
        // byteBuffer: ****|______________| charBuffer: ****|________|
        byteBuffer.put((byte) byt);
        // byteBuffer: ****B|_____________| charBuffer: ****|________|
        byteBuffer.flip();
        // byteBuffer: |****B|_____________ charBuffer: ****|________|
        decoder.decode(byteBuffer, charBuffer, false);
        // byteBuffer: ***|*B|_____________ charBuffer: ****###|_____|
        byteBuffer.compact();
        // byteBuffer: *B|________________| charBuffer: ****###|_____|
        charOut.write(charBuffer);
        // (no change)
        charBuffer.clear();
        // byteBuffer: |*B|________________| charBuffer: |____________|
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
            // byteBuffer: ****|______________| charBuffer: ****|________|
            byteBuffer.put(bytes, off, chunk);
            off += chunk;
            len -= chunk;
            // byteBuffer: ****B|_____________| charBuffer: ****|________|
            byteBuffer.flip();
            // byteBuffer: |****B|_____________ charBuffer: ****|________|
            decoder.decode(byteBuffer, charBuffer, false);
            // byteBuffer: ***|*B|_____________ charBuffer: ****###|_____|
            byteBuffer.compact();
            // byteBuffer: *B|________________| charBuffer: ****###|_____|
            charOut.write(charBuffer);
            charBuffer.clear();
            // byteBuffer: |*B|________________| charBuffer: |____________|
            __chunks++;
        }
    }

    @Override
    public void close()
            throws IOException {
        byteBuffer.flip();
        decoder.decode(byteBuffer, charBuffer, true);
        // byteBuffer.compact();
        byteBuffer.clear();
        charOut.write(charBuffer);
        charBuffer.clear();
        super.close();
    }

}
