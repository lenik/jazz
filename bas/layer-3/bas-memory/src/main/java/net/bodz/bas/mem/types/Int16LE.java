package net.bodz.bas.mem.types;

import net.bodz.bas.mem.AbstractType;
import net.bodz.bas.mem.MemoryAccessException;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.util.primitive.IntMath;

public class Int16LE
        extends AbstractType {

    @Override
    public Short get(Memory memory, int offset)
            throws MemoryAccessException {
        byte[] mem = new byte[2];
        memory.read(offset, mem);
        return read(mem);
    }

    @Override
    public void put(Memory memory, int offset, Object value)
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
