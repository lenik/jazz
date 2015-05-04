package net.bodz.bas.data.mem;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.meta.decl.ThreadUnsafe;

public class MemoryIn
        extends InputStream
        implements IByteIn {

    private final IMemory memory;

    /** addr_t */
    private int start;

    private int size;

    public MemoryIn(IMemory memory, int start, int size) {
        this.memory = memory;
        this.start = start;
        this.size = size;
    }

    public MemoryIn(IMemory memory) {
        this(memory, 0, -1);
    }

    @Override
    public int available()
            throws IOException {
        if (size == -1)
            return 0;
        return size;
    }

    @ThreadUnsafe
    @Override
    public int read()
            throws IOException {
        if (size == 0)
            return -1;
        int byt;
        try {
            byt = memory.read(start++);
        } catch (MemoryAccessException e) {
            throw new IOException(e);
        }
        if (size != -1)
            size--;
        return byt;
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
        } catch (MemoryAccessException e) {
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

    @Override
    public int read(ByteBuffer buf)
            throws IOException {
        return fn.read(this, buf);
    }

    @Override
    public boolean isClosed() {
        return false;
    }

}
