package net.bodz.bas.fn.legacy;

public abstract class Pred_<V>
        implements Func_<Boolean, V> {

    @SafeVarargs
    @Override
    public final Boolean eval(V... args) {
        return test(args);
    }

    public abstract boolean test(@SuppressWarnings("unchecked") V... args);

}
