package net.bodz.bas.data.mem;

public interface RefType
        extends Type {

    @Override
    MemoryWrapOffset get(Memory memory, int offset)
            throws MemoryAccessException;

    Object getTarget(Memory memory, int offset)
            throws MemoryAccessException;

    void putTarget(Memory memory, int offset, Object value)
            throws MemoryAccessException;

}
