package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;

public class CharBufferCharIn
        extends AbstractCharIn {

    private final CharBuffer charBuffer;

    public CharBufferCharIn(CharBuffer charBuffer) {
        if (charBuffer == null)
            throw new NullPointerException("charBuffer");
        this.charBuffer = charBuffer;
    }

    @Override
    public int read()
            throws IOException {
        if (charBuffer.hasRemaining()) {
            char ch = charBuffer.get();
            return ch & 0xFFFF;
        }
        return -1;
    }

    @Override
    public int read(char[] chars, int off, int len)
            throws IOException {
        int remaining = charBuffer.remaining();
        if (remaining == 0)
            return -1;
        int ccRead = Math.min(remaining, len);
        charBuffer.get(chars, off, len);
        return ccRead;
    }

    @Override
    public int read(CharBuffer buffer)
            throws IOException {
        if (buffer == null)
            throw new NullPointerException("buffer");
        return charBuffer.read(buffer);
    }

    @Override
    public int hashCode() {
        return 0x56ec273b + charBuffer.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CharBufferCharIn))
            return false;
        CharBufferCharIn o = (CharBufferCharIn) obj;
        return charBuffer.equals(o.charBuffer);
    }

    @Override
    public String toString() {
        return charBuffer.toString();
    }

}
