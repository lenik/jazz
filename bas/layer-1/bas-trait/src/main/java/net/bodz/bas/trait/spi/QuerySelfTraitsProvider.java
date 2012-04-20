package net.bodz.bas.trait.spi;

import net.bodz.bas.lang.mi.IQueryable;
import net.bodz.bas.lang.mi.QueryException;

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
    public <T> T getTrait(Class<?> objType, Class<T> traitType)
            throws QueryException {
        return null;
    }

    @Override
    public <T> T getTrait(Class<?> objType, Object obj, Class<T> traitType)
            throws QueryException {
        if (obj instanceof IQueryable) {
            T traits = ((IQueryable) obj).query(traitType);
            return traits;
        }
        return null;
    }
}
