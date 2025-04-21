package net.bodz.bas.fn;

public interface ConsumerThrows<T, X extends Throwable> {

    void consume(T value)
            throws X;

}
