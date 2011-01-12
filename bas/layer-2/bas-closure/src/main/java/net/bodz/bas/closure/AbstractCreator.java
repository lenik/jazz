package net.bodz.bas.closure;

import net.bodz.bas.util.exception.CreateException;

public abstract class AbstractCreator<T>
        implements ICreator<T> {

    @Override
    public T create(Object... parameters)
            throws CreateException {
        if (parameters.length == 0)
            return create();
        throw new IllegalArgumentException();
    }

}
