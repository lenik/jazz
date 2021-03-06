package net.bodz.bas.data.mem.types;

import net.bodz.bas.c.primitive.IntMath;
import net.bodz.bas.data.mem.AbstractType;
import net.bodz.bas.data.mem.IMemory;
import net.bodz.bas.data.mem.MemoryAccessException;
import net.bodz.bas.meta.decl.ThreadUnsafe;

public class Int64LE
        extends AbstractType {

    @ThreadUnsafe
    @Override
    public Long get(IMemory memory, int offset)
            throws MemoryAccessException {
        byte[] mem = new byte[8];
        memory.read(offset, mem);
        return read(mem);
    }

    @ThreadUnsafe
    @Override
    public void put(IMemory memory, int offset, Object value)
            throws MemoryAccessException {
        byte[] mem = new byte[8];
        write(mem, (Long) value);
        memory.write(offset, mem);
    }

    public static long read(byte[] b) {
        int vh = IntMath.unsign(b[7]);
        vh <<= 8;
        vh |= IntMath.unsign(b[6]);
        vh <<= 8;
        vh |= IntMath.unsign(b[5]);
        vh <<= 8;
        vh |= IntMath.unsign(b[4]);
        int vl = IntMath.unsign(b[3]);
        vl <<= 8;
        vl |= IntMath.unsign(b[2]);
        vl <<= 8;
        vl |= IntMath.unsign(b[1]);
        vl <<= 8;
        vl |= IntMath.unsign(b[0]);
        long value = IntMath.unsign(vh);
        value <<= 32;
        value |= IntMath.unsign(vl);
        return value;
    }

    public static void write(byte[] b, long value) {
        int vl = (int) value;
        b[0] = (byte) vl;
        vl >>= 8;
        b[1] = (byte) vl;
        vl >>= 8;
        b[2] = (byte) vl;
        vl >>= 8;
        b[3] = (byte) vl;
        value >>= 32;
        int vh = (int) value;
        b[4] = (byte) vh;
        vh >>= 8;
        b[5] = (byte) vh;
        vh >>= 8;
        b[6] = (byte) vh;
        vh >>= 8;
        b[7] = (byte) vh;
    }

}
