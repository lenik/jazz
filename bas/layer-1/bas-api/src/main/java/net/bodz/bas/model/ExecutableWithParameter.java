package net.bodz.bas.model;

public interface ExecutableWithParameter<T, X extends Throwable> {

    void run(T parameter) throws X;

}
