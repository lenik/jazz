package net.bodz.bas.api.functor;

import java.util.concurrent.Callable;

/**
 * @see Callable
 */
public interface Func0<T> {

    T eval();

}
