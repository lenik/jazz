package net.bodz.bas.closure;

public abstract class Pred_<V>
        implements Func_<Boolean, V> {

    @SafeVarargs
    @Override
    public final Boolean eval(V... args) {
        return test(args);
    }

    @SuppressWarnings("unchecked")
    public abstract boolean test(V... args);

}
