package net.bodz.bas.mem.types;

import net.bodz.bas.lang.IntMath;
import net.bodz.bas.lang.annotations.ThreadUnsafe;
import net.bodz.bas.mem.AccessException;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem._Type;

public class Int64BE extends _Type {

    @ThreadUnsafe
    @Override
    public Long get(Memory memory, int offset) throws AccessException {
        byte[] mem = new byte[8];
        memory.read(offset, mem);
        return read(mem);
    }

    @ThreadUnsafe
    @Override
    public void put(Memory memory, int offset, Object value)
            throws AccessException {
        byte[] mem = new byte[8];
        write(mem, (Long) value);
        memory.write(offset, mem);
    }

    public static long read(byte[] b) throws AccessException {
        int vh = IntMath.unsign(b[0]);
        vh <<= 8;
        vh |= IntMath.unsign(b[1]);
        vh <<= 8;
        vh |= IntMath.unsign(b[2]);
        vh <<= 8;
        vh |= IntMath.unsign(b[3]);
        int vl = IntMath.unsign(b[4]);
        vl <<= 8;
        vl |= IntMath.unsign(b[5]);
        vl <<= 8;
        vl |= IntMath.unsign(b[6]);
        vl <<= 8;
        vl |= IntMath.unsign(b[7]);
        long value = IntMath.unsign(vh);
        value <<= 32;
        value |= IntMath.unsign(vl);
        return value;
    }

    public static void write(byte[] b, long value) throws AccessException {
        int vl = (int) value;
        b[7] = (byte) vl;
        vl >>= 8;
        b[6] = (byte) vl;
        vl >>= 8;
        b[5] = (byte) vl;
        vl >>= 8;
        b[4] = (byte) vl;
        value >>= 32;
        int vh = (int) value;
        b[3] = (byte) vh;
        vh >>= 8;
        b[2] = (byte) vh;
        vh >>= 8;
        b[1] = (byte) vh;
        vh >>= 8;
        b[0] = (byte) vh;
    }

}
