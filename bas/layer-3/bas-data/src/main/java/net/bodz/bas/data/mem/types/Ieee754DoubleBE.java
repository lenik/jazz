package net.bodz.bas.data.mem.types;

import net.bodz.bas.data.mem.AbstractType;
import net.bodz.bas.data.mem.Memory;
import net.bodz.bas.data.mem.MemoryAccessException;

public class Ieee754DoubleBE
        extends AbstractType {

    @Override
    public Double get(Memory memory, int offset)
            throws MemoryAccessException {
        byte[] mem = new byte[8];
        memory.read(offset, mem);
        long bits = Int64BE.read(mem);
        return Double.longBitsToDouble(bits);
    }

    @Override
    public void put(Memory memory, int offset, Object value)
            throws MemoryAccessException {
        byte[] mem = new byte[8];
        long bits = Double.doubleToRawLongBits((Double) value);
        Int64BE.write(mem, bits);
        memory.write(offset, mem);
    }

}
