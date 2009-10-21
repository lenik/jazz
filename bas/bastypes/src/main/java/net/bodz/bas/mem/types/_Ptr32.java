package net.bodz.bas.mem.types;

import net.bodz.bas.mem.AccessException;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem.MemoryWrapOffset;
import net.bodz.bas.mem.Type;
import net.bodz.bas.mem._RefType;

public abstract class _Ptr32 extends _RefType {

    public _Ptr32(Type targetType) throws AccessException {
        super(targetType);
    }

    /**
     * @return absolute or relative address
     */
    public abstract int getAddress(Memory memory, int offset) throws AccessException;

    /**
     * @param addr
     *            absolute or relative address
     */
    public abstract void putAddress(Memory memory, int offset, int addr) throws AccessException;

    @Override
    public MemoryWrapOffset get(Memory memory, int offset) throws AccessException {
        int addr = getAddress(memory, offset);
        return new MemoryWrapOffset(memory, addr);
    }

    /**
     * @param targetOffset
     *            absolute address
     */
    @Override
    protected void putLocal(Memory memory, int offset, int targetOffset) throws AccessException {
        putAddress(memory, offset, targetOffset);
    }

    /**
     * @param targetOffset
     *            absolute address
     */
    @Override
    protected void putRemote(Memory memory, int offset, Memory targetMemory, int targetOffset)
            throws AccessException {
        throw new UnsupportedOperationException();
    }

}
