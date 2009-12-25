package net.bodz.bas.closures.alt;

public interface Functor<T> {

    T eval() throws EvalException;

}
