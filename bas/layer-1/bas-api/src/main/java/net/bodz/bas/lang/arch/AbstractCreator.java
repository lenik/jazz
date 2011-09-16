package net.bodz.bas.lang.arch;

import net.bodz.bas.err.CreateException;

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
