package net.bodz.bas.closures;

public interface ExecutableWithParameter<T, X extends Throwable> {

    void run(T parameter) throws X;

}
