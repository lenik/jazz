package net.bodz.bas.lang;

public abstract class ClosureF<T> implements Functor<Object>, Closure<T> {

    @SuppressWarnings("unchecked")
    @Override
    public Object eval() throws EvalException {
        EvalContext context = EvalContext.getInstance();
        T o = (T) context.get(0);
        execute(o);
        return null;
    }

}
