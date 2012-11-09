package net.bodz.bas.mem.types;

import net.bodz.bas.mem.AbstractType;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem.MemoryAccessException;
import net.bodz.bas.meta.decl.ThreadUnsafe;

public class Int8
        extends AbstractType {

    @ThreadUnsafe
    @Override
    public Byte get(Memory memory, int offset)
            throws MemoryAccessException {
        return memory.read(offset);
    }

    @ThreadUnsafe
    @Override
    public void put(Memory memory, int offset, Object value)
            throws MemoryAccessException {
        memory.write(offset, (Byte) value);
    }

}
