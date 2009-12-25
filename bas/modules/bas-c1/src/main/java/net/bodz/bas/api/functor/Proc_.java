package net.bodz.bas.api.functor;

public abstract class Proc_<V> implements Func_<Void, V> {

    @Override
    public final Void eval(V... args) {
        exec(args);
        return null;
    }

    public abstract void exec(V... args);

}
