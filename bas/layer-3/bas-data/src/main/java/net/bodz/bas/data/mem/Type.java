package net.bodz.bas.data.mem;

public interface Type {

    Object get(IMemory memory, int offset)
            throws MemoryAccessException;

    void put(IMemory memory, int offset, Object value)
            throws MemoryAccessException;

    boolean isAbstract();

    Type specialize(Object param)
            throws MemoryAccessException;

}
