package net.bodz.bas.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public abstract class AbstractCharIn
        extends Reader
        implements ICharIn {

    private boolean closed;

    /** ⇱ Implementation Of {@link ICharIn}. */
    ;

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

    /** ⇱ Implementation Of {@link ICloseable}. */
    ;

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
        return this;
    }

}
