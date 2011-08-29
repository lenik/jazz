package net.bodz.bas.closure;

public abstract class Filt4<T, A, B, C, D>
        implements Func4<T, A, B, C, D> {

    @Override
    public final T eval(A a, B b, C c, D d) {
        return filter(a, b, c, d);
    }

    public abstract T filter(A a, B b, C c, D d);

}
