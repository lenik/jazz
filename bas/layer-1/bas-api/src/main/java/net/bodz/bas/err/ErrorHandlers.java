package net.bodz.bas.err;

public class ErrorHandlers {

    public static <E, T> IErrorHandler<E, T> pass() {
        return new NopErrorHandler<>(true);
    }

    public static <E, T> IErrorHandler<E, T> fail() {
        return new NopErrorHandler<>(false);
    }

}
