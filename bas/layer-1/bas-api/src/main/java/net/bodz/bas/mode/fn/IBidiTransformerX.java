package net.bodz.bas.mode.fn;

public interface IBidiTransformerX<S, T, X extends Exception>
        extends ITransformerX<S, T, X> {

    S untransform(T output)
            throws X;

}
