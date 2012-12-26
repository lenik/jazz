package net.bodz.bas.data.mem;

public abstract class AbstractType
        implements Type {

    protected void load() {
    }

    @Override
    public boolean isAbstract() {
        return false;
    }

    @Override
    public Type specialize(Object param)
            throws MemoryAccessException {
        return this;
    }

}
