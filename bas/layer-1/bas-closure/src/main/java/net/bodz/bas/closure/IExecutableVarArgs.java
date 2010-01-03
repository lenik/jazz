package net.bodz.bas.closure;

public interface IExecutableVarArgs<T, X extends Throwable> {

    void execute(T... args) throws X;

}
