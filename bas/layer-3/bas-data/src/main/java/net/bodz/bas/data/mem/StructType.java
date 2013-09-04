package net.bodz.bas.data.mem;

public class StructType
        extends AbstractCompoundType {

    // Type

    @Override
    public Object get(IMemory memory, int offset) {
        return null;
    }

    @Override
    public void put(IMemory memory, int offset, Object value) {
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
