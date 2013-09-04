package net.bodz.bas.data.mem.types;

import net.bodz.bas.data.mem.AbstractType;
import net.bodz.bas.data.mem.IMemory;
import net.bodz.bas.data.mem.MemoryAccessException;

public class Ieee754DoubleLE
        extends AbstractType {

    @Override
    public Double get(IMemory memory, int offset)
            throws MemoryAccessException {
        byte[] mem = new byte[8];
        memory.read(offset, mem);
        long bits = Int64LE.read(mem);
        return Double.longBitsToDouble(bits);
    }

    @Override
    public void put(IMemory memory, int offset, Object value)
            throws MemoryAccessException {
        byte[] mem = new byte[8];
        long bits = Double.doubleToRawLongBits((Double) value);
        Int64LE.write(mem, bits);
        memory.write(offset, mem);
    }

}
