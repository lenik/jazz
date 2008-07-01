package net.bodz.bas.lang;

public interface Functor<T> {

    T eval() throws EvalException;

}
