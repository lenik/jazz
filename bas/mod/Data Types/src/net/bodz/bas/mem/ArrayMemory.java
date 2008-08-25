package net.bodz.bas.mem;

import static net.bodz.bas.types.util.ArrayOps.Bytes;
import net.bodz.bas.lang.err.OutOfDomainException;

public class ArrayMemory extends _Memory {

    private final byte[] array;
    private final int    start;
    private final int    end;

    public ArrayMemory(int capacity) {
        this(new byte[capacity]);
    }

    public ArrayMemory(byte[] array) {
        this(array, 0, array.length);
    }

    public ArrayMemory(byte[] array, int off, int len) {
        this.array = array;
        this.start = off;
        this.end = off + len;
    }

    @Override
    public byte read(int addr) throws AccessException {
        if (addr >= end - start)
            throw new OutOfDomainException("addr", addr, end - start);
        return array[start + addr];
    }

    @Override
    public void write(int addr, byte value) throws AccessException {
        if (addr >= end - start)
            throw new OutOfDomainException("addr", addr, end - start);
        array[start + addr] = value;
    }

    @Override
    public void read(int addr, byte[] buf, int off, int len)
            throws AccessException {
        int actualEnd = start + addr + len;
        if (actualEnd > end)
            throw new OutOfDomainException("actualEnd", actualEnd, end);
        Bytes.copy(array, start + addr, buf, off, len);
    }

    @Override
    public void write(int addr, byte[] buf, int off, int len) {
        int actualEnd = start + addr + len;
        if (actualEnd > end)
            throw new OutOfDomainException("actualEnd", actualEnd, end);
        Bytes.copy(buf, off, array, start + addr, len);
    }

    @Override
    public ArrayMemory offset(int off) {
        if (off == 0)
            return this;
        int len = end - start;
        if (off > len)
            throw new OutOfDomainException("off", off, len);
        return new ArrayMemory(array, start + off, len - off);
    }

}
