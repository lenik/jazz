package net.bodz.bas.err;

@FunctionalInterface
public interface ExceptionSupplier<E extends Throwable> {

    E supply(String message, Throwable cause);

    default E supply(String message) {
        return supply(message, null);
    }

}
