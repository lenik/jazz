package net.bodz.bas.util;

import net.bodz.bas.commons.exceptions.CreateException;
import net.bodz.bas.types.util.Empty;

public abstract class _Factory<T> implements Factory<T> {

    @Override
    public abstract T _create(Class<?>[] argTypes, Object... args) throws CreateException;

    @Override
    public T create(Object... args) throws CreateException {
        return _create(null, args);
    }

    @Override
    public T create() throws CreateException {
        return _create(null, Empty.Objects);
    }

}
