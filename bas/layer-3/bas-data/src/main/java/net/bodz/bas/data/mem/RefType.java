package net.bodz.bas.data.mem;

public interface RefType
        extends Type {

    @Override
    MemoryWrapOffset get(IMemory memory, int offset)
            throws MemoryAccessException;

    Object getTarget(IMemory memory, int offset)
            throws MemoryAccessException;

    void putTarget(IMemory memory, int offset, Object value)
            throws MemoryAccessException;

}
