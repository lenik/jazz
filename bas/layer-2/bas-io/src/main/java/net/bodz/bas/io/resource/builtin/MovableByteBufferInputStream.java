package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.t.buffer.MovableByteBuffer;

public class MovableByteBufferInputStream
        extends InputStream {

    private final MovableByteBuffer buffer;
    private int position;
    private boolean closed;

    public MovableByteBufferInputStream(MovableByteBuffer buffer) {
        this(buffer, 0);
    }

    public MovableByteBufferInputStream(MovableByteBuffer buffer, int position) {
        if (buffer == null)
            throw new NullPointerException("buffer");
        this.buffer = buffer;
        this.position = position;
    }

    public MovableByteBuffer getBuffer() {
        return buffer;
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
