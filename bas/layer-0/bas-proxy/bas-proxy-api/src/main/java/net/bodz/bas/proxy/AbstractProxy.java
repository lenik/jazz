package net.bodz.bas.proxy;

public abstract class AbstractProxy<T>
        implements Proxy<T> {

    public final T proxy;

    public AbstractProxy(T proxy) {
        assert proxy != null : "null proxy";
        this.proxy = proxy;
    }

    @Override
    public T getProxyTarget() {
        return proxy;
    }

}
