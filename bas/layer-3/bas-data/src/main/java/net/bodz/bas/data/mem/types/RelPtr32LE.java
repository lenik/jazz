package net.bodz.bas.data.mem.types;

import net.bodz.bas.data.mem.Memory;
import net.bodz.bas.data.mem.MemoryAccessException;
import net.bodz.bas.data.mem.Type;

public class RelPtr32LE
        extends _Ptr32 {

    public RelPtr32LE(Type targetType)
            throws MemoryAccessException {
        super(targetType);
    }

    @Override
    public int getAddress(Memory memory, int offset)
            throws MemoryAccessException {
        byte[] mem = new byte[4];
        memory.read(offset, mem);
        int addr = Int32LE.read(mem);
        return addr;
    }

    @Override
    public void putAddress(Memory memory, int offset, int addr)
            throws MemoryAccessException {
        byte[] mem = new byte[4];
        Int32LE.write(mem, addr);
        memory.write(offset, mem);
    }

    @Override
    public Object getTarget(Memory memory, int offset)
            throws MemoryAccessException {
        int addr = getAddress(memory, offset);
        return targetType.get(memory, addr);
    }

    @Override
    public void putTarget(Memory memory, int offset, Object value)
            throws MemoryAccessException {
        int addr = getAddress(memory, offset);
        targetType.put(memory, addr, value);
    }

}