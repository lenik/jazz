package net.bodz.bas.closure;

/**
 * May be replaced by apache-commons in future.
 */
public interface ITransformer<T> {

    T transform(T input);

}
