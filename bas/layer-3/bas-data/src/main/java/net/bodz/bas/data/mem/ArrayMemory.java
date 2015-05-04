package net.bodz.bas.data.mem;

import net.bodz.bas.err.OutOfDomainException;

public class ArrayMemory
        extends AbstractMemory {

    private final byte[] array;
    private final int start;
    private final int end;

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
    public int read(int addr)
            throws MemoryAccessException {
        if (addr >= end - start)
            throw new BadAddressException(addr, end - start);
        return array[start + addr] & 0xFF;
    }

    @Override
    public void write(int addr, byte value)
            throws MemoryAccessException {
        if (addr >= end - start)
            throw new BadAddressException(addr, end - start);
        array[start + addr] = value;
    }

    @Override
    public void read(int addr, byte[] buf, int off, int len)
            throws MemoryAccessException {
        int actualEnd = start + addr + len;
        if (actualEnd > end)
            throw new BadAddressException(actualEnd, end);
        System.arraycopy(array, start + addr, buf, off, len);
    }

    @Override
    public void write(int addr, byte[] buf, int off, int len)
            throws BadAddressException {
        int actualEnd = start + addr + len;
        if (actualEnd > end)
            throw new BadAddressException(actualEnd, end);
        System.arraycopy(buf, off, array, start + addr, len);
    }

    /**
     * if access by negative index is allowed, the precheck should be disabled.
     */
    static boolean precheck = true;

    @Override
    public ArrayMemory offset(long off) {
        if (off == 0)
            return this;
        int len = end - start;
        if (precheck && off > len)
            throw new OutOfDomainException("off", off, len);
        int off32 = (int) off;
        if (precheck && start + off32 < 0)
            throw new OutOfDomainException("off", off, len);
        return new ArrayMemory(array, start + off32, len - off32);
    }

}
