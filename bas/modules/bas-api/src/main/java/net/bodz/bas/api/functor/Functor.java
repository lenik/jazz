package net.bodz.bas.api.functor;

public interface Functor<T> {

    T eval() throws EvalException;

}
