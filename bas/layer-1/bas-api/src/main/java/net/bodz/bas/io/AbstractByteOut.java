package net.bodz.bas.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import net.bodz.bas.io.adapter.ByteOutPrintStream;

public abstract class AbstractByteOut
        extends OutputStream
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

    @Override
    public void close()
            throws IOException {
        flush();
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
        return this;
    }

    public OutputStream toPrintStream() {
        return new ByteOutPrintStream(this);
    }

}
