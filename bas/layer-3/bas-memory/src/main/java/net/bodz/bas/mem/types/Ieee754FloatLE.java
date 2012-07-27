package net.bodz.bas.mem.types;

import net.bodz.bas.mem.AbstractType;
import net.bodz.bas.mem.AccessException;
import net.bodz.bas.mem.Memory;

public class Ieee754FloatLE
        extends AbstractType {

    @Override
    public Float get(Memory memory, int offset)
            throws AccessException {
        byte[] mem = new byte[4];
        memory.read(offset, mem);
        int bits = Int32LE.read(mem);
        return Float.intBitsToFloat(bits);
    }

    @Override
    public void put(Memory memory, int offset, Object value)
            throws AccessException {
        byte[] mem = new byte[4];
        int bits = Float.floatToRawIntBits((Float) value);
        Int32LE.write(mem, bits);
        memory.write(offset, mem);
    }

}
