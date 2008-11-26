package net.bodz.bas.lang;

public interface Func_<T, V> {

    T eval(V... args) throws EvalException;

}
