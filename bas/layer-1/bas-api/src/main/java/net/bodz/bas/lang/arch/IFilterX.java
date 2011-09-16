package net.bodz.bas.lang.arch;

public interface IFilterX<T, X extends Exception> {

    boolean accept(T o)
            throws X;

}
