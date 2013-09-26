package net.bodz.bas.t.model;

import java.io.Serializable;

/**
 * Decorator Pattern:
 * 
 * Decorator is also called "Smart Proxy." This is used when you want to add functionality to an
 * object, but not by extending that object's type. This allows you to do so at runtime.
 */
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
