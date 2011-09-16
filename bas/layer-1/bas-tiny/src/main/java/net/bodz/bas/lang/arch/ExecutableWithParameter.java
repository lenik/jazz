package net.bodz.bas.lang.arch;

public interface ExecutableWithParameter<T, X extends Throwable> {

    void run(T parameter) throws X;

}
