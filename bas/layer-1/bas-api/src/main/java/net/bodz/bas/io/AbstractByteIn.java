package net.bodz.bas.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public abstract class AbstractByteIn
        extends InputStream
        implements IByteIn {

    private boolean closed;

    /** ⇱ Implementation Of {@link IByteIn}. */
    ;

    @Override
    public int read(byte[] buf)
            throws IOException {
        return read(buf, 0, buf.length);
    }

    @Override
    public int read(ByteBuffer buf)
            throws IOException {
        return fn.read(this, buf);
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

    public InputStream toInputStream() {
        return this;
    }

}
