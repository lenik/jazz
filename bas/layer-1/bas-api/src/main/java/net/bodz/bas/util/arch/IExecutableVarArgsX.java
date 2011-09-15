package net.bodz.bas.util.arch;

public interface IExecutableVarArgsX<T, X extends Throwable> {

    @SuppressWarnings("unchecked")
    void execute(T... args)
            throws X;

}
