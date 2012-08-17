package net.bodz.bas.mem;

import static net.bodz.bas.mem.types.Types.*;

public abstract class AbstractMemory
        implements Memory {

    @Override
    public void read(int addr, byte[] buf)
            throws MemoryAccessException {
        read(addr, buf, 0, buf.length);
    }

    @Override
    public void write(int addr, byte[] buf)
            throws MemoryAccessException {
        write(addr, buf, 0, buf.length);
    }

    @Override
    public int getByteOrder() {
        return LITTLE_ENDIAN;
    }

    @Override
    public short readInt16(int addr)
            throws MemoryAccessException {
        return INT16LE.get(this, addr);
    }

    @Override
    public int readInt32(int addr)
            throws MemoryAccessException {
        return INT32LE.get(this, addr);
    }

    @Override
    public long readInt64(int addr)
            throws MemoryAccessException {
        return INT64LE.get(this, addr);
    }

    @Override
    public void writeInt16(int addr, short value)
            throws MemoryAccessException {
        INT16LE.put(this, addr, value);
    }

    @Override
    public void writeInt32(int addr, int value)
            throws MemoryAccessException {
        INT32LE.put(this, addr, value);
    }

    @Override
    public void writeInt64(int addr, long value)
            throws MemoryAccessException {
        INT64LE.put(this, addr, value);
    }

}
