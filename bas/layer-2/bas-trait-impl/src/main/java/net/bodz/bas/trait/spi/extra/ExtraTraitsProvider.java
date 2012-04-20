package net.bodz.bas.trait.spi.extra;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.trait.spi.AbstractTraitsProvider;

public class ExtraTraitsProvider
        extends AbstractTraitsProvider {

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    @Override
    public <T> T getTrait(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        throw new NotImplementedException();
    }

}
