package net.bodz.bas.api.types;

public interface IExecutableVarArgs<T, X extends Throwable> {

    void execute(T... args) throws X;

}
