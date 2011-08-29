package net.bodz.bas.lang.mi;

public abstract class AbstractQueryable
        implements IQueryable {

    @Override
    public <T> T query(Class<T> specificationType) {
        if (specificationType.isInstance(this))
            return specificationType.cast(this);
        return null;
    }

    @Override
    public Object query(String specificationId) {
        return null;
    }

    @Override
    public Object query(Object specification)
            throws QueryException {
        return null;
    }

}
