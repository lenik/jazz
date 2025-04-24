package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.buffer.MovableCharBuffer;

public class MovableCharBufferPrintOut
        extends Writer
        implements IPrintOut {

    private final MovableCharBuffer buffer;
    private int position;
    private boolean closed;

    public MovableCharBufferPrintOut(MovableCharBuffer buffer) {
        this(buffer, buffer.size());
    }

    public MovableCharBufferPrintOut(MovableCharBuffer buffer, int position) {
        if (buffer == null)
            throw new NullPointerException("buffer");
        this.buffer = buffer;
        this.position = position;
    }

    @Override
    public synchronized void write(int ch)
            throws IOException {
        ensureOpen();
        buffer.ensureSize(position + 1);
        buffer.charAt(position++, (char) ch);
    }

    @Override
    public synchronized void write(@NotNull char[] buf, int off, int len)
            throws IOException {
        ensureOpen();
        buffer.ensureSize(position + len);
        buffer.copyFrom(buf, off, position, len);
        position += len;
    }

    public synchronized void writeTo(Writer out)
            throws IOException {
        ensureOpen();
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
    public void flush() {
    }

    @Override
    public void close() {
        closed = true;
    }

    private void ensureOpen() {
        if (closed)
            throw new IllegalStateException("closed");
    }

}
