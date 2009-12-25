package net.bodz.bas.commons;

public interface ExecutableWithParameter<T, X extends Throwable> {

    void run(T parameter) throws X;

}
