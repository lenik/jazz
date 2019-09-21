package net.bodz.bas.t.factory;

public abstract class AbstractFactoryX<T, X extends Throwable>
        implements IFactoryX<T, X> {

    protected static final Object[] EMPTY_PARAMS = {};

    @Override
    public T create()
            throws X {
        return _create(null, EMPTY_PARAMS);
    }

    @Override
    public T create(Object... args)
            throws X {
        return _create(null, args);
    }

}
