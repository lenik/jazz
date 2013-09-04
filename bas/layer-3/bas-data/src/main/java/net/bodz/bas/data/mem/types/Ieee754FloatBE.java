package net.bodz.bas.data.mem.types;

import net.bodz.bas.data.mem.AbstractType;
import net.bodz.bas.data.mem.IMemory;
import net.bodz.bas.data.mem.MemoryAccessException;

public class Ieee754FloatBE
        extends AbstractType {

    @Override
    public Float get(IMemory memory, int offset)
            throws MemoryAccessException {
        byte[] mem = new byte[4];
        memory.read(offset, mem);
        int bits = Int32BE.read(mem);
        return Float.intBitsToFloat(bits);
    }

    @Override
    public void put(IMemory memory, int offset, Object value)
            throws MemoryAccessException {
        byte[] mem = new byte[4];
        int bits = Float.floatToRawIntBits((Float) value);
        Int32BE.write(mem, bits);
        memory.write(offset, mem);
    }

}
