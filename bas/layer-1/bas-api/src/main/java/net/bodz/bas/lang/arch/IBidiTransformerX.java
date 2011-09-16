package net.bodz.bas.lang.arch;

public interface IBidiTransformerX<S, T, X extends Exception>
        extends ITransformerX<S, T, X> {

    S untransform(T output)
            throws X;

}
