package net.bodz.bas.mem.types;

import net.bodz.bas.mem.AccessException;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem._Type;
import net.bodz.bas.meta.codereview.ThreadUnsafe;

public class Int8 extends _Type {

    @ThreadUnsafe
    @Override
    public Byte get(Memory memory, int offset) throws AccessException {
        return memory.read(offset);
    }

    @ThreadUnsafe
    @Override
    public void put(Memory memory, int offset, Object value) throws AccessException {
        memory.write(offset, (Byte) value);
    }

}
