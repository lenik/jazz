package net.bodz.bas.closure.alt;

public abstract class Proc2<A, B> implements Func2<Void, A, B> {

    @Override
    public final Void eval(A a, B b) {
        exec(a, b);
        return null;
    }

    public abstract void exec(A a, B b);

}
