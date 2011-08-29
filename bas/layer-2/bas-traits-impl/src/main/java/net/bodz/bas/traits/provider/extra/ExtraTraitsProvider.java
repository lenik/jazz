package net.bodz.bas.traits.provider.extra;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.traits.AbstractTraitsProvider;

public class ExtraTraitsProvider
        extends AbstractTraitsProvider {

    @Override
    public int getPriority() {
        return PRIORITY_NORMAL;
    }

    @Override
    public <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        throw new NotImplementedException();
    }

}
