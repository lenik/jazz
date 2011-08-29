package net.bodz.bas.api.proxy;

public abstract class AbstractProxy<T>
        implements Proxy<T> {

    protected T proxy;

    public AbstractProxy(T proxy) {
        this.proxy = proxy;
    }

    @Override
    public T getProxyTarget() {
        return proxy;
    }

}
