package net.bodz.bas.fn;

public interface SupplierThrows<T, X extends Throwable> {

    T supply()
            throws X;

}
