package net.bodz.bas.mem;

public interface RefType extends Type {

    @Override
    MemoryWrapOffset get(Memory memory, int offset) throws AccessException;

    Object getTarget(Memory memory, int offset) throws AccessException;

    void putTarget(Memory memory, int offset, Object value) throws AccessException;

}
