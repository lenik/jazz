package net.bodz.bas.trait.spi.extra;

import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.trait.spi.AbstractTraitsProvider;

public class ExtraTraitsProvider
        extends AbstractTraitsProvider {

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    /**
     * TODO Not implemented.
     */
    @Override
    public <T> T getTrait(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        return null;
    }

}
