package net.bodz.bas.lang;

public class AbstractQueryProxy
        implements IQueryProxy {

    @Override
    public Object query(Object obj, Object specification)
            throws QueryException {
        if (obj instanceof IQueryable) {
            IQueryable queryable = (IQueryable) obj;
            return queryable.query(specification);
        }
        return null;
    }

    @Override
    public Object query(Object obj, String specificationId)
            throws QueryException {
        if (obj instanceof IQueryable) {
            IQueryable queryable = (IQueryable) obj;
            return queryable.query(specificationId);
        }
        return null;
    }

    @Override
    public <T> T query(Object obj, Class<T> specificationType)
            throws QueryException {
        if (obj instanceof IQueryable) {
            IQueryable queryable = (IQueryable) obj;
            return queryable.query(specificationType);
        }
        return null;
    }

}
