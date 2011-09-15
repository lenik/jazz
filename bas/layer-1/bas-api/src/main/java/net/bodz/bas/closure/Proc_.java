package net.bodz.bas.closure;

public abstract class Proc_<V>
        implements Func_<Void, V> {

    @SafeVarargs
    @Override
    public final Void eval(V... args) {
        exec(args);
        return null;
    }

    @SuppressWarnings("unchecked")
    public abstract void exec(V... args);

}
