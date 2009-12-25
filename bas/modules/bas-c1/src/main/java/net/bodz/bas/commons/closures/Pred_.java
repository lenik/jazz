package net.bodz.bas.commons.closures;

public abstract class Pred_<V> implements Func_<Boolean, V> {

    @Override
    public final Boolean eval(V... args) {
        return test(args);
    }

    public abstract boolean test(V... args);

}
