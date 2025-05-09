package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.buffer.MovableByteBuffer;

public class MovableByteBufferByteOut
        extends OutputStream
        implements IByteOut {

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
    public final void write(int b)
            throws IOException {
        writeByte(b);
    }

    @Override
    public synchronized void writeByte(int b)
            throws IOException {
        checkIfClosed();
        buffer.ensureSize(position + 1);
        buffer.byteAt(position++, (byte) b);
    }

    @Override
    public synchronized void write(@NotNull byte[] b, int off, int len)
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
