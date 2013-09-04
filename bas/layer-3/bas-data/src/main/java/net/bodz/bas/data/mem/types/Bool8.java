package net.bodz.bas.data.mem.types;

import net.bodz.bas.data.mem.AbstractType;
import net.bodz.bas.data.mem.IMemory;
import net.bodz.bas.data.mem.MemoryAccessException;

public class Bool8
        extends AbstractType {

    public static final byte TRUE = 1;
    public static final byte FALSE = 0;

    @Override
    public Boolean get(IMemory memory, int offset)
            throws MemoryAccessException {
        return memory.read(offset) != 0;
    }

    @Override
    public void put(IMemory memory, int offset, Object value)
            throws MemoryAccessException {
        if ((Boolean) value)
            memory.write(offset, TRUE);
        else
            memory.write(offset, FALSE);
    }

}
