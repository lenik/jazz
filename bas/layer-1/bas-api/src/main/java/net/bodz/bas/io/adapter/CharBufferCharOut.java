package net.bodz.bas.io.adapter;

import java.io.IOException;
import java.io.Writer;
import java.nio.BufferOverflowException;
import java.nio.CharBuffer;

import net.bodz.bas.io.ICharOut;
import net.bodz.bas.meta.decl.NotNull;

public class CharBufferCharOut
        extends Writer
        implements ICharOut {

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
    public void writeChar(int ch)
            throws IOException {
        buf.put((char) ch);
    }

    @Override
    public void write(@NotNull char[] src, int off, int len)
            throws IOException {
        buf.put(src, off, len);
    }

    @Override
    public void write(@NotNull String string, int off, int len)
            throws IOException {
        try {
            buf.put(string, off, len);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(@NotNull CharBuffer src)
            throws IOException {
        try {
            buf.put(src);
        } catch (BufferOverflowException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
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