package net.bodz.bas.io.resource.builtin;

import java.io.IOException;

import net.bodz.bas.sio.AbstractCharIn;
import net.bodz.bas.t.buffer.MovableCharBuffer;

public class MovableCharBufferCharIn
        extends AbstractCharIn {

    private final MovableCharBuffer buffer;
    private int position;
    private boolean closed;

    public MovableCharBufferCharIn(MovableCharBuffer buffer) {
        this(buffer, 0);
    }

    public MovableCharBufferCharIn(MovableCharBuffer buffer, int position) {
        if (buffer == null)
            throw new NullPointerException("buffer");
        this.buffer = buffer;
        this.position = position;
    }

    public MovableCharBuffer getBuffer() {
        return buffer;
    }

    @Override
    public synchronized int read() {
        checkIfClosed();
        if (position < buffer.size()) {
            return buffer.charAt(position++);
        } else {
            return -1;
        }
    }

    @Override
    public synchronized int read(char[] b, int off, int len)
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
