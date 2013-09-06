package net.bodz.bas.io.res.builtin;

import java.io.IOException;

import net.bodz.bas.io.AbstractByteIn;
import net.bodz.bas.t.buffer.MovableByteBuffer;

public class MovableByteBufferByteIn
        extends AbstractByteIn {

    private final MovableByteBuffer buffer;
    private int position;
    private boolean closed;

    public MovableByteBufferByteIn(MovableByteBuffer buffer) {
        this(buffer, 0);
    }

    public MovableByteBufferByteIn(MovableByteBuffer buffer, int position) {
        if (buffer == null)
            throw new NullPointerException("buffer");
        this.buffer = buffer;
        this.position = position;
    }

    @Override
    public synchronized int read() {
        checkIfClosed();
        if (position < buffer.size()) {
            return buffer.byteAt(position++);
        } else {
            return -1;
        }
    }

    @Override
    public synchronized int read(byte[] b, int off, int len)
            throws IOException {
        checkIfClosed();
        int count = Math.min(len, buffer.size() - position);
        if (count == 0)
            return -1;
        buffer.copyTo(position, b, off, count);
        return count;
    }

    @Override
    public void close()
            throws IOException {
        closed = true;
    }

    private void checkIfClosed() {
        if (closed)
            throw new IllegalStateException("closed");
    }

}
