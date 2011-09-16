package net.bodz.bas.api.proxy;

public abstract class Decorator<T>
        implements IDecorator<T> {

    protected T impl;

    public Decorator(T implementation) {
        this.impl = implementation;
    }

    @Override
    public T getImplementation() {
        return impl;
    }

}
