package net.bodz.bas.data.mem.types;

import net.bodz.bas.data.mem.AbstractRefType;
import net.bodz.bas.data.mem.Memory;
import net.bodz.bas.data.mem.MemoryAccessException;
import net.bodz.bas.data.mem.MemoryWrapOffset;
import net.bodz.bas.data.mem.Type;

public abstract class _Ptr32
        extends AbstractRefType {

    public _Ptr32(Type targetType)
            throws MemoryAccessException {
        super(targetType);
    }

    /**
     * @return absolute or relative address
     */
    public abstract int getAddress(Memory memory, int offset)
            throws MemoryAccessException;

    /**
     * @param addr
     *            absolute or relative address
     */
    public abstract void putAddress(Memory memory, int offset, int addr)
            throws MemoryAccessException;

    @Override
    public MemoryWrapOffset get(Memory memory, int offset)
            throws MemoryAccessException {
        int addr = getAddress(memory, offset);
        return new MemoryWrapOffset(memory, addr);
    }

    /**
     * @param targetOffset
     *            absolute address
     */
    @Override
    protected void putLocal(Memory memory, int offset, int targetOffset)
            throws MemoryAccessException {
        putAddress(memory, offset, targetOffset);
    }

    /**
     * @param targetOffset
     *            absolute address
     */
    @Override
    protected void putRemote(Memory memory, int offset, Memory targetMemory, int targetOffset)
            throws MemoryAccessException {
        throw new UnsupportedOperationException();
    }

}
