package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.t.buffer.MovableCharBuffer;

public class MovableCharBufferWriter
        extends Writer {

    private final MovableCharBuffer buffer;
    private int position;
    private boolean closed;

    public MovableCharBufferWriter(MovableCharBuffer buffer) {
        this(buffer, buffer.size());
    }

    public MovableCharBufferWriter(MovableCharBuffer buffer, int position) {
        if (buffer == null)
            throw new NullPointerException("buffer");
        this.buffer = buffer;
        this.position = position;
    }

    @Override
    public synchronized void write(int ch)
            throws IOException {
        checkIfClosed();
        buffer.ensureSize(position + 1);
        buffer.charAt(position++, (char) ch);
    }

    @Override
    public synchronized void write(char[] b, int off, int len)
            throws IOException {
        checkIfClosed();
        buffer.ensureSize(position + len);
        buffer.copyFrom(b, off, position, len);
        position += len;
    }

    public synchronized void writeTo(Writer out)
            throws IOException {
        checkIfClosed();
        out.write(buffer.getArray(), buffer.getStart(), buffer.size());
    }

    public MovableCharBuffer getBuffer() {
        return buffer;
    }

    public char[] toCharArray() {
        return buffer.copy();
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    @Override
    public void flush()
            throws IOException {
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
