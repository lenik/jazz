package net.bodz.bas.util.arch;

/**
 * May be replaced by apache-commons in future.
 */
public interface ITransformerX<S, T, X extends Exception> {

    T transform(S input)
            throws X;

}
