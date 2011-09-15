package net.bodz.bas.util.arch;

public interface IFilterX<T, X extends Exception> {

    boolean accept(T o)
            throws X;

}
