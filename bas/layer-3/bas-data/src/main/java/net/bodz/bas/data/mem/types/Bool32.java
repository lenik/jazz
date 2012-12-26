package net.bodz.bas.data.mem.types;

import net.bodz.bas.data.mem.AbstractType;
import net.bodz.bas.data.mem.Memory;
import net.bodz.bas.data.mem.MemoryAccessException;

public class Bool32
        extends AbstractType {

    public static final byte[] TRUE = { 0, 0, 0, 1 };
    public static final byte[] FALSE = { 0, 0, 0, 0 };

    @Override
    public Boolean get(Memory memory, int offset)
            throws MemoryAccessException {
        byte[] mem = new byte[4];
        memory.read(offset, mem);
        return mem[0] != 0 || mem[1] != 0 || mem[2] != 0 || mem[3] != 0;
    }

    @Override
    public void put(Memory memory, int offset, Object value)
            throws MemoryAccessException {
        if ((Boolean) value)
            memory.write(offset, TRUE);
        else
            memory.write(offset, FALSE);
    }

}
