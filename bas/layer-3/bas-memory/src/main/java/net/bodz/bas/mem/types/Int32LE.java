package net.bodz.bas.mem.types;

import net.bodz.bas.c.primitive.IntMath;
import net.bodz.bas.mem.AbstractType;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem.MemoryAccessException;
import net.bodz.bas.meta.decl.ThreadUnsafe;

public class Int32LE
        extends AbstractType {

    @ThreadUnsafe
    @Override
    public Integer get(Memory memory, int offset)
            throws MemoryAccessException {
        byte[] mem = new byte[4];
        memory.read(offset, mem);
        return read(mem);
    }

    @ThreadUnsafe
    @Override
    public void put(Memory memory, int offset, Object value)
            throws MemoryAccessException {
        byte[] mem = new byte[4];
        write(mem, (Integer) value);
        memory.write(offset, mem);
    }

    public static int read(byte[] b) {
        int value = IntMath.unsign(b[3]);
        value <<= 8;
        value |= IntMath.unsign(b[2]);
        value <<= 8;
        value |= IntMath.unsign(b[1]);
        value <<= 8;
        value |= IntMath.unsign(b[0]);
        return value;
    }

    public static void write(byte[] b, int value) {
        b[0] = (byte) value;
        value >>= 8;
        b[1] = (byte) value;
        value >>= 8;
        b[2] = (byte) value;
        value >>= 8;
        b[3] = (byte) value;
    }

}
