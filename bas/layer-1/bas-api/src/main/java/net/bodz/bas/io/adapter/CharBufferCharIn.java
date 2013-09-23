package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.nio.CharBuffer;

import net.bodz.bas.io.AbstractCharIn;

public class CharBufferCharIn
        extends AbstractCharIn {

    private final CharBuffer buf;

    public CharBufferCharIn(CharBuffer buf) {
        if (buf == null)
            throw new NullPointerException("buf");
        this.buf = buf;
    }

    @Override
    public int read()
            throws IOException {
        if (buf.hasRemaining()) {
            char ch = buf.get();
            return ch & 0xFFFF;
        }
        return -1;
    }

    @Override
    public int read(char[] dst, int off, int len)
            throws IOException {
        int remaining = buf.remaining();
        if (remaining == 0)
            return -1;
        int ccRead = Math.min(remaining, len);
        buf.get(dst, off, len);
        return ccRead;
    }

    @Override
    public int read(CharBuffer dst)
            throws IOException {
        if (dst == null)
            throw new NullPointerException("dst");
        return buf.read(dst);
    }

    @Override
    public int hashCode() {
        return 0x56ec273b + buf.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CharBufferCharIn))
            return false;
        CharBufferCharIn o = (CharBufferCharIn) obj;
        return buf.equals(o.buf);
    }

    @Override
    public String toString() {
        return buf.toString();
    }

}
