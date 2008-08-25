package net.bodz.bas.mem;

public interface CompoundType extends Type {

    int indexOf(String fieldName);

    String nameOf(int fieldIndex);

    Type access(int field) throws AccessException;

    Object get(int offset, int field) throws AccessException;

    void put(int offset, int field, Object value) throws AccessException;

}
