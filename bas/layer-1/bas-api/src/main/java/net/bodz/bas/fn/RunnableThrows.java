package net.bodz.bas.fn;

public interface RunnableThrows<X extends Throwable> {

    void run()
            throws X;

}
