package net.bodz.bas.lang;

public abstract class Filt1<T, A> implements Func1<T, A> {

    @Override
    public final T eval(A a) {
        return filter(a);
    }

    public abstract T filter(A a);

}
