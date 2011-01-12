package net.bodz.bas.mem;

public abstract class _CompoundType extends _Type implements CompoundType {

    @Override
    public int indexOf(String fieldName) {
        return 0;
    }

    @Override
    public String nameOf(int fieldIndex) {
        return null;
    }

    @Override
    public Type access(int field) throws AccessException {
        return null;
    }

}
