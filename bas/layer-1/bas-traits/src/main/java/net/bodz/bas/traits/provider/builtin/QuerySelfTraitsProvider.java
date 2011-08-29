package net.bodz.bas.traits.provider.builtin;

import net.bodz.bas.lang.mi.IQueryable;
import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.traits.AbstractTraitsProvider;

/**
 * A simple direct cast traits providier.
 */
public class QuerySelfTraitsProvider
        extends AbstractTraitsProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.immediateQuery.getPriority();
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        return null;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Object obj, Class<T> traitsType)
            throws QueryException {
        if (obj instanceof IQueryable) {
            T traits = ((IQueryable) obj).query(traitsType);
            return traits;
        }
        return null;
    }
}
