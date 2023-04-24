package net.bodz.bas.site.vhost.registry;

import net.bodz.bas.err.DuplicatedKeyException;

public interface IPortRegistry<T> {

    boolean containsPort(int port);

    T findPort(int port);

    void setDefault(T obj);

    void setPort(int port, T obj);

    void setPortRange(int from, int to, T obj);

    boolean addDefault(T obj);

    boolean addPort(int port, T obj);

    boolean addPortRange(int from, int to, T obj);

    default void registerDefault(T obj)
            throws DuplicatedKeyException {
        if (!addDefault(obj))
            throw new DuplicatedKeyException("<default>");
    }

    default void registerPort(int port, T obj)
            throws DuplicatedKeyException {
        if (!addPort(port, obj))
            throw new DuplicatedKeyException(String.valueOf(port));
    }

    default void registerPortRange(int from, int to, T obj)
            throws DuplicatedKeyException {
        if (!addPortRange(from, to, obj))
            throw new DuplicatedKeyException(String.valueOf(from));
    }

    T removePort(int port);

    T removePortRange(int from, int to);

}
