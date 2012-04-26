package net.bodz.bas.model;

public interface ICreatorX<T, X extends Throwable> {

    T create()
            throws X;

    T create(Object... parameters)
            throws X;

}
