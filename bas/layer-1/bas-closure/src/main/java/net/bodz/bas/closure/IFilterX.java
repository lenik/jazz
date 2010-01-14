package net.bodz.bas.closure;

public interface IFilterX<T, X extends Exception> {

    boolean accept(T o)
            throws X;

}
