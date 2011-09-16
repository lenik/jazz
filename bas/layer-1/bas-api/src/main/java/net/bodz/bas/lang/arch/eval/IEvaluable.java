package net.bodz.bas.lang.arch.eval;

public interface IEvaluable<T> {

    T eval() throws EvalException;

}
