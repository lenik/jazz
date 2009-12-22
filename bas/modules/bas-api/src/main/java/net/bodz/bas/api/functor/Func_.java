package net.bodz.bas.api.functor;

public interface Func_<T, V> {

    T eval(V... args);

}
