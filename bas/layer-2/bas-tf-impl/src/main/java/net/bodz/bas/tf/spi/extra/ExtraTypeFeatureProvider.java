package net.bodz.bas.tf.spi.extra;

import net.bodz.bas.tf.spi.AbstractTypeFeatureProvider;
import net.bodz.bas.rtx.QueryException;

public class ExtraTypeFeatureProvider
        extends AbstractTypeFeatureProvider {

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    /**
     * TODO Not implemented.
     */
    @Override
    public <T> T getTypeFeature(Class<?> objType, Class<T> typeFeatureClass)
            throws QueryException {
        return null;
    }

    @Override
    public <T> T getTypeFeature(Class<?> objType, Object obj, Class<T> typeFeatureClass)
            throws QueryException {
        return null;
    }

}
