package net.bodz.bas.mode.fn;

public interface ExecutableWithParameter<T, X extends Throwable> {

    void run(T parameter) throws X;

}
