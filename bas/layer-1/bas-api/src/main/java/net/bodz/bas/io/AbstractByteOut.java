package net.bodz.bas.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import net.bodz.bas.io.adapter.ByteOutOutputStream;
import net.bodz.bas.io.adapter.ByteOutPrintStream;

public abstract class AbstractByteOut
        implements IByteOut {

    private boolean closed;

    @Override
    public void write(byte[] buf)
            throws IOException {
        write(buf, 0, buf.length);
    }

    @Override
    public void write(ByteBuffer buf)
            throws IOException {
        fn.write(this, buf);
    }

    /**
     * Do nothing in default implementation.
     */
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

    public OutputStream toOutputStream() {
        return new ByteOutOutputStream(this);
    }

    public OutputStream toPrintStream() {
        return new ByteOutPrintStream(this);
    }

}
