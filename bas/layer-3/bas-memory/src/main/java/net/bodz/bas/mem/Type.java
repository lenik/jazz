package net.bodz.bas.mem;

public interface Type {

    Object get(Memory memory, int offset)
            throws MemoryAccessException;

    void put(Memory memory, int offset, Object value)
            throws MemoryAccessException;

    boolean isAbstract();

    Type specialize(Object param)
            throws MemoryAccessException;

}
