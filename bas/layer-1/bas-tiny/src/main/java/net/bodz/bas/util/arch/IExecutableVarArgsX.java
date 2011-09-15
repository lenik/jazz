package net.bodz.bas.util.arch;

public interface IExecutableVarArgsX<T, X extends Throwable> {

    void execute(@SuppressWarnings("unchecked") T... args)
            throws X;

}
