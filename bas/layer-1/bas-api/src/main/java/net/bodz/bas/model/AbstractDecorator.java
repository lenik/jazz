package net.bodz.bas.model;

import java.io.Serializable;

public abstract class AbstractDecorator<T>
        implements IDecorator<T>, Serializable {

    private static final long serialVersionUID = 1L;

    protected T impl;

    public AbstractDecorator(T implementation) {
        this.impl = implementation;
    }

    @Override
    public T getImplementation() {
        return impl;
    }

}
