package net.bodz.bas.mem;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.meta.codehint.ThreadUnsafe;

public class MemoryInputStream
        extends InputStream {

    private final Memory memory;

    /** addr_t */
    private int start;

    private int size;

    public MemoryInputStream(Memory memory, int start, int size) {
        this.memory = memory;
        this.start = start;
        this.size = size;
    }

    public MemoryInputStream(Memory memory) {
        this(memory, 0, -1);
    }

    @Override
    public int available()
            throws IOException {
        if (size == -1)
            return 0;
        return size;
    }

    private byte[] buf1 = { 0 };

    @ThreadUnsafe
    @Override
    public int read()
            throws IOException {
        if (size == 0)
            return -1;
        try {
            memory.read(start++, buf1);
        } catch (AccessException e) {
            throw new IOException(e);
        }
        if (size != -1)
            size--;
        return buf1[0];
    }

    @ThreadUnsafe
    @Override
    public int read(byte[] b, int off, int len)
            throws IOException {
        if (size == 0)
            return -1;
        try {
            if (size == -1) {
                memory.read(start, b, off, len);
                start += len;
                return len;
            }
            int cb = Math.min(size, len);
            memory.read(start, b, off, cb);
            start += cb;
            size -= cb;
            return cb;
        } catch (AccessException e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    @ThreadUnsafe
    @Override
    public long skip(long n)
            throws IOException {
        if (n <= 0)
            return 0;
        if (size == -1) {
            start += (int) n;
            return n;
        }
        int cb = size;
        if (cb > n)
            cb = (int) n;
        start += cb;
        return cb;
    }

}
