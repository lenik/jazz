package net.bodz.bas.lang;

public interface VRunnable<T, X extends Throwable> {

    void run(T... parameters) throws X;

}
