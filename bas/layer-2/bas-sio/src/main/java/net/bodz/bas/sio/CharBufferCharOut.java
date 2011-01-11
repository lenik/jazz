package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;

public class CharBufferCharOut
        extends AbstractCharOut {

    private final CharBuffer cb;

    public CharBufferCharOut(CharBuffer charBuffer) {
        if (charBuffer == null)
            throw new NullPointerException("charBuffer");
        this.cb = charBuffer;
    }

    @Override
    public void write(int c)
            throws IOException {
        cb.put((char) c);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        cb.put(chars, off, len);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        try {
            cb.put(string, off, len);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
        try {
            cb.put(charBuffer);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public int hashCode() {
        return 0x56ec273b + cb.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CharBufferCharOut))
            return false;
        CharBufferCharOut o = (CharBufferCharOut) obj;
        return cb.equals(o.cb);
    }

    @Override
    public String toString() {
        return cb.toString();
    }

}