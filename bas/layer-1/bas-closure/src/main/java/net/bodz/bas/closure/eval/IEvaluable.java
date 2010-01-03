package net.bodz.bas.closure.eval;

public interface IEvaluable<T> {

    T eval() throws EvalException;

}
