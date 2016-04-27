package net.bodz.bas.rtx;

public abstract class AbstractQueryable
        implements IQueryable {

    @Override
    public <spec_t> spec_t query(Class<spec_t> clazz) {
        if (clazz.isInstance(this))
            return clazz.cast(this);
        return null;
    }

    @Override
    public Object query(String... args) {
        return null;
    }

}
