package net.bodz.bas.model;

import net.bodz.bas.err.CreateException;

public abstract class AbstractFactory<T>
        implements IFactory<T> {

    @Override
    public abstract T _create(Class<?>[] argTypes, Object... args)
            throws CreateException;

    @Override
    public T create(Object... args)
            throws CreateException {
        return _create(null, args);
    }

    private static final Object[] emptyObjectArray = new Object[0];

    @Override
    public T create()
            throws CreateException {
        return _create(null, emptyObjectArray);
    }

}
