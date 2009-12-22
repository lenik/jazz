package net.bodz.bas.api.functor;

public abstract class Filt2<T, A, B> implements Func2<T, A, B> {

    @Override
    public final T eval(A a, B b) {
        return filter(a, b);
    }

    public abstract T filter(A a, B b);

}
