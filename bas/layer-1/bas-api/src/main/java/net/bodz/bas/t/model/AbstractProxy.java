package net.bodz.bas.t.model;

import net.bodz.bas.meta.decl.NotNull;

/**
 * Proxy Pattern:
 * 
 * Proxy could be used when you want to lazy-instantiate an object, or hide the fact that you're
 * calling a remote service, or control access to the object.
 */
public abstract class AbstractProxy<T>
        implements IWrapper<T> {

    protected T _orig;

    protected AbstractProxy() {
    }

    public AbstractProxy(T _orig) {
        this._orig = _orig;
    }

    @NotNull
    @Override
    public T getWrapped() {
        return _orig;
    }

}
