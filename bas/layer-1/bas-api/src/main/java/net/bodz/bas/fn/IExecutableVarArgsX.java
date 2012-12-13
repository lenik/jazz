package net.bodz.bas.fn;

public interface IExecutableVarArgsX<T, X extends Throwable> {

    void execute(@SuppressWarnings("unchecked") T... args)
            throws X;

}
