package net.bodz.bas.mem;

import static net.bodz.bas.mem.types.Types.*;

public abstract class AbstractMemory implements Memory {

    @Override
    public void read(int addr, byte[] buf) throws AccessException {
        read(addr, buf, 0, buf.length);
    }

    @Override
    public void write(int addr, byte[] buf) throws AccessException {
        write(addr, buf, 0, buf.length);
    }

    @Override
    public int getByteOrder() {
        return LITTLE_ENDIAN;
    }

    @Override
    public short readInt16(int addr) throws AccessException {
        return INT16LE.get(this, addr);
    }

    @Override
    public int readInt32(int addr) throws AccessException {
        return INT32LE.get(this, addr);
    }

    @Override
    public long readInt64(int addr) throws AccessException {
        return INT64LE.get(this, addr);
    }

    @Override
    public void writeInt16(int addr, short value) throws AccessException {
        INT16LE.put(this, addr, value);
    }

    @Override
    public void writeInt32(int addr, int value) throws AccessException {
        INT32LE.put(this, addr, value);
    }

    @Override
    public void writeInt64(int addr, long value) throws AccessException {
        INT64LE.put(this, addr, value);
    }

}
