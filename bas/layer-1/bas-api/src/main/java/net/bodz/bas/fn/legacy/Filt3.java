package net.bodz.bas.fn.legacy;

public abstract class Filt3<T, A, B, C>
        implements Func3<T, A, B, C> {

    @Override
    public final T eval(A a, B b, C c) {
        return filter(a, b, c);
    }

    public abstract T filter(A a, B b, C c);

}
