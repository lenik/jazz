package net.bodz.bas.err;

import java.lang.reflect.Constructor;

public class ExceptionSuppliers {

    public static final ExceptionSupplier<RuntimeException> runtime = //
            (String s, Throwable cause) -> new RuntimeException(s, cause);

    public static final ExceptionSupplier<Error> error = //
            (String s, Throwable cause) -> new Error(s, cause);

    public static final ExceptionSupplier<IllegalArgumentException> illegalArgument = //
            (String s, Throwable cause) -> new IllegalArgumentException(s, cause);

    public static final ExceptionSupplier<IllegalUsageException> illegalUsage = //
            IllegalUsageException.SUPPLIER;

    public static final ExceptionSupplier<ParseException> parse = //
            ParseException.SUPPLIER;

    public static final ExceptionSupplier<UnexpectedException> unexpected = //
            UnexpectedException.SUPPLIER;

    public static <E extends Throwable> ExceptionSupplier<E> forType(//
            Class<E> exceptionType) {
        return new ReflectExceptionSupplier<>(exceptionType);
    }

}

class ReflectExceptionSupplier<E extends Throwable>
        implements ExceptionSupplier<E> {

    Class<E> exceptionType;

    public ReflectExceptionSupplier(Class<E> exceptionType) {
        if (exceptionType == null)
            throw new NullPointerException("exceptionType");
        this.exceptionType = exceptionType;
    }

    @Override
    public E supply(String message, Throwable cause) {
        try {
            if (cause == null) {
                Constructor<E> ctor = exceptionType.getConstructor(String.class);
                E e = ctor.newInstance(message);
                return e;
            } else {
                Constructor<E> ctor = exceptionType.getConstructor(String.class, Throwable.class);
                E e = ctor.newInstance(message, cause);
                return e;
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
