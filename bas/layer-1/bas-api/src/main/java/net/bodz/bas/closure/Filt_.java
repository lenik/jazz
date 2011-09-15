package net.bodz.bas.closure;

public abstract class Filt_<T, V>
        implements Func_<T, V> {

    @SafeVarargs
    @Override
    public final T eval(V... args) {
        return filter(args);
    }

    @SuppressWarnings("unchecked")
    public abstract T filter(V... args);

}
