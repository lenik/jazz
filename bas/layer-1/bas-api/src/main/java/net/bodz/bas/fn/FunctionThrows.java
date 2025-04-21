package net.bodz.bas.fn;

public interface FunctionThrows<R, T, X extends Throwable> {

    R apply(T input)
            throws X;

}
