package net.bodz.bas.mem;

public class UnionType extends _CompoundType {

    // Type

    @Override
    public Object get(Memory memory, int offset) {
        return null;
    }

    @Override
    public void put(Memory memory, int offset, Object value) {
    }

    // CompositeType

    @Override
    public Object get(int offset, int field) {
        return null;
    }

    @Override
    public void put(int offset, int field, Object value) {
    }

}
