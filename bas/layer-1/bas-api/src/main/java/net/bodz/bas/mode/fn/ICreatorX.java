package net.bodz.bas.mode.fn;

public interface ICreatorX<T, X extends Throwable> {

    T create()
            throws X;

    T create(Object... parameters)
            throws X;

}
