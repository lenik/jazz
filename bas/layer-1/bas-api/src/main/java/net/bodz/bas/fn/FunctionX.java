package net.bodz.bas.fn;

import java.util.function.Function;

/**
 * @see Function
 */
@FunctionalInterface
public interface FunctionX<T, R, X extends Throwable> {

    R apply(T t)
            throws X;

}
