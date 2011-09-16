package net.bodz.bas.lang.arch;

public interface ICreatorX<T, X extends Throwable> {

    T create()
            throws X;

    T create(Object... parameters)
            throws X;

}
