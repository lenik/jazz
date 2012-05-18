package net.bodz.bas.model;

public abstract class AbstractProxy<T>
        implements IWrapper<T> {

    protected T _orig;

    protected AbstractProxy() {
    }

    public AbstractProxy(T _orig) {
        this._orig = _orig;
    }

    @Override
    public T getWrapped() {
        return _orig;
    }

}
