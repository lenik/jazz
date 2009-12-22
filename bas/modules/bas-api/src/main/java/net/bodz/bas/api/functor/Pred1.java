package net.bodz.bas.api.functor;

public abstract class Pred1<A> implements Func1<Boolean, A> {

    @Override
    public final Boolean eval(A a) {
        return test(a);
    }

    public abstract boolean test(A a);

}
