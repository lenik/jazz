package net.bodz.bas.mode._fn;

public abstract class Proc1<A>
        implements Func1<Void, A> {

    @Override
    public final Void eval(A a) {
        exec(a);
        return null;
    }

    public abstract void exec(A a);

}
