package net.bodz.bas.tf.spi;

import net.bodz.bas.rtx.AbstractQueryProxy;
import net.bodz.bas.rtx.QueryException;

public abstract class AbstractTypeFeatureProvider
        extends AbstractQueryProxy
        implements ITypeFeatureProvider {

    @Override
    public <T> T query(Object obj, Class<T> specificationType)
            throws QueryException {
        if (obj instanceof Class) {
            Class<?> typeObj = (Class<?>) obj;
            return getTypeFeature(typeObj, specificationType);
        } else if (obj != null) {
            Class<?> objType = (Class<?>) obj;
            return getTypeFeature(objType, obj, specificationType);
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

}
