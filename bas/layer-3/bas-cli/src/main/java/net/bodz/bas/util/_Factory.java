package net.bodz.bas.util;

import net.bodz.bas.exceptions.CreateException;

import org.apache.commons.lang.ArrayUtils;

public abstract class _Factory<T>
        implements Factory<T> {

    @Override
    public abstract T _create(Class<?>[] argTypes, Object... args)
            throws CreateException;

    @Override
    public T create(Object... args)
            throws CreateException {
        return _create(null, args);
    }

    @Override
    public T create()
            throws CreateException {
        return _create(null, ArrayUtils.EMPTY_OBJECT_ARRAY);
    }

}
