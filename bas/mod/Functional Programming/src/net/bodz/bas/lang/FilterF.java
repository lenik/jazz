package net.bodz.bas.lang;

public abstract class FilterF<T> implements Functor<T>, Filter<T, Object> {

    @Override
    public T eval() throws EvalException {
        EvalContext context = EvalContext.getInstance();
        Object o = context.get(0);
        return filter(o);
    }

}
