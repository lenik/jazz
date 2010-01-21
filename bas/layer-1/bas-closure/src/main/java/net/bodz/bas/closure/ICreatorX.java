package net.bodz.bas.closure;

public interface ICreatorX<T, X extends Exception> {

    T create()
            throws X;

    T create(Object... parameters)
            throws X;

}
