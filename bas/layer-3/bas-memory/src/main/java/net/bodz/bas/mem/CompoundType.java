package net.bodz.bas.mem;

public interface CompoundType
        extends Type {

    int indexOf(String fieldName);

    String nameOf(int fieldIndex);

    Type access(int field)
            throws MemoryAccessException;

    Object get(int offset, int field)
            throws MemoryAccessException;

    void put(int offset, int field, Object value)
            throws MemoryAccessException;

}
