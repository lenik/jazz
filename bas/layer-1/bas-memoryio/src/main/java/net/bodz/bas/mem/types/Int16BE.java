package net.bodz.bas.mem.types;

import net.bodz.bas.mem.AccessException;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem._Type;
import net.bodz.bas.primitive.IntMath;

public class Int16BE extends _Type {

    @Override
    public Short get(Memory memory, int offset) throws AccessException {
        byte[] mem = new byte[2];
        memory.read(offset, mem);
        return read(mem);
    }

    @Override
    public void put(Memory memory, int offset, Object value) throws AccessException {
        byte[] mem = new byte[2];
        write(mem, (Short) value);
        memory.write(offset, mem);
    }

    public static short read(byte[] b) {
        int value = IntMath.unsign(b[0]);
        value <<= 8;
        value |= IntMath.unsign(b[1]);
        return (short) value;
    }

    public static void write(byte[] b, short value) {
        b[1] = (byte) value;
        value >>= 8;
        b[0] = (byte) value;
    }

}
