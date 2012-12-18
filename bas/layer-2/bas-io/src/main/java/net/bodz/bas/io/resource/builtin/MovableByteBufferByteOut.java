package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.sio.AbstractByteOut;
import net.bodz.bas.t.buffer.MovableByteBuffer;

public class MovableByteBufferByteOut
        extends AbstractByteOut {

    private final MovableByteBuffer buffer;
    private int position;
    private boolean closed;

    public MovableByteBufferByteOut(MovableByteBuffer buffer) {
        this(buffer, buffer.size());
    }

    public MovableByteBufferByteOut(MovableByteBuffer buffer, int position) {
        this.buffer = buffer;
        this.position = position;
    }

    @Override
    public synchronized void write(int b)
            throws IOException {
        checkIfClosed();
        buffer.ensureSize(position + 1);
        buffer.byteAt(position++, (byte) b);
    }

    @Override
    public synchronized void write(byte[] b, int off, int len)
            throws IOException {
        checkIfClosed();
        buffer.ensureSize(position + len);
        buffer.copyFrom(b, off, position, len);
        position += len;
    }

    public synchronized void writeTo(OutputStream out)
            throws IOException {
        out.write(buffer.getArray(), buffer.getStart(), buffer.size());
    }

    public MovableByteBuffer getBuffer() {
        return buffer;
    }

    public byte[] toByteArray() {
        return buffer.copy();
    }

    @Override
    public String toString() {
        return buffer.toString();
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
