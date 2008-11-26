package net.bodz.bas.lang;

public abstract class Pred_<A> implements Func1<Boolean, A> {

    @Override
    public final Boolean eval(A a) {
        return test(a);
    }

    public abstract boolean test(A a);

}
