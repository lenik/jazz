package net.bodz.bas.rtx;

import java.util.ArrayList;

public class QueryableUnion
        extends ArrayList<IQueryable>
        implements IQueryable {

    private static final long serialVersionUID = 1L;

    @Override
    public Object query(Object specification)
            throws QueryException {
        for (IQueryable q : this) {
            Object impl = q.query(specification);
            if (impl != null)
                return impl;
        }
        return null;
    }

    @Override
    public Object query(String specificationId)
            throws QueryException {
        for (IQueryable q : this) {
            Object impl = q.query(specificationId);
            if (impl != null)
                return impl;
        }
        return null;
    }

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        for (IQueryable q : this) {
            spec_t impl = q.query(specificationType);
            if (impl != null)
                return impl;
        }
        return null;
    }

}
