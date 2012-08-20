package net.bodz.bas.mem.types;

import net.bodz.bas.mem.AbstractType;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem.MemoryAccessException;

public class Ieee754FloatLE
        extends AbstractType {

    @Override
    public Float get(Memory memory, int offset)
            throws MemoryAccessException {
        byte[] mem = new byte[4];
        memory.read(offset, mem);
        int bits = Int32LE.read(mem);
        return Float.intBitsToFloat(bits);
    }

    @Override
    public void put(Memory memory, int offset, Object value)
            throws MemoryAccessException {
        byte[] mem = new byte[4];
        int bits = Float.floatToRawIntBits((Float) value);
        Int32LE.write(mem, bits);
        memory.write(offset, mem);
    }

}
