package net.bodz.bas.closure;

public interface ExecutableWithParameter<T, X extends Throwable> {

    void run(T parameter) throws X;

}
