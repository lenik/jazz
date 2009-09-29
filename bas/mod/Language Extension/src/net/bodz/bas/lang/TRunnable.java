package net.bodz.bas.lang;

public interface TRunnable<T, X extends Throwable> {

    void run(T parameter) throws X;

}
