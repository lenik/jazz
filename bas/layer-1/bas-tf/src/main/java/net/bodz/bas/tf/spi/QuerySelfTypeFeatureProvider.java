package net.bodz.bas.tf.spi;

import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;

/**
 * This provider simply cast this object to specific type feature class.
 */
public class QuerySelfTypeFeatureProvider
        extends AbstractTypeFeatureProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.immediateQuery.getPriority();
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public <T> T getTypeFeature(Class<?> objType, Class<T> typeFeatureClass)
            throws QueryException {
        return null;
    }

    @Override
    public <T> T getTypeFeature(Class<?> objType, Object obj, Class<T> typeFeatureClass)
            throws QueryException {
        if (obj instanceof IQueryable) {
            T typeFeatures = ((IQueryable) obj).query(typeFeatureClass);
            return typeFeatures;
        }
        return null;
    }
}
