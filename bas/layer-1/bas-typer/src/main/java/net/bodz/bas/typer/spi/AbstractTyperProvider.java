package net.bodz.bas.typer.spi;

import net.bodz.bas.rtx.QueryException;

public abstract class AbstractTyperProvider
        implements ITyperProvider {

    @Override
    public <T> T query(Object obj, Class<T> specificationType)
            throws QueryException {
        if (obj instanceof Class) {
            Class<?> typeObj = (Class<?>) obj;
            return getTyper(typeObj, specificationType);
        } else if (obj != null) {
            Class<?> objType = (Class<?>) obj;
            return getTyper(objType, obj, specificationType);
        }
        return ITyperProvider.super.query(obj, specificationType);
    }

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    @Override
    public boolean isAggressive() {
        return false;
    }

}
