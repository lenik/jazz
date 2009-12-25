package net.bodz.bas.commons.hint;


public abstract class AbstractProxy<T>
        implements Proxy<T> {

    public final T proxy;

    public AbstractProxy(T proxy) {
        assert proxy != null : "null proxy"; //$NON-NLS-1$
        this.proxy = proxy;
    }

    @Override
    public T getProxyTarget() {
        return proxy;
    }

}
