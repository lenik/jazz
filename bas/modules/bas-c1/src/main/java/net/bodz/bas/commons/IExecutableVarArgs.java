package net.bodz.bas.commons;

public interface IExecutableVarArgs<T, X extends Throwable> {

    void execute(T... args) throws X;

}
