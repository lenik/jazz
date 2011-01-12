package net.bodz.bas.closure.alt;

public abstract class Pred2<A, B> implements Func2<Boolean, A, B> {

    @Override
    public final Boolean eval(A a, B b) {
        return test(a, b);
    }

    public abstract boolean test(A a, B b);

}
