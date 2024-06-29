package net.bodz.bas.c.util;

public interface IAdapterFactory<S, T> {

    Class<S> getSourceType();

    T create(S source);

}
