package net.bodz.bas.io;

import java.io.IOException;
import java.io.Writer;
import java.nio.CharBuffer;

public abstract class AbstractCharOut
        extends Writer
        implements ICharOut {

    private boolean closed;

    @Override
    public void write(char[] chars)
            throws IOException {
        write(chars, 0, chars.length);
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        CharBuffer buf = CharBuffer.wrap(chars, off, off + len);
        write(buf);
    }

    @Override
    public void write(String s)
            throws IOException {
        write(s.toCharArray());
    }

    @Override
    public void write(String string, int off, int len)
            throws IOException {
        char[] buf = new char[len];
        string.getChars(off, off + len, buf, 0);
        write(buf, 0, len);
    }

    @Override
    public void write(CharBuffer buf)
            throws IOException {
        fn.write(this, buf);
    }

    public void dump(ICharIn charIn)
            throws IOException {
        fn.dump(this, charIn);
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
        flush(false);
    }

    @Override
    public void close()
            throws IOException {
        flush(true);
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    protected void ensureOpen() {
        if (closed)
            throw new IllegalStateException("already closed");
    }

    public Writer toWriter() {
        return this;
    }

}
