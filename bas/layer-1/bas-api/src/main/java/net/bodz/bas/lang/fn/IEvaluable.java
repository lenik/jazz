package net.bodz.bas.lang.fn;

public interface IEvaluable<T>
// extends Func0<T>
{

    T eval()
            throws EvalException;

}
