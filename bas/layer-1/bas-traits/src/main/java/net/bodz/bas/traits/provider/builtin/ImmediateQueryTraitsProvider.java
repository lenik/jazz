package net.bodz.bas.traits.provider.builtin;

import net.bodz.bas.lang.IQueryable;
import net.bodz.bas.lang.QueryException;
import net.bodz.bas.traits.AbstractTraitsProvider;

/**
 * A simple direct cast traits providier.
 */
public class ImmediateQueryTraitsProvider
        extends AbstractTraitsProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.immediateQuery.getPriority();
    }

    @Override
    public boolean isDefined() {
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
