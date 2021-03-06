package net.bodz.bas.fn.legacy;

public abstract class Filt_<T, V>
        implements Func_<T, V> {

    @SafeVarargs
    @Override
    public final T eval(V... args) {
        return filter(args);
    }

    public abstract T filter(@SuppressWarnings("unchecked") V... args);

}
