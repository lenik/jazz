package net.bodz.bas.traits;

import net.bodz.bas.lang.QueryException;

public abstract class AbstractTraitsProvider
        implements ITraitsProvider {

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    @Override
    public boolean isDefined() {
        return false;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Object obj, Class<T> traitsType)
            throws QueryException {
        return getTraits(objType, traitsType);
    }

}
