package net.bodz.bas.lang2;

public abstract class Filt_<T, V> implements Func_<T, V> {

    @Override
    public final T eval(V... args) {
        return filter(args);
    }

    public abstract T filter(V... args);

}
