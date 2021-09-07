package net.bodz.bas.fn;

public interface IBidiTransformerX<S, T, X extends Throwable> {

    T apply(S t)
            throws X;

    S unapply(T output)
            throws X;

}
