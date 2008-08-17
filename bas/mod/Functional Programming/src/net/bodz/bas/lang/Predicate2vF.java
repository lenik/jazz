package net.bodz.bas.lang;

public abstract class Predicate2vF<T, U> implements Functor<Boolean>,
        Predicate2v<T, U> {

    @SuppressWarnings("unchecked")
    @Override
    public final Boolean eval() throws EvalException {
        EvalContext context = EvalContext.getInstance();
        T a = (T) context.get(0);
        U b = (U) context.get(1);
        return eval(a, b);
    }

}
