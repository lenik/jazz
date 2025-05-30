package net.bodz.bas.data.mem;

import java.io.IOException;
import java.io.OutputStream;

import net.bodz.bas.io.IByteOut;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.ThreadUnsafe;

public class MemoryOut
        extends OutputStream
        implements IByteOut {

    private final IMemory memory;

    /**
     * addr_t
     */
    private int start;

    private int size;

    public MemoryOut(IMemory memory, int start, int size) {
        this.memory = memory;
        this.start = start;
        this.size = size;
    }

    public MemoryOut(IMemory memory) {
        this(memory, 0, -1);
    }

    private final byte[] buf1 = { 0 };

    @Override
    public final void write(int b)
            throws IOException {
        writeByte(b);
    }

    @ThreadUnsafe
    @Override
    public void writeByte(int b)
            throws IOException {
        if (size == 0)
            throw new IOException("overflow");
        buf1[0] = (byte) b;
        try {
            memory.write(start++, buf1);
        } catch (MemoryAccessException e) {
            throw new IOException(e);
        }
        if (size != -1)
            size--;
    }

    @ThreadUnsafe
    @Override
    public void write(@NotNull byte[] b, int off, int len)
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
        } catch (MemoryAccessException e) {
            throw new IOException(e);
        }
    }

}
