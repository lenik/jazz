package net.bodz.bas.data.mem.types;

import net.bodz.bas.data.mem.AbstractRefType;
import net.bodz.bas.data.mem.IMemory;
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
    public abstract int getAddress(IMemory memory, int offset)
            throws MemoryAccessException;

    /**
     * @param addr
     *            absolute or relative address
     */
    public abstract void putAddress(IMemory memory, int offset, int addr)
            throws MemoryAccessException;

    @Override
    public MemoryWrapOffset get(IMemory memory, int offset)
            throws MemoryAccessException {
        int addr = getAddress(memory, offset);
        return new MemoryWrapOffset(memory, addr);
    }

    /**
     * @param targetOffset
     *            absolute address
     */
    @Override
    protected void putLocal(IMemory memory, int offset, int targetOffset)
            throws MemoryAccessException {
        putAddress(memory, offset, targetOffset);
    }

    /**
     * @param targetOffset
     *            absolute address
     */
    @Override
    protected void putRemote(IMemory memory, int offset, IMemory targetMemory, int targetOffset)
            throws MemoryAccessException {
        throw new UnsupportedOperationException();
    }

}
