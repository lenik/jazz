package net.bodz.bas.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

import net.bodz.bas.io.adapter.CharInReader;

public abstract class AbstractCharIn
        implements ICharIn {

    private boolean closed;

    @Override
    public int read(char[] chars)
            throws IOException {
        return read(chars, 0, chars.length);
    }

    @Override
    public int read(CharBuffer buf)
            throws IOException {
        return fn.read(this, buf);
    }

    @Override
    public String readString(int maxCharacters)
            throws IOException {
        return fn.readString(this, maxCharacters);
    }

    @Override
    public void close()
            throws IOException {
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

    public Reader toReader() {
        return new CharInReader(this);
    }

}
