package net.bodz.bas.fn;

/**
 * May be replaced by apache-commons in future.
 */
public interface ITransformerX<S, T, Ex extends Exception> {

    T transform(S input)
            throws Ex;

}
