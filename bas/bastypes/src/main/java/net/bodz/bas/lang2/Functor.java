package net.bodz.bas.lang2;

public interface Functor<T> {

    T eval() throws EvalException;

}
