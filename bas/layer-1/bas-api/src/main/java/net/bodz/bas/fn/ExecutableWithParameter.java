package net.bodz.bas.fn;

public interface ExecutableWithParameter<T, X extends Throwable> {

    void run(T parameter)
            throws X;

}
