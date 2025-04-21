package net.bodz.bas.fn;

public class FalseRunner1<T>
        implements IRunner1<T> {

    private FalseRunner1() {
    }

    @Override
    public <X extends Throwable> boolean thenFinally(ConsumerThrows<T, X> consumer, String message) {
        return false;
    }

    @Override
    public <X extends Throwable> IRunner1<T> thenStep(ConsumerThrows<T, X> consumer, String message) {
        return getInstance();
    }

    @Override
    public <R, X extends Throwable> IRunner1<R> then(FunctionThrows<R, T, X> function, String message) {
        return getInstance();
    }

    static final FalseRunner1<Object> INSTANCE = new FalseRunner1<>();

    @SuppressWarnings("unchecked")
    public static <T> FalseRunner1<T> getInstance() {
        return (FalseRunner1<T>) INSTANCE;
    }

}
