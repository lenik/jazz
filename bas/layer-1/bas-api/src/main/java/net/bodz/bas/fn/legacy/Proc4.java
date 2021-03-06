package net.bodz.bas.fn.legacy;

public abstract class Proc4<A, B, C, D>
        implements Func4<Void, A, B, C, D> {

    @Override
    public final Void eval(A a, B b, C c, D d) {
        exec(a, b, c, d);
        return null;
    }

    public abstract void exec(A a, B b, C c, D d);

}
