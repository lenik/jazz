package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;

public class CharBufferCharIn
        extends AbstractCharIn {

    private final CharBuffer cb;

    public CharBufferCharIn(CharBuffer charBuffer) {
        if (charBuffer == null)
            throw new NullPointerException("charBuffer");
        this.cb = charBuffer;
    }

    @Override
    public int read()
            throws IOException {
        if (cb.hasRemaining()) {
            char ch = cb.get();
            return ch & 0xFFFF;
        }
        return -1;
    }

    @Override
    public int read(char[] chars, int off, int len)
            throws IOException {
        int remaining = cb.remaining();
        if (remaining == 0)
            return -1;
        int ccRead = Math.min(remaining, len);
        cb.get(chars, off, len);
        return ccRead;
    }

    @Override
    public int read(CharBuffer buffer)
            throws IOException {
        if (buffer == null)
            throw new NullPointerException("buffer");
        return cb.read(buffer);
    }

    @Override
    public int hashCode() {
        return 0x56ec273b + cb.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CharBufferCharIn))
            return false;
        CharBufferCharIn o = (CharBufferCharIn) obj;
        return cb.equals(o.cb);
    }

    @Override
    public String toString() {
        return cb.toString();
    }

}
