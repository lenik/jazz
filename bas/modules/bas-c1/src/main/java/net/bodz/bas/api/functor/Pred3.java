package net.bodz.bas.api.functor;

public abstract class Pred3<A, B, C> implements Func3<Boolean, A, B, C> {

    @Override
    public final Boolean eval(A a, B b, C c) {
        return test(a, b, c);
    }

    public abstract boolean test(A a, B b, C c);

}
