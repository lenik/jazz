package net.bodz.bas.fn;

public interface IRunner1<T> {

    // FalseRunner1<Object> FALSE = FalseRunner1.getInstance();

    <X extends Throwable> boolean thenFinally(ConsumerThrows<T, X> consumer, String message);

    default <X extends Throwable> boolean thenFailed(ConsumerThrows<T, X> consumer, String message) {
        return !thenFinally(consumer, message);
    }

    <X extends Throwable> IRunner1<T> thenStep(ConsumerThrows<T, X> consumer, String message);

    <R, X extends Throwable> IRunner1<R> then(FunctionThrows<R, T, X> function, String message);

}
