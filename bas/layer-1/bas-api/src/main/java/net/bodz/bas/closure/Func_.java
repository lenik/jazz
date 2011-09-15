package net.bodz.bas.closure;

public interface Func_<T, V> {

    @SuppressWarnings("unchecked")
    T eval(V... args);

}
