package net.bodz.bas.mem.types;

import net.bodz.bas.mem.AccessException;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem._Type;

public class Ieee754DoubleLE extends _Type {

    @Override
    public Double get(Memory memory, int offset) throws AccessException {
        byte[] mem = new byte[8];
        memory.read(offset, mem);
        long bits = Int64LE.read(mem);
        return Double.longBitsToDouble(bits);
    }

    @Override
    public void put(Memory memory, int offset, Object value)
            throws AccessException {
        byte[] mem = new byte[8];
        long bits = Double.doubleToRawLongBits((Double) value);
        Int64LE.write(mem, bits);
        memory.write(offset, mem);
    }

}
