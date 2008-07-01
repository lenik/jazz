package net.bodz.bas.proxy;

public class Proxy<T> {

    public final T proxy;

    public Proxy(T proxy) {
        assert proxy != null : "null proxy";
        this.proxy = proxy;
    }

}
