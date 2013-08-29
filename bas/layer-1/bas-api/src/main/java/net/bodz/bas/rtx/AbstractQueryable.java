package net.bodz.bas.rtx;

public abstract class AbstractQueryable
        implements IQueryable {

    @Override
    public Object query(Object specification)
            throws QueryException {
        if (specification instanceof Class<?>)
            return query((Class<?>) specification);
        if (specification instanceof String)
            return query((String) specification);
        return null;
    }

    @Override
    public <S> S query(Class<S> specificationType) {
        if (specificationType.isInstance(this))
            return specificationType.cast(this);
        return null;
    }

    @Override
    public Object query(String specificationId) {
        return null;
    }

}
