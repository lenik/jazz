package net.bodz.bas.lang;

public abstract class PredicateF implements Functor<Boolean>, Predicate {

    @Override
    public final Boolean eval() throws EvalException {
        EvalContext context = EvalContext.getInstance();
        Object o = context.get(0);
        return eval(o);
    }

}
