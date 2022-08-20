package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;

import net.bodz.bas.io.AbstractCharOut;

public class CharBufferCharOut
        extends AbstractCharOut {

    private final CharBuffer buf;

    public CharBufferCharOut(CharBuffer buf) {
        if (buf == null)
            throw new NullPointerException("buf");
        this.buf = buf;
    }

    @Override
    public void write(int c)
            throws IOException {
        buf.put((char) c);
    }

    @Override
    public void write(char[] src, int off, int len)
            throws IOException {
        buf.put(src, off, len);
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        try {
            buf.put(string, off, len);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(CharBuffer src)
            throws IOException {
        try {
            buf.put(src);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public int hashCode() {
        return 0x56ec273b + buf.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CharBufferCharOut))
            return false;
        CharBufferCharOut o = (CharBufferCharOut) obj;
        return buf.equals(o.buf);
    }

    @Override
    public String toString() {
        return buf.toString();
    }

}