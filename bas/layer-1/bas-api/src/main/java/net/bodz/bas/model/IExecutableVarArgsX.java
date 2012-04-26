package net.bodz.bas.model;

public interface IExecutableVarArgsX<T, X extends Throwable> {

    void execute(@SuppressWarnings("unchecked") T... args)
            throws X;

}
