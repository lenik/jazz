package net.bodz.bas.api.types;

public interface ExecutableVarArgs<T, X extends Throwable> {

    void execute(T... args) throws X;

}
