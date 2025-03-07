package net.bodz.bas.err;

@FunctionalInterface
public interface IErrorHandler<E, T> {

    boolean handleError(E error, T source);

}
