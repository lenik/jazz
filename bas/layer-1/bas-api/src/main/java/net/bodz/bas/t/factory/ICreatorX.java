package net.bodz.bas.t.factory;

public interface ICreatorX<T, X extends Throwable> {

    T create()
            throws X;

    T create(Object... parameters)
            throws X;

}
