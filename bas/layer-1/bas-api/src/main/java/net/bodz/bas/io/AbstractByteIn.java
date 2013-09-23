package net.bodz.bas.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import net.bodz.bas.io.adapter.ByteInInputStream;

public abstract class AbstractByteIn
        implements IByteIn {

    private boolean closed;

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
        return new ByteInInputStream(this);
    }

}
