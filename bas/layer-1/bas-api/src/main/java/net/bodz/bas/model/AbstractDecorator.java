package net.bodz.bas.model;

public abstract class AbstractDecorator<T>
        implements IDecorator<T> {

    protected T impl;

    public AbstractDecorator(T implementation) {
        this.impl = implementation;
    }

    @Override
    public T getImplementation() {
        return impl;
    }

}
