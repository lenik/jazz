package net.bodz.bas.commons.closures;

public interface Functor<T> {

    T eval() throws EvalException;

}
