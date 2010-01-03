package net.bodz.bas.closures.alt;

public abstract class Proc_<V> implements Func_<Void, V> {

    @Override
    public final Void eval(V... args) {
        exec(args);
        return null;
    }

    public abstract void exec(V... args);

}
