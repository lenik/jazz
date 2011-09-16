package net.bodz.bas.closure;

public abstract class Proc_<V>
        implements Func_<Void, V> {

    @SafeVarargs
    @Override
    public final Void eval(V... args) {
        exec(args);
        return null;
    }

    public abstract void exec(@SuppressWarnings("unchecked") V... args);

}
