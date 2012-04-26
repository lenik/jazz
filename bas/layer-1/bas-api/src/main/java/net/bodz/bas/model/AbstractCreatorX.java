package net.bodz.bas.model;

public abstract class AbstractCreatorX<T, X extends Throwable>
        implements ICreatorX<T, X> {

    protected static final Object[] EMPTY_PARAMS = {};

    @Override
    public T create()
            throws X {
        return create(EMPTY_PARAMS);
    }

}
