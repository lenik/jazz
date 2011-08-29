package net.bodz.bas.traits;

import net.bodz.bas.lang.mi.AbstractQueryProxy;
import net.bodz.bas.lang.mi.QueryException;

public abstract class AbstractTraitsProvider
        extends AbstractQueryProxy
        implements ITraitsProvider {

    @Override
    public <T> T query(Object obj, Class<T> specificationType)
            throws QueryException {
        if (obj instanceof Class) {
            Class<?> typeObj = (Class<?>) obj;
            return getTraits(typeObj, specificationType);
        } else if (obj != null) {
            Class<?> objType = (Class<?>) obj;
            return getTraits(objType, obj, specificationType);
        }
        return super.query(obj, specificationType);
    }

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    @Override
    public boolean isAggressive() {
        return false;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        return null;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Object obj, Class<T> traitsType)
            throws QueryException {
        return getTraits(objType, traitsType);
    }

}
