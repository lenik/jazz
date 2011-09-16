package net.bodz.bas.mem.types;

import net.bodz.bas.mem.AccessException;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem.Type;

public class AbsPtr32LE extends _Ptr32 {

    public AbsPtr32LE(Type targetType) throws AccessException {
        super(targetType);
    }

    @Override
    public int getAddress(Memory memory, int offset) throws AccessException {
        byte[] mem = new byte[4];
        memory.read(offset, mem);
        int addr = Int32LE.read(mem);
        return addr;
    }

    @Override
    public void putAddress(Memory memory, int offset, int addr) throws AccessException {
        byte[] mem = new byte[4];
        Int32LE.write(mem, addr);
        memory.write(offset, mem);
    }

    @Override
    public Object getTarget(Memory memory, int offset) throws AccessException {
        int addr = getAddress(memory, offset);
        return targetType.get(memory, addr);
    }

    @Override
    public void putTarget(Memory memory, int offset, Object value) throws AccessException {
        int addr = getAddress(memory, offset);
        targetType.put(memory, addr, value);
    }

}
