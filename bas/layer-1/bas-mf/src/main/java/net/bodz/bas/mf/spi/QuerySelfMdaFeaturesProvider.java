package net.bodz.bas.mf.spi;

import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryException;

/**
 * A simple direct cast mdaFeatures providier.
 */
public class QuerySelfMdaFeaturesProvider
        extends AbstractMdaFeaturesProvider {

    @Override
    public int getPriority() {
        return BuiltinProviderOrder.immediateQuery.getPriority();
    }

    @Override
    public boolean isAggressive() {
        return true;
    }

    @Override
    public <T> T getMdaFeature(Class<?> objType, Class<T> mdaFeatureType)
            throws QueryException {
        return null;
    }

    @Override
    public <T> T getMdaFeature(Class<?> objType, Object obj, Class<T> mdaFeatureType)
            throws QueryException {
        if (obj instanceof IQueryable) {
            T mdaFeatures = ((IQueryable) obj).query(mdaFeatureType);
            return mdaFeatures;
        }
        return null;
    }
}
