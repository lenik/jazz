package net.bodz.bas.mf.spi;

import net.bodz.bas.rtx.AbstractQueryProxy;
import net.bodz.bas.rtx.QueryException;

public abstract class AbstractMdaFeaturesProvider
        extends AbstractQueryProxy
        implements IMdaFeaturesProvider {

    @Override
    public <T> T query(Object obj, Class<T> specificationType)
            throws QueryException {
        if (obj instanceof Class) {
            Class<?> typeObj = (Class<?>) obj;
            return getMdaFeature(typeObj, specificationType);
        } else if (obj != null) {
            Class<?> objType = (Class<?>) obj;
            return getMdaFeature(objType, obj, specificationType);
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
    public <T> T getMdaFeature(Class<?> objType, Class<T> mdaFeaturesType)
            throws QueryException {
        return null;
    }

    @Override
    public <T> T getMdaFeature(Class<?> objType, Object obj, Class<T> mdaFeaturesType)
            throws QueryException {
        return getMdaFeature(objType, mdaFeaturesType);
    }

}
