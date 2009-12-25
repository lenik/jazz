package net.bodz.bas.api.functor;

public abstract class Proc1<A> implements Func1<Void, A> {

    @Override
    public final Void eval(A a) {
        exec(a);
        return null;
    }

    public abstract void exec(A a);

}
