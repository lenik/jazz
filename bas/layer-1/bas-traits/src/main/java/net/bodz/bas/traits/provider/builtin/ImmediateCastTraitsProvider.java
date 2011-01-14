package net.bodz.bas.traits.provider.builtin;

import net.bodz.bas.lang.QueryException;
import net.bodz.bas.traits.provider.AbstractTraitsProvider;

/**
 * A simple direct cast traits providier.
 */
public class ImmediateCastTraitsProvider
        extends AbstractTraitsProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.PRIORITY_CAST;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        return null;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Object obj, Class<T> traitsType)
            throws QueryException {
        if (traitsType.isInstance(obj))
            return traitsType.cast(obj);
        return null;
    }

}
