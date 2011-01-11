package net.bodz.bas.closure;

public interface IExecutableVarArgsX<T, X extends Throwable> {

    void execute(T... args)
            throws X;

}
