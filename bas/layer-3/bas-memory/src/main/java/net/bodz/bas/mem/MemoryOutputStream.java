package net.bodz.bas.mem;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.meta.codehint.ThreadUnsafe;

public class MemoryOutputStream
        extends OutputStream {

    private final Memory memory;

    /** addr_t */
    private int start;

    private int size;

    public MemoryOutputStream(Memory memory, int start, int size) {
        this.memory = memory;
        this.start = start;
        this.size = size;
    }

    public MemoryOutputStream(Memory memory) {
        this(memory, 0, -1);
    }

    private byte[] buf1 = { 0 };

    @ThreadUnsafe
    @Override
    public void write(int b)
            throws IOException {
        if (size == 0)
            throw new IOException("overflow");
        buf1[0] = (byte) b;
        try {
            memory.write(start++, buf1);
        } catch (AccessException e) {
            throw new IOException(e);
        }
        if (size != -1)
            size--;
    }

    @ThreadUnsafe
    @Override
    public void write(byte[] b, int off, int len)
            throws IOException {
        if (size == 0)
            throw new IOException("overflow");
        try {
            if (size == -1) {
                memory.write(start, b, off, len);
                start += len;
                return;
            }
            int cb = Math.min(size, len);
            memory.write(start, b, off, cb);
            start += cb;
            size -= cb;
        } catch (AccessException e) {
            throw new IOException(e);
        }
    }

}
