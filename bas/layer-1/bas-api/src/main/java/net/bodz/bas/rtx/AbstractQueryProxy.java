package net.bodz.bas.rtx;

public class AbstractQueryProxy
        implements IQueryProxy {

    @Override
    public <T> T query(Object obj, Class<T> specificationType)
            throws QueryException {
        if (obj instanceof IQueryable) {
            IQueryable queryable = (IQueryable) obj;
            return queryable.query(specificationType);
        }
        return null;
    }

    @Override
    public Object query(Object obj, String... args)
            throws QueryException {
        if (obj instanceof IQueryable) {
            IQueryable queryable = (IQueryable) obj;
            return queryable.query(args);
        }
        return null;
    }

}
