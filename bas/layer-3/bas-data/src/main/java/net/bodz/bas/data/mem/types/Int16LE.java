package net.bodz.bas.data.mem.types;

import net.bodz.bas.c.primitive.IntMath;
import net.bodz.bas.data.mem.AbstractType;
import net.bodz.bas.data.mem.IMemory;
import net.bodz.bas.data.mem.MemoryAccessException;

public class Int16LE
        extends AbstractType {

    @Override
    public Short get(IMemory memory, int offset)
            throws MemoryAccessException {
        byte[] mem = new byte[2];
        memory.read(offset, mem);
        return read(mem);
    }

    @Override
    public void put(IMemory memory, int offset, Object value)
            throws MemoryAccessException {
        byte[] mem = new byte[2];
        write(mem, (Short) value);
        memory.write(offset, mem);
    }

    public static short read(byte[] b) {
        int value = IntMath.unsign(b[1]);
        value <<= 8;
        value |= IntMath.unsign(b[0]);
        return (short) value;
    }

    public static void write(byte[] b, short value) {
        b[0] = (byte) value;
        value >>= 8;
        b[1] = (byte) value;
    }

}
