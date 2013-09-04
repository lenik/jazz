package net.bodz.bas.data.mem.types;

import net.bodz.bas.data.mem.AbstractType;
import net.bodz.bas.data.mem.IMemory;
import net.bodz.bas.data.mem.MemoryAccessException;
import net.bodz.bas.meta.decl.ThreadUnsafe;

public class Int8
        extends AbstractType {

    @ThreadUnsafe
    @Override
    public Byte get(IMemory memory, int offset)
            throws MemoryAccessException {
        return memory.read(offset);
    }

    @ThreadUnsafe
    @Override
    public void put(IMemory memory, int offset, Object value)
            throws MemoryAccessException {
        memory.write(offset, (Byte) value);
    }

}
