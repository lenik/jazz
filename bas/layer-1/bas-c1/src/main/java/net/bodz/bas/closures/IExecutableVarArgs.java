package net.bodz.bas.closures;

public interface IExecutableVarArgs<T, X extends Throwable> {

    void execute(T... args) throws X;

}
