package net.bodz.bas.closures.alt;

public abstract class Proc3<A, B, C> implements Func3<Void, A, B, C> {

    @Override
    public final Void eval(A a, B b, C c) {
        exec(a, b, c);
        return null;
    }

    public abstract void exec(A a, B b, C c);

}
