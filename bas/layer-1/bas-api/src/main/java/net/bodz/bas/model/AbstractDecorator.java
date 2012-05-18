package net.bodz.bas.model;

import java.io.Serializable;

public abstract class AbstractDecorator<T>
        implements IWrapper<T>, Serializable {

    private static final long serialVersionUID = 1L;

    protected T _orig;

    public AbstractDecorator(T _orig) {
        if (_orig == null)
            throw new NullPointerException("_orig");
        this._orig = _orig;
    }

    @Override
    public T getWrapped() {
        return _orig;
    }

}
