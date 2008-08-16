package net.bodz.bas.lang;

public interface RunnableThrows<E extends Throwable> {

    void run() throws E;

}
