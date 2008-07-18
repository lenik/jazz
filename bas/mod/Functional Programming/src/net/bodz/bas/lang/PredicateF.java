package net.bodz.bas.lang;

public abstract class PredicateF<T> implements Functor<Boolean>, Predicate<T> {

    @SuppressWarnings("unchecked")
    @Override
    public final Boolean eval() throws EvalException {
        EvalContext context = EvalContext.getInstance();
        T o = (T) context.get(0);
        return eval(o);
    }

}
