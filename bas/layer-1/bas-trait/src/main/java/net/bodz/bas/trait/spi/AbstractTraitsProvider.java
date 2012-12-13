package net.bodz.bas.trait.spi;

import net.bodz.bas.rtx.AbstractQueryProxy;
import net.bodz.bas.rtx.QueryException;

public abstract class AbstractTraitsProvider
        extends AbstractQueryProxy
        implements ITraitsProvider {

    @Override
    public <T> T query(Object obj, Class<T> specificationType)
            throws QueryException {
        if (obj instanceof Class) {
            Class<?> typeObj = (Class<?>) obj;
            return getTrait(typeObj, specificationType);
        } else if (obj != null) {
            Class<?> objType = (Class<?>) obj;
            return getTrait(objType, obj, specificationType);
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
    public <T> T getTrait(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        return null;
    }

    @Override
    public <T> T getTrait(Class<?> objType, Object obj, Class<T> traitsType)
            throws QueryException {
        return getTrait(objType, traitsType);
    }

}
