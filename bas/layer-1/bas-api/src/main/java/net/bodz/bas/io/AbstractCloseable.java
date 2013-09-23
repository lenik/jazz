package net.bodz.bas.io;

import java.io.IOException;

public abstract class AbstractCloseable
        implements ICloseable {

    private boolean closed;

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

}
