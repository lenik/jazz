package net.bodz.bas.lang;

public abstract class Proc_<A> implements Func1<Void, A> {

    @Override
    public final Void eval(A a) {
        exec(a);
        return null;
    }

    public abstract boolean exec(A a);

}
