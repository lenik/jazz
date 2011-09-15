package net.bodz.bas.util.arch;

public interface ExecutableWithParameter<T, X extends Throwable> {

    void run(T parameter) throws X;

}
