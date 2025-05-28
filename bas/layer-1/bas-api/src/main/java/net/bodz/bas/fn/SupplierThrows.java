package net.bodz.bas.fn;

@FunctionalInterface
public interface SupplierThrows<T, X extends Throwable> {

    T get()
            throws X;

}
