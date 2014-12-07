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
    public <spec_t> spec_t query(Class<spec_t> specificationClass) {
        if (specificationClass.isInstance(this))
            return specificationClass.cast(this);
        return null;
    }

    @Override
    public Object query(String specificationId) {
        return null;
    }

}
