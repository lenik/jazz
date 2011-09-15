package net.bodz.bas.util.arch.eval;

public interface IEvaluable<T> {

    T eval() throws EvalException;

}
