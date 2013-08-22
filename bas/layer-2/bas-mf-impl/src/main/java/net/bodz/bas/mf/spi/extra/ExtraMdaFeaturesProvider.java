package net.bodz.bas.mf.spi.extra;

import net.bodz.bas.mf.spi.AbstractMdaFeaturesProvider;
import net.bodz.bas.rtx.QueryException;

public class ExtraMdaFeaturesProvider
        extends AbstractMdaFeaturesProvider {

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    /**
     * TODO Not implemented.
     */
    @Override
    public <T> T getMdaFeature(Class<?> objType, Class<T> mdaFeaturesType)
            throws QueryException {
        return null;
    }

}
