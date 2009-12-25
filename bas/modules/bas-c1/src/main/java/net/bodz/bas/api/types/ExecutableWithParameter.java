package net.bodz.bas.api.types;

public interface ExecutableWithParameter<T, X extends Throwable> {

    void run(T parameter) throws X;

}
