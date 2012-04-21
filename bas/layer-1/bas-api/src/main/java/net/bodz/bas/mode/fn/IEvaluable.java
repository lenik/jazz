package net.bodz.bas.mode.fn;

public interface IEvaluable<T> {

    T eval() throws EvalException;

}
