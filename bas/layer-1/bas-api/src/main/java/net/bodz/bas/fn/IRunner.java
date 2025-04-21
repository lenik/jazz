package net.bodz.bas.fn;

public interface IRunner {

    <X extends Throwable> boolean run(RunnableThrows<X> runnable, String message);

    default <X extends Throwable> boolean failed(RunnableThrows<X> runnable, String message) {
        return !run(runnable, message);
    }

    <T, X extends Throwable> IRunner1<T> run(SupplierThrows<T, X> supplier, String message);

}
