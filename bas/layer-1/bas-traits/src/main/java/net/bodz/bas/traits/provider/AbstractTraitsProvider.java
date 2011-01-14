package net.bodz.bas.traits.provider;

import net.bodz.bas.lang.QueryException;

public abstract class AbstractTraitsProvider
        implements ITraitsProvider {

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Object obj, Class<T> traitsType)
            throws QueryException {
        return getTraits(objType, traitsType);
    }

}
