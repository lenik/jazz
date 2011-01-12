package net.bodz.bas.mem;

public interface Type {

    Object get(Memory memory, int offset) throws AccessException;

    void put(Memory memory, int offset, Object value) throws AccessException;

    boolean isAbstract();

    Type specialize(Object param) throws AccessException;

}
