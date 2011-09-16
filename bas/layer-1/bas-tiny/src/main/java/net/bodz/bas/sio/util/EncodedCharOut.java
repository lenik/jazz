package net.bodz.bas.sio.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import net.bodz.bas.sio.AbstractCharOut;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharOut;

/**
 * The chars written to this {@link ICharOut}, is encoded into a {@link IByteOut}.
 */
public class EncodedCharOut
        extends AbstractCharOut {

    private final IByteOut byteOut;
    private final CharsetEncoder encoder;

    /** The encode input buffer */
    private CharBuffer charBuffer;
    /** The encode output buffer */
    private ByteBuffer byteBuffer;

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
    public void write(int ch)
            throws IOException {
        // charBuffer: ****|______________| byteBuffer: ****|________|
        charBuffer.put((char) ch);
        // charBuffer: ****C|_____________| byteBuffer: ****|________|
        charBuffer.flip();
        // charBuffer: |****C|_____________ byteBuffer: ****|________|
        encoder.encode(charBuffer, byteBuffer, false);
        // charBuffer: ***|*C|_____________ byteBuffer: ****###|_____|
        charBuffer.compact();
        // charBuffer: *C|________________| byteBuffer: ****###|_____|
        byteOut.write(byteBuffer);
        // (no change)
        byteBuffer.clear();
        // charBuffer: |*C|________________| byteBuffer: |____________|
        __chunks++;
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        while (len > 0) {
            int chunk = Math.min(len, charBuffer.remaining());
            // charBuffer: ****|______________| byteBuffer: ****|________|
            charBuffer.put(chars, off, chunk);
            off += chunk;
            len -= chunk;
            // charBuffer: ****C|_____________| byteBuffer: ****|________|
            charBuffer.flip();
            // charBuffer: |****C|_____________ byteBuffer: ****|________|
            encoder.encode(charBuffer, byteBuffer, false);
            // charBuffer: ***|*C|_____________ byteBuffer: ****###|_____|
            charBuffer.compact();
            // charBuffer: *C|________________| byteBuffer: ****###|_____|
            byteOut.write(byteBuffer);
            byteBuffer.clear();
            // charBuffer: |*C|________________| byteBuffer: |____________|
            __chunks++;
        }
    }

    @Override
    public void close()
            throws IOException {
        charBuffer.flip();
        encoder.encode(charBuffer, byteBuffer, true);
        // charBuffer.compact();
        charBuffer.clear();
        byteOut.write(byteBuffer);
        byteBuffer.clear();
        super.close();
    }

}
