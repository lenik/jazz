package net.bodz.bas.fn;

public interface IExecutableVarArgsX<T, X extends Throwable> {

    int execute(@SuppressWarnings("unchecked") T... args)
            throws X;

}
